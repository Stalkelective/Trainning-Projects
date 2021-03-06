package com.querying_system_data_using_contentresolver.queryingsystemdata;

import android.os.Bundle;
import android.widget.GridView;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class PhotosActivity extends AppCompatActivity {
    int int_position;
    private GridView gridView;
    GridViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gv_folder);
        int_position = getIntent().getIntExtra("value", 0);
        adapter = new GridViewAdapter(this, MainActivity.al_images, int_position);
        gridView.setAdapter(adapter);
    }
}
