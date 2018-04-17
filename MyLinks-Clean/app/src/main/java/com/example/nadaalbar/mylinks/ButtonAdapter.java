package com.example.nadaalbar.mylinks;
/**
 * Created by nadaalbar on 4/13/18.
 */


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;


public class ButtonAdapter extends BaseAdapter {


    private Context mContext;
    public String[] filenames; /*= {
            "News",
            "Food",
            "Education",
            "Social Network",
            "Shopping",
            "Sports"
    };*/



    public void setFileNames(String [] names){
       filenames= names;
    }



    // Gets the context so it can be used later
    public ButtonAdapter(Context c) {
        mContext = c;
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return filenames.length;
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,
                        View convertView, ViewGroup parent) {



        Button btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(180, 180));
            btn.setPadding(8, 55, 8, 55);
        }
        else {
            btn = (Button) convertView;
        }
        // Set the onclicklistener so that pressing the button fires an event
        // We will need to implement this onclicklistner.
        btn.setOnClickListener(new MyOnClickListener(position));
        btn.setText(filenames[position]);
        btn.setHeight(45);
        btn.setMinimumHeight(45);
        btn.setBackgroundColor(btn.getContext().getResources().getColor(R.color.orange));
        // filenames is an array of strings
        btn.setTextColor(btn.getContext().getResources().getColor(R.color.White));
        // btn.setBackgroundResource(R.drawable.button);
        btn.setId(position);

        return btn;
    }
}