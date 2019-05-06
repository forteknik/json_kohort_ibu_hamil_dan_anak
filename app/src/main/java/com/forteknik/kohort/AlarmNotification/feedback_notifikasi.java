package com.forteknik.kohort.AlarmNotification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.forteknik.kohort.Menu.JadwalPemeriksaan;
import com.forteknik.kohort.Menu.MenuUtama;
import com.forteknik.kohort.R;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.User;

import java.util.Calendar;

public class feedback_notifikasi extends AppCompatActivity {
    private MediaPlayer player;

    @Override
    protected  void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_notifikasi);

        TextView pesan;
        pesan = (TextView) findViewById(R.id.tv_pesan);
        User user = SharedPrefManager.getInstance(this).getUser();
        pesan.setText("Ibu "+user.getNama().toString()+", Hari ini adalah jadwal anda untuk melakukan pemeriksaan kohort Ibu Hamil dan Anak,  Anda harus mendapatkan perawatan yang optimal dengan tujuan untuk menjaga kesehatan Anda dan bayi Anda. Pemeriksaan kehamilan atau antenatal care (ANC) oleh dokter atau bidan merupakan salah satu upaya untuk mendapatkan perawatan kehamilan yang optimal. Ibu hamil yang sering memeriksakan kehamilannya ke dokter dapat mengetahui kondisi kesehatan dirinya dan janin, sehingga ia dapat mencegah hal-hal buruk terjadi padanya dan janin.");

        playsound();


    }

    private void playsound(){
        try{
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }
            }catch(Exception e){
            }
            player = MediaPlayer.create(this, R.raw.sound_feedback);
            player.setLooping(false);
            player.start();

    }
    private void stopsound(){
        player.stop();
    }



    public void tmbHari(View view) {
        stopsound();
        finish();
        Intent intent =new Intent(feedback_notifikasi.this, TambahAlarm.class);
        startActivity(intent);
    }

    public void ok(View view) {
        stopsound();
        finish();
        Intent intent_menuUtama =new Intent(feedback_notifikasi.this, MenuUtama.class);
        startActivity(intent_menuUtama);
        Toast.makeText(this, "Terimakasih, Silahkan Kepuskesmas", Toast.LENGTH_LONG).show();

        stopService(new Intent( feedback_notifikasi.this, NotificationService.class));
        startService(new Intent(feedback_notifikasi.this, NotificationService.class));
        Toast.makeText(this, "Mengatur Ulang Jadwal", Toast.LENGTH_LONG).show();

    }
}
