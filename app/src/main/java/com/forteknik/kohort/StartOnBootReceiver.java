package com.forteknik.kohort;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.forteknik.kohort.AlarmNotification.AlarmReceiver;
import com.forteknik.kohort.AlarmNotification.NotificationService;

public class StartOnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Start Notification Service
            Intent serviceIntent = new Intent(context, NotificationService.class);
            context.startService(serviceIntent);
           }
    }
}
