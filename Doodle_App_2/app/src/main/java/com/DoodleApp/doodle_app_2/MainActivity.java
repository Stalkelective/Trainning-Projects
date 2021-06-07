package com.DoodleApp.doodle_app_2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawingView = findViewById(R.id.DrawingView);
       // ImageView imageView = findViewById(R.id.imageView);
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
                return true;
            case R.id.share:
               // sharingImageIntentFire();
                share();
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

        @SuppressLint("DefaultLocale") String filename = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, filename);
        try {
            outputStream = new FileOutputStream(outFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
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
                (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_MEDIA_LOCATION)!=PackageManager.PERMISSION_GRANTED)        )
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
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
            case 3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(String.valueOf(PackageManager.PERMISSION_GRANTED),"3");
                    System.out.println(PackageManager.PERMISSION_GRANTED+" loop 3 execute");
                    saveImage();
                } else {
                    Toast.makeText(MainActivity.this, "MEDIA LOCATION ACCESSED", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    @SuppressLint("SetWorldReadable")
    private void share(){
        Bitmap bitmap=getBitmapfromView(drawingView);
        @SuppressLint("DefaultLocale") String filename = String.format("%d.jpg", System.currentTimeMillis());
        try {
            File file=new File(this.getExternalCacheDir(),filename);
            FileOutputStream fout =new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fout);
            fout.flush();
            fout.close();
            file.setReadable(true,false);
            file.setWritable(true,false);
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
            intent.setType("image/jpg");
            startActivity(Intent.createChooser(intent,"share by"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @SuppressLint("ResourceAsColor")
    private Bitmap getBitmapfromView(View view){
        Bitmap returnBitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(returnBitmap);
        Drawable bgDrawable = view.getBackground();

        if(bgDrawable !=null){
            bgDrawable.draw(canvas);
        }else{
            canvas.drawColor(android.R.color.white);
        }
        view.draw(canvas);

        return returnBitmap;
    }

    private void sharingImageIntentFire(){
       /* View v = findViewById(R.id.DrawingView);
        int canvasWidth = v.getWidth();
        int canvasHeight = v.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        Uri uriToImage = Uri.parse(String.valueOf(bitmap));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM,uriToImage);
        shareIntent.setType("image/jpg");

        Intent sentIntent= Intent.createChooser(shareIntent,"SENDING DRAWING");
        startActivity(sentIntent);*/

        @SuppressLint("DefaultLocale") String filename = String.format("%d.jpg", System.currentTimeMillis());
        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath() + "/MyPics@DoodleApp_2");
        File f= new File( file.getAbsolutePath() + "/MyPics@DoodleApp_2", filename);
        File path =new File("Home/Internal shared storage/MyPics@DoodleApp_2/"+filename);
        Uri uriToImage=Uri.fromFile(path);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        // shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.putExtra(Intent.EXTRA_STREAM,uriToImage);
        shareIntent.setType("image/jpg");
        // startActivity(shareIntent);
        Intent sentIntent= Intent.createChooser(shareIntent,"SENDING DRAWING");
        startActivity(sentIntent);
    }


}