package com.Scheduling_Alarms_and_AlarmManager.alarminten;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
      Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        AlarmManager();



    }

    public void AlarmManager(){
        Intent intent = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,
                0,intent,0);

        AlarmManager alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
        long timeAtButtonClick= System.currentTimeMillis();
        long tenMinutesInMillis=1000*10*60;
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick +
                tenMinutesInMillis,pendingIntent);

    }


    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="channel1";
            String description="Channel for Reminder";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel= new NotificationChannel("notify", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


    }
}