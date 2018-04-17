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
        // Preform a function based on the position
        //someFunction(this.position)

        //send the position to the LinksList  activity

        myIntent = new Intent(v.getContext(), LinksUnderCategory.class);
        myIntent.putExtra("position",position);
        v.getContext().startActivity(myIntent);



       /* Intent intent=new Intent(v.getContext(),MyOnClickListener.class);
        intent.putExtra("position", Integer.toString(position));
        v.getContext().startActivity(intent);

        myIntent = new Intent(v.getContext(), LinksList.class);
        Bundle bundle= new Bundle();
        //String msg=lastNames.get(position);

        myIntent.putExtras(bundle);
        startActivity(myIntent);*/
        Toast.makeText(v.getContext(),"position "+ position,Toast.LENGTH_SHORT).show();
    }
}
