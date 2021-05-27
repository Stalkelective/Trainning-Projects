package com.innodeed.doodleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Doodle doodle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doodle = findViewById(R.id.doodle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Colorid:
            case R.id.clearId:
                doodle.clear();
                return true;
            case R.id.eraseId:
            case R.id.widthID:
            case R.id.saveId:
                doodle.saveToInternalStorage();
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}