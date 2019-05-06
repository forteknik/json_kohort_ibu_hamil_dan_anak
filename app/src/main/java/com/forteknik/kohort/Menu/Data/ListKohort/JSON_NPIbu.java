package com.forteknik.kohort.Menu.Data.ListKohort;

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

public class JSON_NPIbu extends AppCompatActivity {

    String putke;
    TextView ID_KIBU,
            ID_PASIEN,
            NAMA,
            NOKTP,
            SO,
            SP,
            JK,
            UM,
            KOM,
            TB,
            tvNP
    ;



    ConstraintLayout detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nilai_np);

        ID_KIBU= (TextView) findViewById(R.id.tv_idkibu);

        NAMA= (TextView) findViewById(R.id.tv_nama_pasien);
        NOKTP= (TextView) findViewById(R.id.tv_noktp);
        SO= (TextView) findViewById(R.id.tv_so);
        SP= (TextView) findViewById(R.id.tv_sp);
        JK= (TextView) findViewById(R.id.tv_jk);
        UM= (TextView) findViewById(R.id.tv_um);
        KOM= (TextView) findViewById(R.id.tv_kom);
        TB= (TextView) findViewById(R.id.tv_tb);
        NOKTP=(TextView) findViewById(R.id.tv_noktp);
        tvNP= (TextView) findViewById(R.id.tv_np) ;


        detail = (ConstraintLayout) findViewById(R.id.detail);

//        hidden detail
        detail.setVisibility(View.INVISIBLE);

        kohortIbu();
        }

    public void lihat(View view) {
        kohortIbu();
    }

private void kohortIbu(){
    User user = SharedPrefManager.getInstance(this).getUser();

    final String noktp = user.getNoktp();

    Intent intent = getIntent();
    putke = intent.getStringExtra("ke");



    class KohortIbu extends AsyncTask<Void, Void, String> {

        ProgressBar progressBar;
        TextView txtprogressbar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            txtprogressbar =(TextView) findViewById(R.id.progressBarMessage);
            txtprogressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            txtprogressbar.setVisibility(View.GONE);


            try {
                //converting response to json object
                JSONObject obj = new JSONObject(s);

                //if no error in response
                if (!obj.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    //getting the user from the response
                    JSONObject js_kibu = obj.getJSONObject("JSONkibu");

                            final String id_kibu = js_kibu.getString("id_kibu");
                            String id_pasien = js_kibu.getString("id_pasien");
                            String nama = js_kibu.getString("nama");
                            String noktp = js_kibu.getString("noktp");
                            String so = js_kibu.getString("so");
                            String sp = js_kibu.getString("sp");
                            String jk = js_kibu.getString("jk");
                            String um = js_kibu.getString("um");
                            String kom = js_kibu.getString("kom");
                            String tb = js_kibu.getString("tb");


                            final Integer NP = Integer.valueOf(so)+Integer.valueOf(sp)+Integer.valueOf(jk)+Integer.valueOf(um)+Integer.valueOf(kom)+Integer.valueOf(tb);
                            tvNP.setText(NP.toString());
                    ID_KIBU.setText("ID Kibu("+id_kibu+")");

                    NAMA.setText("Ibu "+nama);
                    NOKTP.setText("No KTP ("+noktp+")");

                    if(Integer.valueOf(so)==10){
                    SO.setText("Belum Pernah (Nilai:("+so+")");
                    }else{
                        SO.setText("Sudah Pernah (Nilai:("+so+")");
                    }

                    if(Integer.valueOf(sp)==10){
                        SP.setText("Belum Pernah (Nilai:("+sp+")");
                    }else {
                        SP.setText("Sudah Pernah (Nilai:("+sp+")");
                    }

                    if(Integer.valueOf(jk)==10){
                        JK.setText("Kurang Dari 2 Tahun (Nilai:("+jk+")");
                    }else{
                        JK.setText("Kurang Dari 2 Tahun (Nilai:("+jk+")");
                    }

                    if(Integer.valueOf(um)==30){
                        UM.setText("30-35 Tahun ("+um+")");
                    }else{
                        UM.setText("Kurang 30 / Lebih 35 Tahun ("+um+")");
                    }

                    if(Integer.valueOf(kom)==30){
                        KOM.setText("Belum Pernah ("+kom+")");
                    }else{
                        KOM.setText("Sudah Pernah ("+kom+")");
                    }
                    if(Integer.valueOf(tb)==10){
                        TB.setText("165cm-180cm Tahun ("+tb+")");
                    }else{
                        TB.setText("Kurang 165cm / Lebih 180cm Tahun ("+tb+")");
                    }
                    detail.setVisibility(View.VISIBLE);


                    findViewById(R.id.bt_hasilkjg).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String putid_kibu = id_kibu.toString();
                            String putNP= Integer.valueOf(NP).toString();
                            Intent intent= new Intent(getApplicationContext(), DaftarKHIbu.class);


                            intent.putExtra("id_kibu", putid_kibu);
                            intent.putExtra("NP", putNP);

                            startActivity(intent);
                        }
                    });


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
            params.put("noktp", noktp);

            //returing the response
            return requestHandler.sendPostRequest(URLs.URL_GETKIBU, params);

        }
    }

    KohortIbu ul = new KohortIbu();
    ul.execute();
}

}