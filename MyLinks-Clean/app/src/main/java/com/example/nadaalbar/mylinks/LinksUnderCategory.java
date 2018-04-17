package com.example.nadaalbar.mylinks;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class LinksUnderCategory extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<String> linksStr ;
    ArrayList<Link>LinksObj;
    int CatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links_under_category);
        db = new DatabaseHelper(this);
        ListView listview = (ListView) findViewById(R.id.listView2);
        //Initializing Variables
        LinksObj =new ArrayList<Link>();
        LinksObj  = db.getAllLinksObjects();
        linksStr =new ArrayList<>();

        //if Bundle is empty else if bundle is loaded {grab the links under a category}
        String c="C......";
        Intent mIntent = getIntent();
        if(mIntent.hasExtra("position")){
            CatID  = mIntent.getIntExtra("position",0);
            c= Integer.toString(CatID);
          //  Toast.makeText(getApplicationContext(),"CatID "+CatID + "..."+LinksObj.get(0).getCategory(),Toast.LENGTH_LONG).show();
        }



        for (int i=0; i<LinksObj.size();i++){
           String idC= LinksObj.get(i).getCategory();
            if(idC.equalsIgnoreCase(c)){
                linksStr.add(LinksObj.get(i).getLink());

            }
        }

        ListAdapter mylistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, linksStr){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };
        listview.setAdapter(mylistAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {


                Intent browser = new Intent (Intent.ACTION_VIEW, Uri.parse("http://"+linksStr.get(position)));
                startActivity(browser);


             


            }
        });

    }
}
