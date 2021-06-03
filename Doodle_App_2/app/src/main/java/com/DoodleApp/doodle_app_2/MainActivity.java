package com.DoodleApp.doodle_app_2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawingView = findViewById(R.id.DrawingView);
        ImageView imageView = findViewById(R.id.imageView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // case R.id.brushID:
            case R.id.clearID:
                drawingView.clearImage();
                return true;
            case R.id.saveID:
                askStoragePermission();
                //  saveToGallery();
                // return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveImage() {
        View v = findViewById(R.id.DrawingView);
        int canvasWidth = v.getWidth();
        int canvasHeight = v.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        saveToGallery(bitmap);
        // imageView.setImageBitmap(bitmap);

    }

    private void saveToGallery(Bitmap bitmap) {

        // BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        // Bitmap bitmap = bitmapDrawable.getBitmap();
        FileOutputStream outputStream = null;
        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath() + "/MyPics@DoodleApp_2");
        dir.mkdir();

        @SuppressLint("DefaultLocale") String filename = String.format("%d.png", System.currentTimeMillis());
        File outFile = new File(dir, filename);
        try {
            outputStream = new FileOutputStream(outFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        try {
            assert outputStream != null;
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void askStoragePermission (){
        if ((ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)&&
                (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }else{
                    saveImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(String.valueOf(PackageManager.PERMISSION_GRANTED),"1");
                System.out.println(PackageManager.PERMISSION_GRANTED+" loop 1 execute");
                saveImage();
            } else {
                Toast.makeText(MainActivity.this, "Storage Permission Required", Toast.LENGTH_SHORT).show();
            }
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(String.valueOf(PackageManager.PERMISSION_GRANTED),"2");
                    System.out.println(PackageManager.PERMISSION_GRANTED+" loop 2 execute");
                    saveImage();
                } else {
                    Toast.makeText(MainActivity.this, "Storage Permission Required", Toast.LENGTH_SHORT).show();
                }
        }
    }
}