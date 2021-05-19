package com.innodeed.event_handling;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    public Button btn;
    public TextView textView;
    public ImageView imageView;
    public EditText Name;
    public EditText Email;
    public EditText password;
    public CheckBox checkBox;
    public Switch aSwitch;
    public ConstraintLayout constraintLayout;
    @SuppressLint("ShowToast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);
        Name=findViewById(R.id.editTextTextPersonName);
        Email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        checkBox=findViewById(R.id.checkBox);
        aSwitch=findViewById(R.id.switch1);
        constraintLayout=findViewById(R.id.layout);
        btn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Clicked on Button", Toast.LENGTH_SHORT).show();
        });
        textView.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Clicked on TextView", Toast.LENGTH_SHORT).show();
        });
        imageView.setOnLongClickListener(v -> {
            Toast.makeText(MainActivity.this,"Clicked on ImageView" ,Toast.LENGTH_SHORT).show();
            return false;
        });
        checkBox=findViewById(R.id.checkBox);
         checkBox.setOnCheckedChangeListener(this);
         aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 switch (buttonView.getId()){
                     case R.id.switch1:
                         if (isChecked == true) {
                             Toast.makeText(MainActivity.this,"Checked" ,Toast.LENGTH_SHORT).show();
                             constraintLayout.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary_variant));
                         }else{
                             Toast.makeText( MainActivity.this,"Unchecked",Toast.LENGTH_SHORT).show();
                             constraintLayout.setBackgroundColor(getResources().getColor(R.color.lightYellow));
                         }
                         break;
                 }

             }
         });

    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          switch (buttonView.getId()){
              case R.id.checkBox:
                  if (isChecked == true) {
                      Toast.makeText(this,"Checked" ,Toast.LENGTH_SHORT).show();


                  }else{
                      Toast.makeText(this,"Unchecked",Toast.LENGTH_SHORT).show();
                  }
                  break;
                  }

          }

    }


