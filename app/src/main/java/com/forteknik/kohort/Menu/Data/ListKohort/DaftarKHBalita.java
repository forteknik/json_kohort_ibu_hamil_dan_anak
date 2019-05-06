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

import com.forteknik.kohort.Menu.Data.JSON_KHBalita;
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

public class DaftarKHBalita extends AppCompatActivity {

    ArrayList<HashMap<String, String>> daftarkohortbalita;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.tampilan_list);

        daftarkohortbalita = new ArrayList<>();

        User user = SharedPrefManager.getInstance(this).getUser();
        final String nama = user.getNama();
        final String noktp = user.getNoktp();
        TextView tv_nama =(TextView)findViewById(R.id.tv_nama_pasien);
        tv_nama.setText(nama);


        class Lv_KHBalita extends AsyncTask<Void, Void, String> {

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
                        final JSONArray list_ibu = obj.getJSONArray("list_balita");

                        for (int i = 0; i < list_ibu.length(); i++)
                        {
                            JSONObject Js_ibu = list_ibu.getJSONObject(i);

                            //creating a new user object
                            String id = Js_ibu.getString("id");
                            String nama = Js_ibu.getString("nama");
                            String tgllahir = Js_ibu.getString("tgllahir");

                            HashMap<String, String> ibu = new HashMap<>();
                            ibu.put("id",id);
                            ibu.put("nama",nama);
                            ibu.put("tgllahir",tgllahir);
                            daftarkohortbalita.add(ibu);

                            ListAdapter adapter = new SimpleAdapter(
                                    DaftarKHBalita.this, daftarkohortbalita,
                                    R.layout.daftar_khbalita, new String[]{"id","nama","tgllahir"
                                    }, new int[]{R.id.id,R.id.nama_balita,R.id.tgl_lahir
                                    });

                            lv=(ListView) findViewById(R.id.list);
                            lv.setAdapter(adapter);

                            lv.setOnItemClickListener(new OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    String listid = ((TextView)view.findViewById(R.id.id)).getText().toString();
                                    Intent intent= new Intent(getApplicationContext(), JSON_KHBalita.class);
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
                return requestHandler.sendPostRequest(URLs.URL_GETLISTBALITA, params);

            }
        }

        Lv_KHBalita ul = new Lv_KHBalita();
        ul.execute();
    }

}
