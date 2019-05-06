package com.forteknik.kohort.Menu.Data;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.forteknik.kohort.R;
import com.forteknik.kohort.RequestHandler;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.URLs;
import com.forteknik.kohort.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class JSON_KHBalita extends AppCompatActivity{
    String id;
    TextView ID,
            NO_KTP,
            NAMA_ANAK,
            NAMA_IBU,
            ALAMAT,
            TGL_LAHIR,
            JK,
            VIT_A,
            GIZI,
            BB,
            TINGGI,
            LINGKAR_KPL,
            IMUNISASI_LANJUTAN,
            PERKEMBANGAN,
            KETERANGAN;
    TextView judul;
    ConstraintLayout detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_khbalita);

        ID = (TextView) findViewById(R.id.tv_idpasien);
        NAMA_ANAK = (TextView) findViewById(R.id.tv_namaanak);
        NAMA_IBU = (TextView) findViewById(R.id.tv_nama_pasien);
        ALAMAT = (TextView) findViewById(R.id.tv_alamat);
        TGL_LAHIR = (TextView) findViewById(R.id.tv_tgl_lahir);
        JK = (TextView) findViewById(R.id.tv_jk);
        VIT_A = (TextView) findViewById(R.id.tv_bak);
        GIZI = (TextView) findViewById(R.id.tv_gizi);
        BB = (TextView) findViewById(R.id.tv_bb);
        TINGGI = (TextView) findViewById(R.id.tv_tinggi);
        LINGKAR_KPL = (TextView) findViewById(R.id.tv_lkr_kpl);
        IMUNISASI_LANJUTAN = (TextView) findViewById(R.id.tv_imunisasi_lanjutan);
        PERKEMBANGAN = (TextView) findViewById(R.id.tv_perkembangan);
        KETERANGAN = (TextView) findViewById(R.id.tv_keterangan);
        detail = (ConstraintLayout) findViewById(R.id.detail);
        judul = (TextView) findViewById(R.id.tv_judulkohort);
        NO_KTP = (TextView) findViewById(R.id.tv_noktp);
//        hidden detail
        detail.setVisibility(View.INVISIBLE);

        User user = SharedPrefManager.getInstance(this).getUser();
        NAMA_IBU.setText("Ibu "+user.getNama());


        final String no_ktp = user.getNoktp();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        final String id_balita = id.toString();

        class KohortBalita extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject JsonAnak = obj.getJSONObject("JSONbalita");

                        //creating a new user object
                        String id = JsonAnak.getString("id");
                        String balitake = JsonAnak.getString("balitake");
                        String no_ktp = JsonAnak.getString("no_ktp");
                        String nama = JsonAnak.getString("nama");
                        String alamat = JsonAnak.getString("alamat");
                        String tgl_lahir = JsonAnak.getString("tgllahir");
                        String jenkel = JsonAnak.getString("jenkel");
                        String vita = JsonAnak.getString("vita");
                        String gizi = JsonAnak.getString("gizi");
                        String bb = JsonAnak.getString("bb");
                        String tinggi = JsonAnak.getString("tinggi");
                        String lp = JsonAnak.getString("lp");
                        String imunisasi = JsonAnak.getString("imunisasi");
                        String kembang = JsonAnak.getString("kembang");
                        String keterangan = JsonAnak.getString("keterangan");

                        NO_KTP.setText("No KTP ("+no_ktp+")");
                        judul.setText("Hasil Pemeriksaan Kohort Balita ("+nama+")");
                        ID.setText("ID ("+id+")");
                        NAMA_ANAK.setText(nama);
                        ALAMAT.setText(alamat);
                        TGL_LAHIR.setText(tgl_lahir);
                        JK.setText(jenkel);
                        VIT_A.setText(vita);
                        GIZI.setText(gizi);
                        BB.setText(bb+" Gram");
                        TINGGI.setText(tinggi+" Cm");
                        LINGKAR_KPL.setText(lp+" Cm");
                        IMUNISASI_LANJUTAN.setText(imunisasi);
                        PERKEMBANGAN.setText(kembang);
                        KETERANGAN.setText(keterangan);
                        detail.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(getApplicationContext(), "Data Belum Tersedia", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("noktp", no_ktp);
                params.put("id_balita", id_balita);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_GETBALITA, params);

            }
        }

        KohortBalita ul = new KohortBalita();
        ul.execute();

    }

}
