package com.innodeed.menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fruit:
            case R.id.movie:
                return true;
            case R.id.appbaricon:
                Toast.makeText(this,"APPLE ICON ITEM",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.apple:
                Toast.makeText(this, "selected Apple", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mango:
                Toast.makeText(this, "selected Mango", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.grape:
                Toast.makeText(this, "selected Grapes", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kiwi:
                Toast.makeText(this, "selected Kiwi", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.litchi:
                Toast.makeText(this, "selected Litchi", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.tenet:
                Toast.makeText(this, "selected Tenet", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.just:
                Toast.makeText(this, "selected Justice League", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.lord:
                Toast.makeText(this, "selected LORD OF THE RINGS", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.eurotrip:
                Toast.makeText(this, "selected Euro Trip", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}