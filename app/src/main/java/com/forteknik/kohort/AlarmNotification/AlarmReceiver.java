package com.forteknik.kohort.AlarmNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.forteknik.kohort.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 101;
    private static final String TAG_BOOT_BROADCAST_RECEIVER = "BOOT_BROADCAST_RECEIVER";


    @Override
    public void onReceive(Context context, Intent intent) {

            // TODO: This method is called when the BroadcastReceiver is receiving an Intent broadcast.
            String action = intent.getAction();
            Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + "com.forteknik.kohort" + "/" + R.raw.sound_baby);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent contentIntent = new Intent(context, feedback_notifikasi.class);
            Log.d(TAG_BOOT_BROADCAST_RECEIVER, action);

            final PendingIntent contentPendingIntent = PendingIntent.getActivity
                    (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // megambil pesan yang diketik user di EditText pada JadwalPemeriksaan
            String pesan = intent.getExtras().getString("pesan");
            // membuat notifikasi
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(context.getString(R.string.notification_title))
                    .setContentText(pesan)
                    .setContentIntent(contentPendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true);


            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                builder.setSound(soundUri);
            }
            Notification notification = builder.build();
            notification.flags = Notification.FLAG_INSISTENT;
            mNotificationManager.notify((int) System.currentTimeMillis(), notification);
        }
    }


