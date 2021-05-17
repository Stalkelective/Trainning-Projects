package com.innodeed.digiclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static BroadcastReceiver tickReceiver;
    private TextView hourText;
    private TextView minuteText;
    private TextView secText;
    private TextView day;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hourText=(TextView)findViewById(R.id.hourText);
        minuteText=(TextView)findViewById(R.id.minuteText);
        secText=(TextView)findViewById(R.id.secText);
        day=(TextView)findViewById(R.id.day);


        //setting current time at startup
        hourText.setText(String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
        minuteText.setText(String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)));
        secText.setText(String.valueOf(Calendar.getInstance().get(Calendar.SECOND)));
        day.setText(String.valueOf(Calendar.getInstance().get(Calendar.DATE)));
        day.setText(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)));
        day.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

        // create a broadcast receiver to change time
        tickReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK)==0){

                    hourText.setText(String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
                    minuteText.setText(String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)));
                    secText.setText(String.valueOf(Calendar.getInstance().get(Calendar.SECOND)));
                    day.setText(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));

                }
            }
        };

        registerReceiver(tickReceiver,new IntentFilter(Intent.ACTION_TIME_TICK));


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (tickReceiver!=null){
            unregisterReceiver(tickReceiver);
        }
    }
}