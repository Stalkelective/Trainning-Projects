package com.innodeed.firstgridview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//public class ProgramViewHolder {
//}
public class ProgramViewHolder {
    ImageView itemImage;
    TextView programTitle;

    ProgramViewHolder(View v){

        itemImage =v.findViewById(R.id.imgs);
        programTitle =v.findViewById(R.id.names);

    }

}

