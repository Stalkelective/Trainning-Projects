package com.innodeed.multitouch_handling;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    TextView textView;
    TextView textView1;
    public int x;
    public int y;

    // private  int count=0;
    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.main_layout);
        textView = findViewById(R.id.display);
        textView1 = findViewById(R.id.display2);
        constraintLayout.setOnTouchListener((v, event) -> {
            // count=event.getPointerCount();
            int eventType = event.getActionMasked();
            switch (eventType) {
                case MotionEvent.ACTION_DOWN:
                    Log.i("TouchEvents", "DOWN" + " " + event.getPointerCount());
                    if (event.getPointerCount()==0){
                        textView.setText("No.of finger touching: 0"  );
                    }else {
                        textView.setText("No.of finger touching:" + event.getPointerCount());
                        x=event.getPointerCount();
                    }

                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.i("TouchEvents", "POINTER DOWN" + " " + event.getPointerCount());
                    if (event.getPointerCount()==0){
                        textView.setText("No.of finger touching: 0"  );
                    }else {
                        textView.setText("No.of finger touching:" + event.getPointerCount());
                        x=event.getPointerCount();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.i("TouchEvents", "MOVE" + " " + event.getPointerCount());
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    y=event.getPointerCount();
                    textView1.setText("No. of finger removed:"+" "+( x-(y-1)));
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("TouchEvents", "UP" + " " + event.getPointerCount());
                    textView1.setText("No. of finger removed:"+" "+(event.getPointerCount()+( x-(y-1))));
                    break;

            }
            return true;
        });
    }
}