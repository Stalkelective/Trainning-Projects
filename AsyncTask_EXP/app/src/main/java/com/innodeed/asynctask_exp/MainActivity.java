package com.innodeed.asynctask_exp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText num1,num2;
    Button btn;
    TextView res;
    String strUrl,result ;
    int i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1=findViewById(R.id.firstnum);
        num2=findViewById(R.id.secondnum);
        res=findViewById(R.id.result);

        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 i= Integer.parseInt(num1.getText().toString());
                 j= Integer.parseInt(num2.getText().toString());
                 strUrl="http://www.google.com";
                 MultiplyTask multiplyTask=new MultiplyTask();
                 multiplyTask.execute();

            }
        });
    }
      @SuppressLint("StaticFieldLeak")
      public class MultiplyTask extends AsyncTask<String,String,String>{
          @Override
          protected void onPreExecute() {
              super.onPreExecute();
          }


          @Override
          protected void onPostExecute(String s) {
             // super.onPostExecute(s);
              Toast.makeText(MainActivity.this,"The output is:"+s,Toast.LENGTH_LONG).show();

          }


          @Override
          protected String doInBackground(String... params) {

              try {
                  URL url=new URL(strUrl);
                  HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                  connection.setRequestMethod("GET");
                  connection.connect();

                  BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  String value=bufferedReader.readLine();
                  System.out.println("result is:"+value);
                  result=value;
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return result;
          }
      }
}