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

import com.forteknik.kohort.Menu.InfoSkor;
import com.forteknik.kohort.R;
import com.forteknik.kohort.RequestHandler;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.URLs;
import com.forteknik.kohort.User;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class JSON_KHIbu extends AppCompatActivity {

    String putid_kibu, putid_dk, put_skor;
    TextView ID,
            NO_KTP,
            TANGGAL,
            KE,
            TD,
            LP,
            BB,
            ANC,
            detailTD,
            detailLP,
            detailBB,
            KELUHAN,
            UMUR,
            SADAR,
            DETAK,
            TIFU,
            LETAKJANIN,
            KAKI,
            LAB,
            TINDAKAN,
            SARAN,
            SKOR,
            TANGGALX;

    TextView judul,namapasien;
    EditText tv_ke;
    ConstraintLayout detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_khibu);

        namapasien = (TextView) findViewById(R.id.tv_nama_pasien);
        ID = (TextView) findViewById(R.id.tv_idpasien);
        NO_KTP = (TextView) findViewById(R.id.tv_noktp);
        TANGGAL = (TextView) findViewById(R.id.tv_tanggalPemeriksaan);
        TD = (TextView) findViewById(R.id.tv_td);
        LP = (TextView) findViewById(R.id.tv_lp);
        BB = (TextView) findViewById(R.id.tv_bb);
        detailTD = (TextView) findViewById(R.id.tv_tddetail);
        detailLP = (TextView) findViewById(R.id.tv_lpdetail);
        detailBB = (TextView) findViewById(R.id.tv_bbdetail);
        KELUHAN = (TextView) findViewById(R.id.tv_keluhanSekarang);
        UMUR = (TextView) findViewById(R.id.tv_usiahamil);
        SADAR = (TextView) findViewById(R.id.tv_tk);
        DETAK = (TextView) findViewById(R.id.tv_detakjantung);
        TIFU = (TextView) findViewById(R.id.tv_tfundus);
        LETAKJANIN = (TextView) findViewById(R.id.tv_letakjanin);
        KAKI = (TextView) findViewById(R.id.tv_kakibengkak);
        LAB = (TextView) findViewById(R.id.tv_hasillab);
        TINDAKAN = (TextView) findViewById(R.id.tv_tindakan);
        SARAN = (TextView) findViewById(R.id.tv_saranBidan);
        ANC = (TextView) findViewById(R.id.tv_nilaianc);
        detail = (ConstraintLayout) findViewById(R.id.detail);

//        hidden detail
        detail.setVisibility(View.INVISIBLE);

//        Intent intent = getIntent();
//        ke = intent.getStringExtra("ke");
//        tv_ke.setText(ke.toString());
        kohortIbu();
        detail.setVisibility(View.VISIBLE);
        }

    public void lihat(View view) {
        kohortIbu();
    }

private void kohortIbu(){
    final User user = SharedPrefManager.getInstance(this).getUser();
    namapasien.setText(user.getNama());


    judul = (TextView) findViewById(R.id.judulkohortibu);
//    final String pemeriksaan = tv_ke.getText().toString();
    Intent intent = getIntent();

    putid_dk = intent.getStringExtra("id");
    putid_kibu = intent.getStringExtra("id_kibu");
    put_skor = intent.getStringExtra("skor");



    class KohortIbu extends AsyncTask<Void, Void, String> {

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
                    JSONObject Js_ibu = obj.getJSONObject("JSONibu");

                    //creating a new user object
                            String id = Js_ibu.getString("id");
                            String id_kibu = Js_ibu.getString("id_kibu");
                            String tanggal = Js_ibu.getString("tanggal");
                            String ke = Js_ibu.getString("ke");
                            String td = Js_ibu.getString("td");
                            String lp = Js_ibu.getString("lp");
                            String bb = Js_ibu.getString("bb");
                            String tt = Js_ibu.getString("tt");
                            String detailtd = Js_ibu.getString("detailtd");
                            String detaillp = Js_ibu.getString("detaillp");
                            String detailbb = Js_ibu.getString("detailbb");
                            String keluhan = Js_ibu.getString("keluhan");
                            String umur = Js_ibu.getString("umur");
                            String sadar = Js_ibu.getString("sadar");
                            String detak = Js_ibu.getString("detak");
                            String tifu = Js_ibu.getString("tifu");
                            String letakjanin = Js_ibu.getString("letakjanin");
                            String kaki = Js_ibu.getString("kaki");
                            String lab = Js_ibu.getString("lab");
                            String tindakan = Js_ibu.getString("tindakan");
                            String saran = Js_ibu.getString("saran");
                            String tanggalx = Js_ibu.getString("tanggalx");

                    if(Integer.valueOf(ke)==0){
                        int kunke = 1;
                        judul.setText("Hasil Pemeriksaan Kohort Ibu Hamil Ke- "+kunke);
                    } if(Integer.valueOf(ke)==25){
                        int kunke = 2;
                        judul.setText("Hasil Pemeriksaan Kohort Ibu Hamil Ke- "+kunke);
                    } if(Integer.valueOf(ke)==50){
                        int kunke = 3;
                        judul.setText("Hasil Pemeriksaan Kohort Ibu Hamil Ke- "+kunke);
                    } if(Integer.valueOf(ke)==75){
                        int kunke = 4;
                        judul.setText("Hasil Pemeriksaan Kohort Ibu Hamil Ke- "+kunke);
                    }


                    //setting the values to the textviews
                    ID.setText("ID K.ibu ("+id_kibu+")");
                    NO_KTP.setText("No KTP ("+user.getNoktp()+")");
                    TANGGAL.setText(tanggal);
                    TD.setText("Nilai : "+td);
                    LP.setText("Nilai : "+lp);
                    BB.setText("Nilai : "+bb);
                    ANC.setText(tt);
                    detailTD.setText(detailtd+" MMHG");
                    detailLP.setText(detaillp+"Cm");
                    detailBB.setText(detailbb +"Kg");
                    KELUHAN.setText(keluhan);
                    UMUR.setText(umur+" Minggu");
                    SADAR.setText(sadar);
                    DETAK.setText(detak+"/Menit");
                    TIFU.setText(tifu+"Cm" );
                    LETAKJANIN.setText(letakjanin);
                    KAKI.setText(kaki);
                    LAB.setText(lab);
                    TINDAKAN.setText(tindakan);
                    SARAN.setText(saran);

                } else {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan Saat Mengambil Data", Toast.LENGTH_SHORT).show();
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
            params.put("id_kibu", putid_kibu);
            params.put("id_detailkibu", putid_dk);

            //returing the response
            return requestHandler.sendPostRequest(URLs.URL_GETIBU, params);

        }
    }

    KohortIbu ul = new KohortIbu();
    ul.execute();
}

    public void info_skor(View view) {
        Intent intent_tgl_bersalin = new Intent(JSON_KHIbu.this,InfoSkor.class);
        startActivity(intent_tgl_bersalin);
    }
}