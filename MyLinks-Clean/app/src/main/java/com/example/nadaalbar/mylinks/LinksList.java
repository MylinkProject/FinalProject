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
import android.widget.TextView;
import android.graphics.Color;

import android.view.ViewGroup;

import android.widget.Toast;

import java.util.ArrayList;

public class LinksList extends AppCompatActivity {
DatabaseHelper db;
    ArrayList<String>linksStr =new ArrayList<>();
    ArrayList<Link>LinksObj;
    int CatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links_list);

        //Initializing Variables
        LinksObj =new ArrayList<Link>();

        db = new DatabaseHelper(this);
        ListView listview = (ListView) findViewById(R.id.listView);


        //if Bundle is empty else if bundle is loaded {grab the links under a category}
      //  Intent mIntent = getIntent();
        LinksObj  = db.getAllLinksObjects();

        //if(mIntent.hasExtra("position")){
          //  CatID  = mIntent.getIntExtra("position",0);
            //CatID= CatID+1;//match the position with IDs
          /*  String c=Integer.toString(CatID);
            for (int i=0;i<LinksObj.size();i++){
               String idC= LinksObj.get(i).getCategory();

               if(idC==c){
                   linksStr.add(LinksObj.get(i).getLink());

               }
            }

            Toast.makeText(getApplicationContext(),"CatID "+CatID + "..."+LinksObj.get(0).getCategory(),Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(),"****.."+LinksObj.get(0).getLink(),Toast.LENGTH_LONG).show();
        //}else{

           // LinksObj= db.getAllLinksObjects();
            for (int i=0; i<LinksObj.size();i++){
                linksStr.add(LinksObj.get(i).getLink());
            }
        }
*/



        LinksObj= db.getAllLinksObjects();
        for (int i=0; i<LinksObj.size();i++){
            linksStr.add(LinksObj.get(i).getLink());
        }

        ListAdapter mylistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, linksStr){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);


                textView.setTextColor(Color.WHITE);

                return view;
            }
        };
        listview.setAdapter(mylistAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {


               /* Intent browser = new Intent (Intent.ACTION_VIEW, Uri.parse("http://"+linksStr.get(position)));
                startActivity(browser);*/



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
               // Toast.makeText(getApplicationContext(),"ID in link object =  "+iID+ "ilink "+ilink+"  "+idesc+"  "+icat,Toast.LENGTH_LONG).show();



            }
        });

    }
}
