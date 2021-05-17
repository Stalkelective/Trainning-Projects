package com.innodeed.notification_app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper  extends ContextWrapper
{
    public static final String channel1_ID="channel1_ID";
    public static final String channel1_Name="Channel1 ";// name should be what the channel is used for
   // public static final String channel2_ID="channel2_ID";
   // public static final String channel2_Name="Channel 2 ";// name should be what the channel is used for

    private NotificationManager notificationManager;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        createChannels();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels()
    {

        NotificationChannel channel1 =new NotificationChannel(channel1_ID,channel1_Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.design_default_color_primary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel1);
       /* NotificationChannel channel2 =new NotificationChannel(channel2_ID,channel2_Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.design_default_color_primary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel2);*/
    }
    public NotificationManager getManager(){
        if (notificationManager==null){
            notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public NotificationCompat.Builder getChannel1Notification(String title,String message) {
        Intent resultIntent =new  Intent(this,MainActivity.class);
        PendingIntent resultPendingIntent= PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), channel1_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_one)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);

    }

      /*  public NotificationCompat.Builder getChannel2Notification(String title,String message){

            return new NotificationCompat.Builder(getApplicationContext(),channel2_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_two);
    }*/
}
