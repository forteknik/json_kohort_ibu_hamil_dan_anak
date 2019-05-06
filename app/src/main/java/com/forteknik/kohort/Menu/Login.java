package com.forteknik.kohort.Menu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.forteknik.kohort.AlarmNotification.NotificationService;
import com.forteknik.kohort.R;
import com.forteknik.kohort.RequestHandler;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.URLs;
import com.forteknik.kohort.User;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class Login extends AppCompatActivity {

    EditText et_noktp, et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        et_noktp = (EditText) findViewById(R.id.editTextUsername);
        et_password = (EditText) findViewById(R.id.editTextPassword);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MenuUtama.class));
            return;
        }

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                userLogin();
                final String noktp = et_noktp.getText().toString();
                final String password = et_password.getText().toString();

                if (TextUtils.isEmpty(noktp)) {
                    et_noktp.setError("No KTP Name Tidak Boleh Kosong");
                    et_noktp.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    et_password.setError("Silahkan Masukkan Password");
                    et_password.requestFocus();
                    return;
                }


                class UserLogin extends AsyncTask<Void, Void, String> {

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
                                JSONObject userJson = obj.getJSONObject("pasien");

                                //creating a new user object
                                User user = new User(
                                        userJson.getString("id"),
                                        userJson.getString("noktp"),
                                        userJson.getString("nama"),
                                        userJson.getString("alamat"),
                                        userJson.getString("tempatlahir"),
                                        userJson.getString("tgllahir"),
                                        userJson.getString("agama"),
                                        userJson.getString("telp"),
                                        userJson.getString("pekerjaan"),
                                        userJson.getString("pendidikan"),
                                        userJson.getString("goldarah"),
                                        userJson.getString("tglhpht"),
                                        userJson.getString("password")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MenuUtama.class));
                                startService(new Intent(Login.this, NotificationService.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Kesalahan username atau password", Toast.LENGTH_SHORT).show();
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
                        params.put("password", password);

                        //returing the response
                        return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);

                    }
                }

                UserLogin ul = new UserLogin();
                ul.execute();
            }
        });

    }


}