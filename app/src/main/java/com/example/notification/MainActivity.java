package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button notifyButton;
   public static final int NOTIFICATION_ID = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notifyButton = findViewById(R.id.Button12);

        Intent mIntent = new Intent(this, Accept.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent acceptIntent = PendingIntent.getActivity(this, 0, mIntent, 0);

        Intent intent = new Intent(this ,Cancel.class);
        intent.putExtra("Id",NOTIFICATION_ID);
        PendingIntent cancelIntent =PendingIntent.getBroadcast(this.getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);





        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                NotificationCompat.Builder  builder = new NotificationCompat.Builder(MainActivity.this,"My Notification")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Notification")
                        .setContentText("Payment Transaction")
                        .addAction(R.drawable.ic_launcher_background,"Accept",acceptIntent)
                        .addAction(R.drawable.ic_launcher_background,"Cancel",cancelIntent)
                        .setAutoCancel(true);



                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

// notificationId is a unique int for each notification that you must define
                notificationManager.notify(NOTIFICATION_ID, builder.build());


            }
        });
    }
}