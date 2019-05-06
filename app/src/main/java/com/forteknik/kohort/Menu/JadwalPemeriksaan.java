package com.forteknik.kohort.Menu;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.forteknik.kohort.AlarmNotification.AlarmReceiver;
import com.forteknik.kohort.AlarmNotification.NotificationService;
import com.forteknik.kohort.R;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class JadwalPemeriksaan extends AppCompatActivity {

    static TextView tvDate,tvDate2,tvDate3,tvDate4, tvnotifikasiaktif;
    static ImageView ic_alarm1,ic_alarm2,ic_alarm3,ic_alarm4;

    private String[] namabulan = {"Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov","Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.jadwal_pemeriksaan);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate2 = (TextView) findViewById(R.id.tvDate2);
        tvDate3 = (TextView) findViewById(R.id.tvDate3);
        tvDate4 = (TextView) findViewById(R.id.tvDate4);
        tvnotifikasiaktif = (TextView) findViewById(R.id.tv_notifikasi_aktif);

        ic_alarm1=(ImageView) findViewById(R.id.ic_alarm1);
        ic_alarm2=(ImageView) findViewById(R.id.ic_alarm2);
        ic_alarm3=(ImageView) findViewById(R.id.ic_alarm3);
        ic_alarm4=(ImageView) findViewById(R.id.ic_alarm4);

        ic_alarm1.setVisibility(View.INVISIBLE);
        ic_alarm2.setVisibility(View.INVISIBLE);
        ic_alarm3.setVisibility(View.INVISIBLE);
        ic_alarm4.setVisibility(View.INVISIBLE);




        User user = SharedPrefManager.getInstance(this).getUser();
        String tglHPHT = user.getTglhpht();
        SimpleDateFormat dfhpht = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calTS1 = Calendar.getInstance();
        Calendar calTS2 = Calendar.getInstance();
        Calendar calTS3 = Calendar.getInstance();
        Calendar calTS32 = Calendar.getInstance();
//TS1
        try {
            calTS1.setTime(dfhpht.parse(tglHPHT));
            calTS1.set(Calendar.HOUR_OF_DAY, 8);
            calTS1.add(Calendar.DAY_OF_MONTH,60);

            int tahuncalTS1=calTS1.get(Calendar.YEAR);
            int bulancalTS1=calTS1.get(Calendar.MONTH);
            int haricalTS1=calTS1.get(Calendar.DAY_OF_MONTH);
            int jamTS1= calTS1.get(Calendar.HOUR_OF_DAY);
            String tanggalTS1= haricalTS1+"-"+namabulan[bulancalTS1]+"-"+tahuncalTS1+" Jam :"+jamTS1+":00";
            tvDate.setText(tanggalTS1);

            //TS2
            calTS2.setTime(dfhpht.parse(tglHPHT));
            calTS2.set(Calendar.HOUR_OF_DAY, 8);
            calTS2.add(Calendar.DAY_OF_MONTH,92);

            int tahuncalTS2=calTS2.get(Calendar.YEAR);
            int bulancalTS2=calTS2.get(Calendar.MONTH);
            int haricalTS2=calTS2.get(Calendar.DAY_OF_MONTH);
            int jamTS2= calTS2.get(Calendar.HOUR_OF_DAY);
            String tanggalTS2= haricalTS2+"-"+namabulan[bulancalTS2]+"-"+tahuncalTS2+" Jam :"+jamTS2+":00";
            tvDate2.setText(tanggalTS2);

            //TS3
            calTS3.setTime(dfhpht.parse(tglHPHT));
            calTS3.set(Calendar.HOUR_OF_DAY, 8);
            calTS3.add(Calendar.DAY_OF_MONTH,190);

            int tahuncalTS3=calTS3.get(Calendar.YEAR);
            int bulancalTS3=calTS3.get(Calendar.MONTH);
            int haricalTS3=calTS3.get(Calendar.DAY_OF_MONTH);
            int jamTS3= calTS3.get(Calendar.HOUR_OF_DAY);
            String tanggalTS3= haricalTS3+"-"+namabulan[bulancalTS3]+"-"+tahuncalTS3+" Jam :"+jamTS3+":00";
            tvDate3.setText(tanggalTS3);

            //TS3.2
            calTS32.setTime(dfhpht.parse(tglHPHT));
            calTS32.set(Calendar.HOUR_OF_DAY, 8);
            calTS32.add(Calendar.DAY_OF_MONTH,239);

            int tahuncalTS32=calTS32.get(Calendar.YEAR);
            int bulancalTS32=calTS32.get(Calendar.MONTH);
            int haricalTS32=calTS32.get(Calendar.DAY_OF_MONTH);
            int jamTS32= calTS32.get(Calendar.HOUR_OF_DAY);
            String tanggalTS32= haricalTS32+"-"+namabulan[bulancalTS32]+"-"+tahuncalTS32+" Jam :"+jamTS32+":00";
            tvDate4.setText(tanggalTS32);
            Calendar calSekarang = Calendar.getInstance();
            if (calSekarang.equals(calTS1) || calSekarang.before(calTS1)) {
                ic_alarm1.setVisibility(View.VISIBLE);
                ic_alarm2.setVisibility(View.INVISIBLE);
                ic_alarm3.setVisibility(View.INVISIBLE);
                ic_alarm4.setVisibility(View.INVISIBLE);
                tvnotifikasiaktif.setText("Jadwal Pemeriksaan Berikutnya : Jadwal (1)");
            } else if (calSekarang.equals(calTS2) || calSekarang.before(calTS2)) {
                ic_alarm1.setVisibility(View.INVISIBLE);
                ic_alarm2.setVisibility(View.VISIBLE);
                ic_alarm3.setVisibility(View.INVISIBLE);
                ic_alarm4.setVisibility(View.INVISIBLE);
                tvnotifikasiaktif.setText("Jadwal Pemeriksaan Berikutnya : Jadwal (2)");
            } else if (calSekarang.equals(calTS3) || calSekarang.before(calTS3)) {
                ic_alarm1.setVisibility(View.INVISIBLE);
                ic_alarm2.setVisibility(View.INVISIBLE);
                ic_alarm3.setVisibility(View.VISIBLE);
                ic_alarm4.setVisibility(View.INVISIBLE);
                tvnotifikasiaktif.setText("Jadwal Pemeriksaan Berikutnya : Jadwal (3)");
            } else if (calSekarang.equals(calTS32) || calSekarang.before(calTS32)) {
                ic_alarm1.setVisibility(View.INVISIBLE);
                ic_alarm2.setVisibility(View.INVISIBLE);
                ic_alarm3.setVisibility(View.INVISIBLE);
                ic_alarm4.setVisibility(View.VISIBLE);
                tvnotifikasiaktif.setText("Jadwal Pemeriksaan Berikutnya : Jadwal (4)");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void refreshAlarm(View view) {
        stopService(new Intent( JadwalPemeriksaan.this, NotificationService.class));
        startService(new Intent(JadwalPemeriksaan.this, NotificationService.class));
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
