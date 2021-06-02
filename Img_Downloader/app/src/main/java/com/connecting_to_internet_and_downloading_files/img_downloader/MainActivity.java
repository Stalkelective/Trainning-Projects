package com.connecting_to_internet_and_downloading_files.img_downloader;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    EditText editText;
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);

        checkConnection();

    }

    public void checkConnection() {

        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null) {

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                networkInfo.isConnected();
                Toast.makeText(MainActivity.this, "Wifi Connected", Toast.LENGTH_LONG).show();
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                networkInfo.isConnected();
                Toast.makeText(MainActivity.this, "MobileData Connected", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(MainActivity.this, "No Internet Connectivity", Toast.LENGTH_LONG).show();
        }

    }

    public void btnAction(View view) {
        url=editText.getText().toString();
        imageDownloader imageDownloader = new imageDownloader();
        Bitmap bitmapImage;
        try {
            if(url.length()==0){
            bitmapImage = imageDownloader.execute("https://freepngimg.com/thumb/cartoon/36530-9-cartoon-hd.png").get();
            System.out.println(url);
            }else {
                bitmapImage = imageDownloader.execute(url).get();
                System.out.println(url);
            }
            imageView.setImageBitmap(bitmapImage);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public class imageDownloader extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url;
                url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}