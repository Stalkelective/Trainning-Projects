package com.innodeed.firstgridview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     GridView grid;

    String[] name={"jalabi","dosa","pizza","chessepacks","doughnuts","frenchfries",
             "samosa","vadapav","burger","tacos"};
    int[] img={R.drawable.jalabi,R.drawable.dosa,R.drawable.pizza,R.drawable.cheeseypizza,
            R.drawable.doughnut,R.drawable.frenchfries,R.drawable.samosa,R.drawable.vadapav,
            R.drawable.burger, R.drawable.tacos};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid=findViewById(R.id.grid);
        gridAdapter adapter=new gridAdapter(this,name,img);
        grid.setAdapter(adapter);






    }
}