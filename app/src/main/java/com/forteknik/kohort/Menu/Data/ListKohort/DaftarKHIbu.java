package com.forteknik.kohort.Menu.Data.ListKohort;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.forteknik.kohort.Menu.Data.JSON_KHIbu;
import com.forteknik.kohort.R;
import com.forteknik.kohort.RequestHandler;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.URLs;
import com.forteknik.kohort.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.AdapterView.OnItemClickListener;

public class DaftarKHIbu extends AppCompatActivity {
   String putid_kibu, putNP;
    ArrayList<HashMap<String, String>> daftarkohortibu;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.tampilan_list);

        daftarkohortibu = new ArrayList<>();

        User user = SharedPrefManager.getInstance(this).getUser();
        final String id_kibupasien = user.getId();
        final String nama = user.getNama();
        TextView tv_nama =(TextView)findViewById(R.id.tv_nama_pasien);
        tv_nama.setText(nama);

        Intent intent = getIntent();
        putid_kibu = intent.getStringExtra("id_kibu");
        putNP = intent.getStringExtra("NP");

        class Lv_KHIbu extends AsyncTask<Void, Void, String> {

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
                    if (s!=null) {
//                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//

                        //getting the user from the response
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        final JSONArray list_ibu = obj.getJSONArray("list_ibu");

                        for (int i = 0; i < list_ibu.length(); i++)
                        {
                            JSONObject Js_ibu = list_ibu.getJSONObject(i);

                            //creating a new user object
                            String id_detailkibu = Js_ibu.getString("id");
                            String id_kibu = Js_ibu.getString("id_kibu");
                            String ke = Js_ibu.getString("ke");
                            String tanggal = Js_ibu.getString("tanggal");
                            String tt = Js_ibu.getString("tt");


                            HashMap<String, String> ibu = new HashMap<>();
                            ibu.put("id", id_detailkibu);
                            ibu.put("id_kibu",id_kibu);
                            ibu.put("tanggal",tanggal);
                            ibu.put("skor",tt);
                            daftarkohortibu.add(ibu);

                            ListAdapter adapter = new SimpleAdapter(
                                    DaftarKHIbu.this, daftarkohortibu,
                                    R.layout.daftar_khibu, new String[]{"id","id_kibu","tanggal","skor"
                                    }, new int[]{R.id.tv_iddetailkibu,R.id.item1,R.id.item3,R.id.tv_anc
                                    });

                            lv=(ListView) findViewById(R.id.list);
                            lv.setAdapter(adapter);

                            lv.setOnItemClickListener(new OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    String putid_dk = ((TextView)view.findViewById(R.id.tv_iddetailkibu)).getText().toString();
                                    String putid_kibu = ((TextView)view.findViewById(R.id.item1)).getText().toString();
                                    String put_skor =((TextView)view.findViewById(R.id.tv_anc)).getText().toString();
                                    Intent intent= new Intent(getApplicationContext(), JSON_KHIbu.class);

                                    intent.putExtra("id", putid_dk);
                                    intent.putExtra("id_kibu", putid_kibu);
                                    intent.putExtra("skor", put_skor);
                                    startActivity(intent);
                                }
                            });
                        }

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

                params.put("idkibu", putid_kibu);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_GETLISTIBU, params);

            }
        }

        Lv_KHIbu ul = new Lv_KHIbu();
        ul.execute();
    }

}
