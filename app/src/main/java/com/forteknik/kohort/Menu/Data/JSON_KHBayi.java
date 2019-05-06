package com.forteknik.kohort.Menu.Data;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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

public class JSON_KHBayi extends AppCompatActivity {

    String id;
    TextView ID, NAMA_PASIEN,
            BAYI_KE,
            NAMA_BAYI,
            PANJANG,
            SUHU,
            FREQ_NAFAS,
            FREQ_DENYUT_JANTUNG,
            DIARE,
            IKTERUS,
            BB_RENDAH,
            PEMBERIAN_ASI,
            VIT_K1,
            IMUNISASI_HB_O,
            noktp;
    TextView judul;
    EditText tv_ke;
    ConstraintLayout detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_khbayi);

        ID = (TextView) findViewById(R.id.tv_idpasien);
        NAMA_PASIEN = (TextView) findViewById(R.id.tv_nama_pasien);

        NAMA_BAYI = (TextView) findViewById(R.id.tv_nama_bayi);
        PANJANG = (TextView) findViewById(R.id.tv_panjang);
        SUHU = (TextView) findViewById(R.id.tv_suhu);
        FREQ_NAFAS = (TextView) findViewById(R.id.tv_freq_nafas);
        FREQ_DENYUT_JANTUNG = (TextView) findViewById(R.id.tv_freq_denyut_jantung);
        DIARE = (TextView) findViewById(R.id.tv_diare);
        IKTERUS = (TextView) findViewById(R.id.tv_ikterus);
        BB_RENDAH = (TextView) findViewById(R.id.tv_bb_rendah);
        PEMBERIAN_ASI = (TextView) findViewById(R.id.tv_pemberian_asi);
        VIT_K1 = (TextView) findViewById(R.id.tv_vit_k1);
        IMUNISASI_HB_O = (TextView) findViewById(R.id.tv_imunisasi_hb_o);

        detail = (ConstraintLayout) findViewById(R.id.detail);
        judul = (TextView) findViewById(R.id.tv_judulkohortanak);
        noktp=(TextView)findViewById(R.id.tv_noktp);

        User user = SharedPrefManager.getInstance(this).getUser();
        NAMA_PASIEN.setText("Ibu "+user.getNama());


        final String no_ktp = user.getNoktp();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        final String id_bayi = id.toString();





        class KohortBayi extends AsyncTask<Void, Void, String> {

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
                        JSONObject JsonBayi = obj.getJSONObject("JSONbayi");

                        //creating a new user object

                        String id =JsonBayi.getString("id");
                        String no_ktp =JsonBayi.getString("no_ktp");
                        String ke =JsonBayi.getString("ke");
                        String nama =JsonBayi.getString("nama");
                        String panjang =JsonBayi.getString("panjang");
                        String suhu =JsonBayi.getString("suhu");
                        String freq_nafas =JsonBayi.getString("freq_nafas");
                        String denyut =JsonBayi.getString("denyut");
                        String diare =JsonBayi.getString("diare");
                        String ikterus =JsonBayi.getString("ikterus");
                        String bbrendah =JsonBayi.getString("bbrendah");
                        String pasi =JsonBayi.getString("pasi");
                        String vitk =JsonBayi.getString("vitk");
                        String imunisasihb =JsonBayi.getString("imunisasihb");

                        judul.setText("Hasil Pemeriksaan Kohort Bayi "+"("+nama+")");
                        ID.setText("(ID) " +id);
                        noktp.setText("No KTP ("+no_ktp+")");


                        NAMA_BAYI.setText(nama);
                        PANJANG.setText(panjang+ " Cm");
                        SUHU.setText(suhu + " Celsius");
                        FREQ_NAFAS.setText(freq_nafas);
                        FREQ_DENYUT_JANTUNG.setText(denyut+ " /Detik");
                        DIARE.setText(diare);
                        IKTERUS.setText(ikterus);
                        BB_RENDAH.setText(bbrendah);
                        PEMBERIAN_ASI.setText(pasi);
                        VIT_K1.setText(vitk);
                        IMUNISASI_HB_O.setText(imunisasihb);
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
                params.put("id_bayi", id_bayi);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_GETBAYI, params);

            }
        }

        KohortBayi ul = new KohortBayi();
        ul.execute();

    }


}