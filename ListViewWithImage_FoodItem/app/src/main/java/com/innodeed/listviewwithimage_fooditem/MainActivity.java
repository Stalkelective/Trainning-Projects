package com.innodeed.listviewwithimage_fooditem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
ListView lvProgram;
String[] programName={"jalabi","dosa","pizza","chessepacks","doughnuts","frenchfries",
        "samosa","vadapav","burger","tacos"};
String[] programDescription ={"20","40","90","50","25","30",
        "20","20","30","30"};
int[] programImages={R.drawable.jalabi,R.drawable.dosa,R.drawable.pizza,R.drawable.chesssepacks,
                      R.drawable.doughnut,R.drawable.frenchfries,R.drawable.samosa,R.drawable.vadapav,
                       R.drawable.burger, R.drawable.tacos};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvProgram =findViewById(R.id.lvProgram);
        ProgramAdapter programAdapter = new ProgramAdapter(this,programName,programImages,programDescription);
        lvProgram.setAdapter(programAdapter);

    }
}