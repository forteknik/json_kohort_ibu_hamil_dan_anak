package com.forteknik.kohort.Menu.Data.ListKohort;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.forteknik.kohort.Menu.Data.JSON_KHBayi;
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

public class DaftarKHBayi extends AppCompatActivity {

    ArrayList<HashMap<String, String>> daftarkohortbayi;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.tampilan_list);

        daftarkohortbayi = new ArrayList<>();

        User user = SharedPrefManager.getInstance(this).getUser();
        final String nama = user.getNama();
        final String noktp = user.getNoktp();
        TextView tv_nama =(TextView)findViewById(R.id.tv_nama_pasien);
        tv_nama.setText(nama);


        class Lv_KHBayi extends AsyncTask<Void, Void, String> {

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
                    if (s!=null) {
                        //getting the user from the response
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        final JSONArray list_bayi = obj.getJSONArray("list_bayi");

                        for (int i = 0; i < list_bayi.length(); i++)
                        {
                            JSONObject Js_ibu = list_bayi.getJSONObject(i);
                            String id = Js_ibu.getString("id");
                            String nama = Js_ibu.getString("nama");

                            HashMap<String, String> bayi = new HashMap<>();
                            bayi.put("id",id);
                            bayi.put("nama",nama);
                            daftarkohortbayi.add(bayi);

                            ListAdapter adapter = new SimpleAdapter(
                                    DaftarKHBayi.this, daftarkohortbayi,
                                    R.layout.daftar_khbayi, new String[]{"id","nama"
                                    }, new int[]{R.id.id,R.id.nama_bayi
                                    });

                            lv=(ListView) findViewById(R.id.list);
                            lv.setAdapter(adapter);

                            lv.setOnItemClickListener(new OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String listid = ((TextView)view.findViewById(R.id.id)).getText().toString();
                                    Intent intent= new Intent(getApplicationContext(), JSON_KHBayi.class);
                                    intent.putExtra("id", listid);
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

                params.put("noktp", noktp);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_GETLISTBAYI, params);

            }
        }

        Lv_KHBayi ul = new Lv_KHBayi();
        ul.execute();
    }

}
