package com.innodeed.how_to_save_image_in_external_studio;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;

   // OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        button=findViewById(R.id.btn);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        button.setOnClickListener(v -> {
           /* BitmapDrawable drawable=(BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap= drawable.getBitmap();

            File filepath= Environment.getExternalStorageDirectory();
            File dir=new File(filepath.getAbsolutePath()+"/Demo/");
            dir.mkdir();
            File file=new File(dir,System.currentTimeMillis()+".jpg");
            try {
                outputStream=new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            Toast.makeText(getApplicationContext(),"Image Saved To Internal",Toast.LENGTH_SHORT).show();
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            saveToGallery();

        });

    }
    private void saveToGallery(){

        BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();
        FileOutputStream outputStream=null;
        File file = Environment.getExternalStorageDirectory();
        File dir= new File(file.getAbsolutePath()+"/MyPics");
        dir.mkdir();

        @SuppressLint("DefaultLocale") String filename =String.format("%d.png",System.currentTimeMillis());
        File outFile=new File(dir,filename);
        try {
            outputStream=new FileOutputStream(outFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        try {
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}