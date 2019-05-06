package com.forteknik.kohort.Menu.Data;

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

public class JSON_KHNifas extends AppCompatActivity{

    TextView ID,
            NO_KTP,
            KONDISI,
            PERVIGMA,
            PERINEUM,
            INFEKSI,
            KONTRAKSI,
            UTERI,
            PAYUDARA,
            ASI,
            VITA,
            PASCA,
            KOMPLIKASI,
            BAB,
            BAK,
            KANAK,
            PASI,
            TINDAKAN,
            SARAN;
    TextView judul,namapasien;
    ConstraintLayout detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_khnifas);

        namapasien = (TextView) findViewById(R.id.tv_nama_pasien);
        ID= (TextView) findViewById(R.id.tv_id);
        NO_KTP = (TextView) findViewById(R.id.tv_noktp);
        KONDISI= (TextView) findViewById(R.id.tv_kondisi);
        PERVIGMA= (TextView) findViewById(R.id.tv_pervigma);
        PERINEUM= (TextView) findViewById(R.id.tv_perineum);
        INFEKSI= (TextView) findViewById(R.id.tv_infeksi);
        KONTRAKSI= (TextView) findViewById(R.id.tv_kontraksi);
        UTERI= (TextView) findViewById(R.id.tv_uteri);
        PAYUDARA= (TextView) findViewById(R.id.tv_spayudara);
        ASI= (TextView) findViewById(R.id.tv_ASI);
        VITA= (TextView) findViewById(R.id.tv_vita);
        PASCA= (TextView) findViewById(R.id.tv_letakjanin);
        KOMPLIKASI= (TextView) findViewById(R.id.tv_komplikasi);
        BAB= (TextView) findViewById(R.id.tv_bab);
        BAK= (TextView) findViewById(R.id.tv_bak);
        KANAK= (TextView) findViewById(R.id.tv_kanak);
        PASI= (TextView) findViewById(R.id.tv_pemASI);
        TINDAKAN= (TextView) findViewById(R.id.tv_tindakan);
        SARAN= (TextView) findViewById(R.id.tv_saranBidan);

        judul = (TextView) findViewById(R.id.judulkohort);
        detail=(ConstraintLayout) findViewById(R.id.detail);
        detail.setVisibility(View.INVISIBLE);

        User user = SharedPrefManager.getInstance(this).getUser();
        namapasien.setText("Ibu "+user.getNama());
        Kohort_nifas();

        judul.setText("Hasil Pemeriksaan Kohort Nifas");
        //setting the values to the textviews


    }
    private void Kohort_nifas() {
        User user = SharedPrefManager.getInstance(this).getUser();
        final String noktp = user.getNoktp();

        class KohortNifas extends AsyncTask<Void, Void, String> {

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
                        JSONObject JsonHasil = obj.getJSONObject("nifasibu");

                        //creating a new user object

                               String id = JsonHasil.getString("id");
                        String no_ktp = JsonHasil.getString("no_ktp");
                        String kondisi = JsonHasil.getString("kondisi");
                        String pervigma = JsonHasil.getString("pervigma");
                        String perineum = JsonHasil.getString("perineum");
                        String infeksi = JsonHasil.getString("infeksi");
                        String kontraksi = JsonHasil.getString("kontraksi");
                        String uteri = JsonHasil.getString("uteri");
                        String payudara = JsonHasil.getString("payudara");
                        String asi = JsonHasil.getString("asi");
                        String vita = JsonHasil.getString("vita");
                        String pasca = JsonHasil.getString("pasca");
                        String komplikasi = JsonHasil.getString("komplikasi");
                        String bab = JsonHasil.getString("bab");
                        String bak = JsonHasil.getString("bak");
                        String kanak = JsonHasil.getString("kanak");
                        String pasi = JsonHasil.getString("pasi");
                        String tindakan = JsonHasil.getString("tindakan");
                        String saran = JsonHasil.getString("saran");

                        ID.setText("ID ("+id+")");
                        NO_KTP.setText("No KTP (" + no_ktp+")");
                        KONDISI.setText(kondisi);
                        PERVIGMA.setText(pervigma);
                        PERINEUM.setText(perineum);
                        INFEKSI.setText(infeksi);
                        KONTRAKSI.setText(kontraksi);
                        UTERI.setText(uteri);
                        PAYUDARA.setText(payudara);
                        ASI.setText(asi);
                        VITA.setText(vita);
                        PASCA.setText(pasca);
                        KOMPLIKASI.setText(komplikasi);
                        BAB.setText(bab);
                        BAK.setText(bak);
                        KANAK.setText(kanak);
                        PASI.setText(pasi);
                        TINDAKAN.setText(tindakan);
                        SARAN.setText(saran);

                        detail.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Kesalahan Mengabil Data", Toast.LENGTH_SHORT).show();
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
                params.put("noktp", noktp);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_GETNIFAS, params);

            }
        }

        KohortNifas ul = new KohortNifas();
        ul.execute();
    }
    public void lihat(View view) {
        Kohort_nifas();
    }
}