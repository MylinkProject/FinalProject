package com.example.nadaalbar.mylinks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CategoriesList extends AppCompatActivity {
    ArrayList<Category> categoriesObjs;
    DatabaseHelper db;
    String[] categorisNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);
        db= new DatabaseHelper(this);

        //Categories list


        //Get the list of Cat. from db into a grid View ?
        categoriesObjs= new ArrayList<Category>();
        categoriesObjs= db.getAllCategoriesObj();

        categorisNames= new String [categoriesObjs.size()];
        for (int i=0 ; i<categoriesObjs.size();i++){
            categorisNames[i] =categoriesObjs.get(i).getCategoryName();
        }

        //Categories list
        GridView gridview = (GridView) findViewById(R.id.grid_View);
        ButtonAdapter btnAdapt=  new ButtonAdapter(this);
        btnAdapt.setFileNames(categorisNames);
        gridview.setAdapter(btnAdapt);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CategoriesList.this, "Position " + position,
                        Toast.LENGTH_LONG).show();



            }
        });

    }
}
