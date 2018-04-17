package com.example.nadaalbar.mylinks;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
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
            Toast.makeText(getApplicationContext(),"CatID "+CatID + "..."+LinksObj.get(0).getCategory(),Toast.LENGTH_LONG).show();
        }



        for (int i=0; i<LinksObj.size();i++){
           String idC= LinksObj.get(i).getCategory();
            if(idC.equalsIgnoreCase(c)){
                linksStr.add(LinksObj.get(i).getLink());

            }
        }

        ListAdapter mylistAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, linksStr);
        listview.setAdapter(mylistAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {


                Intent browser = new Intent (Intent.ACTION_VIEW, Uri.parse("http://"+linksStr.get(position)));
                startActivity(browser);
/*
                Intent intentBundle = new Intent (LinksList.this, ViewUpdateLink.class);
                Bundle bundle= new Bundle();
                //String msg=lastNames.get(position);
                int iID= LinksObj.get(position).getID();
                String ilink= LinksObj.get(position).getLink();
                String idesc= LinksObj.get(position).getDescription();
                String icat= LinksObj.get(position).getCategory();

                bundle.putString("iID",Integer.toString(iID));
                bundle.putString("ilink",ilink);
                bundle.putString("idesc",idesc);
                bundle.putString("icat",icat);//******

                intentBundle.putExtras(bundle);
                startActivity(intentBundle);
                Toast.makeText(getApplicationContext(),"ID in link object =  "+iID+ "ilink "+ilink+"  "+idesc+"  "+icat,Toast.LENGTH_LONG).show();

*/

              /* Intent i = new Intent(getApplicationContext(), LinksList.class);

                i.putExtra("link", (Link)arg0.getItemAtPosition(position));
                startActivity(i);

                Intent intent = new Intent(ViewContactsList.this, ViewUpdateContact.class);
                String message = lastNames.get(position);
                intent.("lastname", message);
                startActivity(intent);

               Intent i = new Intent(getApplicationContext(), ViewUpdateContact.class);

                i.putExtra("contact", (Contact)arg0.getItemAtPosition(position));
                startActivity(i);*/


            }
        });

    }
}
