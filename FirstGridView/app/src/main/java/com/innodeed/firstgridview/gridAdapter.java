package com.innodeed.firstgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class gridAdapter extends ArrayAdapter<String> {
    Context context;
     int[] img;
     String[] name;

    public gridAdapter(Context context, String[] name, int[] img) {
        super(context,R.layout.gridlayout,R.id.names,name);
        this.context =context;
        this.img=img;
        this.name=name;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleItem = convertView;
        ProgramViewHolder holder= null;
        if(singleItem==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem =layoutInflater.inflate(R.layout.gridlayout,parent,false);/* single_Item here creates
                                                                                              a layout xml file single_item.xml which
                                                                                               will carry the data that will be displayed
                                                                                                 in list view */
            holder=new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else{
            holder= (ProgramViewHolder) singleItem.getTag();
        }
        holder.itemImage.setImageResource(img[position]);
        holder.programTitle.setText(name[position]);

        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"YOU CLICKED:"+ name[position],Toast.LENGTH_SHORT).show();
            }
        });
        return singleItem;
    }
}
