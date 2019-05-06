package com.forteknik.kohort.AlarmNotification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotificationService extends Service {
    // konstanta ACTION_NOTIFY untuk memanggil AlarmReceiver melalui manifest
    private static final String ACTION_NOTIFY = "com.forteknik.kohort.ACTION_NOTIFY";
    AlarmManager alarmManager;
    NotificationManager mNotificationManager;
    PendingIntent notifyPendingIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Toast.makeText(this, " MyService onBInd ", Toast.LENGTH_SHORT).show();
        return null;
    }
    @Override
    public void onCreate() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        setAlarm();
    }

    @Override
    public void onDestroy() {
        //Stop Service
        stopAlarmManager();
    }

    public void setAlarm(){

        User user = SharedPrefManager.getInstance(this).getUser();
        String tglHPHT = user.getTglhpht();

        SimpleDateFormat dfhpht = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calTS1 = Calendar.getInstance();
        Calendar calTS2 = Calendar.getInstance();
        Calendar calTS3 = Calendar.getInstance();
        Calendar calTS32 = Calendar.getInstance();

        try {
            calTS1.setTime(dfhpht.parse(tglHPHT));
            calTS1.set(Calendar.HOUR_OF_DAY, 8);
            calTS1.set(Calendar.MINUTE,00);
            calTS1.set(Calendar.SECOND, 0);
            calTS1.add(Calendar.DAY_OF_MONTH,60);

            calTS2.setTime(dfhpht.parse(tglHPHT));
            calTS2.set(Calendar.HOUR_OF_DAY, 8);
            calTS2.set(Calendar.MINUTE,00);
            calTS2.add(Calendar.DAY_OF_MONTH,92);

            calTS3.setTime(dfhpht.parse(tglHPHT));
            calTS3.set(Calendar.HOUR_OF_DAY,8);
            calTS3.set(Calendar.MINUTE,00);
            calTS1.set(Calendar.SECOND, 0);
            calTS3.add(Calendar.DAY_OF_MONTH,190);

            calTS32.setTime(dfhpht.parse(tglHPHT));
            calTS32.set(Calendar.HOUR_OF_DAY, 8);
            calTS32.set(Calendar.MINUTE,00);
            calTS1.set(Calendar.SECOND, 0);
            calTS32.add(Calendar.DAY_OF_MONTH,239);


//          membuat Intent, memanggil AlarmReceiver
            Intent notifyIntent = new Intent(ACTION_NOTIFY);

            String cal1 = calTS1.getTime().toString();
            String cal2 = calTS2.getTime().toString();
            String cal3 = calTS3.getTime().toString();
            String cal4 = calTS32.getTime().toString();
            Calendar calSekarang = Calendar.getInstance();


//          Reset ALarm
            alarmManager.cancel(notifyPendingIntent);

            if (calSekarang.equals(calTS1) || calSekarang.before(calTS1)) {
                notifyIntent.putExtra("pesan", "Pemeriksaan (1) Anda Hari Ini : \n" +cal1);
                notifyPendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calTS1.getTimeInMillis(), notifyPendingIntent);
                Toast.makeText(this, "Pengingat Jadwal Pemeriksaan 1 Aktif", Toast.LENGTH_SHORT).show();
            } else if (calSekarang.equals(calTS2) || calSekarang.before(calTS2)) {
                notifyIntent.putExtra("pesan", "Pemeriksaan (2) Anda Hari Ini : \n" +cal2);
                notifyPendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calTS2.getTimeInMillis(), notifyPendingIntent);
                Toast.makeText(this, "Pengingat Jadwal Pemeriksaan 2 Aktif",Toast.LENGTH_SHORT).show();
            } else if (calSekarang.equals(calTS3) || calSekarang.before(calTS3)) {
                notifyIntent.putExtra("pesan", "Pemeriksaan (3) Anda Hari Ini : \n" +cal3);
                notifyPendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calTS3.getTimeInMillis(), notifyPendingIntent);
                Toast.makeText(this, "Pengingat Jadwal Pemeriksaan 3 Aktif", Toast.LENGTH_SHORT).show();
            } else if (calSekarang.equals(calTS32) || calSekarang.before(calTS32)) {
                notifyIntent.putExtra("pesan", "Pemeriksaan (4) Anda Hari Ini : \n" +cal4);
                notifyPendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calTS32.getTimeInMillis(), notifyPendingIntent);
                Toast.makeText(this, "Pengingat Jadwal Pemeriksaan 4 Aktif",Toast.LENGTH_SHORT).show();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void stopAlarmManager() {
        alarmManager.cancel(notifyPendingIntent);
        mNotificationManager.cancelAll();
        Toast.makeText(this, "Reset Alarm", Toast.LENGTH_SHORT).show();
    }

    public void setting_alarm(){

    }

}
