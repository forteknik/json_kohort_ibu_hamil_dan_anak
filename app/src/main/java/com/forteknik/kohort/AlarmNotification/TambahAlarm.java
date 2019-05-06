package com.forteknik.kohort.AlarmNotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.forteknik.kohort.R;

import java.util.Calendar;

public class TambahAlarm extends AppCompatActivity {
    private static final String ACTION_NOTIFY =
            "com.forteknik.kohort.ACTION_NOTIFY";
    AlarmManager alarmManager;
    EditText et_menit;
    PendingIntent pendingIntent;
    @Override
    protected  void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_alarm);
        et_menit=(EditText) findViewById(R.id.et_menit);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


    }

    public void setAlarm(){
        int i = Integer.parseInt(et_menit.getText().toString());
        Calendar cal_tmbHari = Calendar.getInstance();
        cal_tmbHari.add(Calendar.MINUTE, i);
        alarmManager.cancel(pendingIntent);
        Intent notifyIntent = new Intent(ACTION_NOTIFY);
        notifyIntent.putExtra("pesan", "Silahkan Ke Puskesmas Untuk Pemeriksaan");
        pendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), notifyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_tmbHari.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Pengingat Ditambah"+et_menit.getText()+" Menit", Toast.LENGTH_SHORT).show();
    }

    public void ok(View view) {
        setAlarm();
        finish();

    }

    public void batal(View view) {
        finish();
    }
}
