package com.innodeed.my_factorial_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;
    double num,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.result);
        editText=findViewById(R.id.editTextTextPersonName);
        button=findViewById(R.id.button);
        button.setOnClickListener(v -> {
            num=Double.parseDouble(editText.getText().toString());
           Factorial factorial= new Factorial();
           factorial.execute();
        });
    }


    public class Factorial extends AsyncTask<Double,Double,Double>{

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);
            String res= aDouble.toString();
            textView.setText(res);
        }

        @Override
        protected Double doInBackground(Double... params) {
            double fact=1;
            double i;
            for(i=1;i<=num;i++){
                fact=fact*i;
            }
            result=fact;
            return result;
        }
    }
}