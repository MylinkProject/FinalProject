package com.example.nadaalbar.mylinks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewUpdateLink extends AppCompatActivity {
DatabaseHelper db;

    Button UpdateBTN;
    Link link;
   // Link updatedLink;
    EditText LinkEdit, descEdit;
    String  ilink,idesc,icat;
    int iID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_link);


        db= new DatabaseHelper(this);
        //updatedLink= new Link();

        //Getting Link info from pre. Act.
        Intent i= getIntent();
        Bundle extraBundle= i.getExtras();

        link = new Link();

        if(extraBundle!=null){


            Toast.makeText(getApplicationContext(),"There is a bundle",Toast.LENGTH_SHORT).show();
            Bundle bundle = getIntent().getExtras();

            String iIDs = bundle.getString("iID");
             ilink = bundle.getString("ilink");
             idesc = bundle.getString("idesc");
             icat = bundle.getString("icat");

            iID= Integer.parseInt(iIDs);
            link.setID(iID);
            link.setLink(ilink);
            link.setDescription(idesc);
            link.setCategory(icat);

        }else {
            Toast.makeText(getApplicationContext(),"NO bundle was found",Toast.LENGTH_SHORT).show();

        }


        LinkEdit   = (EditText)findViewById(R.id.vulinkId);
        descEdit= (EditText)findViewById(R.id.vuAddDescId);




        //The data is taken from oldLink object and added to the EditText Elements
        LinkEdit.setText(ilink, EditText.BufferType.EDITABLE);
        descEdit.setText(idesc, EditText.BufferType.EDITABLE);


        UpdateBTN = (Button) findViewById(R.id.update);

        UpdateBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                String ulink= LinkEdit.getText().toString();
                String uDesc= descEdit.getText().toString();


                link.setLink(ulink);
                link.setDescription(uDesc);


                // mydb.updateContactVoid(updatedCOntact,fname);


                //mydb.updateExec(updatedCOntact,fname);
                //mydb.close();

                // mydb.updateContactVoid(updatedCOntact,fname);
                db.updateLink(link);
                Toast.makeText(getApplicationContext(),"link was successfully updated ",Toast.LENGTH_SHORT).show();


            }

        });


    }
}
