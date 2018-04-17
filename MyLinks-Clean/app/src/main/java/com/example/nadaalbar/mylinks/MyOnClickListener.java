package com.example.nadaalbar.mylinks;

/**
 * Created by nadaalbar on 4/13/18.
 */


import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;

import static android.support.v4.content.ContextCompat.startActivity;


public class MyOnClickListener implements View.OnClickListener {
    private final int position;
    Intent myIntent;

    public MyOnClickListener(int position)
    {
        this.position = position;


    }

    public void onClick(View v)
    {

        //When a category is clicked view a listView of the links listed under that category

        myIntent = new Intent(v.getContext(), LinksUnderCategory.class);
        myIntent.putExtra("position",position);
        v.getContext().startActivity(myIntent);



    }
}
