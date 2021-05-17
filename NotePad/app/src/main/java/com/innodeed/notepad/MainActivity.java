package com.innodeed.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button ;
    TextView textView ;
    EditText editText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button = findViewById(R.id.button);
         textView = findViewById(R.id.textView);
         editText = findViewById(R.id.editText);

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String msg= editText.getText().toString();// storing the editText input in msg in form of string
// WRITE IN SharedPreference
                 SharedPreferences shrd = getSharedPreferences("demo",MODE_PRIVATE) ;
                 // name of this SharedPreference in demo and can only be used gy this app
                 SharedPreferences.Editor editor = shrd.edit();
                 // made an editor
                 editor.putString("str1",msg); // passed the data stored in msg to str
                 editor.apply();
                 editor.clear();// additional function to play with
                 textView.setText(msg);// dynamically displaying the value to editText on textView

             }
         });
// READ FROM SharedPreference
         //  get the value of SharedPreferences back
        SharedPreferences getShared = getSharedPreferences("demo",MODE_PRIVATE);
       String value=getShared.getString("str","Save A NOTE");
       // storing the value from str and displaying default value
       textView.setText(value); // passing the data stored in value in i.e str to textView for display

    }
}