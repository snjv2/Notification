package com.example.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Cancel extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("Id",0);

        NotificationManager  manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(id);

    }
}