package com.example.nadaalbar.mylinks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategory extends AppCompatActivity {
    EditText catTitle,catDesc;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);


         Button saveCat= (Button)findViewById(R.id.btnSaveCatId);


         catTitle= (EditText) findViewById (R.id.CatTitleId);
         catDesc= (EditText) findViewById (R.id.edCatDescId);

         db= new DatabaseHelper(this);


        saveCat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

               Category category= new Category();

                category.setCategoryName(catTitle.getText().toString());
                category.setDescription(catDesc.getText().toString());
                db.addCategory(category);


                Toast.makeText(getApplicationContext()," "+category.getCategoryName()+" was sucessfully saved",Toast.LENGTH_SHORT).show();


            }

        });



    }
}
