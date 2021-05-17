package com.innodeed.notification_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextMessage;
    private Button buttonChannel1;
  //  private Button buttonChannel2;
    private NotificationHelper notificationHelper;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMessage=findViewById(R.id.edittext_message);
        editTextTitle=findViewById(R.id.edittext_title);
        buttonChannel1=findViewById(R.id.btn_channel_1);
      //  buttonChannel2=findViewById(R.id.btn_channel_2);
        notificationHelper=new NotificationHelper(this);
        buttonChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1(editTextTitle.getText().toString(),editTextMessage.getText().toString());
            }
        });
      /*  buttonChannel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOncChannel2(editTextTitle.getText().toString(),editTextMessage.getText().toString());
            }
        });*/


    }
    public void sendOnChannel1(String title,String message){
        NotificationCompat.Builder builder= notificationHelper.getChannel1Notification(title,message);
        notificationHelper.getManager().notify(1,builder.build());
    }
   /* public void sendOncChannel2(String title,String message){
        NotificationCompat.Builder builder= notificationHelper.getChannel2Notification(title,message);
        notificationHelper.getManager().notify(2,builder.build());

    }*/





    }

