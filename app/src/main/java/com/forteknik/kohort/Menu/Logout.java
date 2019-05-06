package com.forteknik.kohort.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.forteknik.kohort.AlarmNotification.NotificationService;
import com.forteknik.kohort.R;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.User;

public class Logout extends AppCompatActivity {
    TextView tv_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_logout);

        tv_logout=(TextView) findViewById(R.id.tv_logout);
        User user = SharedPrefManager.getInstance(this).getUser();
        String nama = user.getNama();
        tv_logout.setText("Ibu "+nama+", Apakah anda yakin untuk keluar akun?");

    }

    public void bt_batal(View view) {
        finish();
    }

    public void bt_logout(View view) {
        stopService(new Intent( Logout.this, NotificationService.class));
        finish();
        SharedPrefManager.getInstance(getApplicationContext()).logout();
    }
}

