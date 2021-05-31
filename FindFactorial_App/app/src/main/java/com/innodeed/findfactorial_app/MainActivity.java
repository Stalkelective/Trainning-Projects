package com.innodeed.findfactorial_app;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    Button button;
    TextView textView;
    int result;
    int num1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.first);
        textView = findViewById(R.id.result);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            num1 =  Integer.parseInt(editText1.getText().toString());
            Factorial factorial = new Factorial();
            factorial.execute();
        });
    }


    @SuppressLint("StaticFieldLeak")
    public class Factorial extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String s= integer.toString();
            textView.setText(s);
        }

        protected Integer doInBackground(Integer... params) {
            int fact=1;
            for (int i=1;i<=num1;i++){
                    fact= fact*i;
            }
            result=fact;
          //  m=Integer.parseInt(String.valueOf(result));
            System.out.println(result);
            return result;
        }
    }
}