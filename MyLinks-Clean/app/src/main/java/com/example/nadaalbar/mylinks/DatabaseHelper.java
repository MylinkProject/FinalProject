package com.example.nadaalbar.mylinks;

/**
 * Created by nadaalbar on 4/2/18.
 */


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Declaring  vars
    public static final String DATABASE_NAME="Links.db";
    public static final String TABLE_Links="Links_table";

    public static final String COL_1="ID";
    public static final String COL_2="Link";
    public static final String COL_3="description";
    public static final String COL_4="category";

//---------------Categories_table
    public static final String TABLE_CATEGORIES="Categories_table";
    public static final String COLC_1="ID";
    public static final String COLC_2="Category_Title";
    public static final String COLC_3="description";

//--------------VoiceNotes Table

    public static final String   TABLE_VoiceNotes="VoiceNotes_Table";
    public static final String  vCol_1= "ID";
    public static final String  vCol_2= "Path";

//*************************************************

    //   public static final String COL_5="tags";

    //Constructor--
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_Links+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Link TEXT, description TEXT, category TEXT);");
        db.execSQL("create table "+ TABLE_CATEGORIES+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Category_Title TEXT, description TEXT);");
        db.execSQL("create table "+ TABLE_VoiceNotes+" (ID TEXT , Path TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists "+TABLE_Links);
        db.execSQL("Drop Table if exists "+TABLE_CATEGORIES);
        db.execSQL("Drop Table if exists "+TABLE_VoiceNotes);
        onCreate(db);
    }



    //-------------- Links
    public void addLink(Link link) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
       //Col_1 is ID
        cv.put(COL_2,link.getLink() );
        cv.put(COL_3,link.getDescription() );
        cv.put(COL_4,link.getCategory() );
       // cv.put(COL_5,link.getTags());

        // Inserting Row
        db.insert(TABLE_Links, null, cv);
        db.close(); // Closing database connection

       /* getInsetedID(link.getLink());
        String id ="temp";
        return id;*/

    }

    /*
    // Getting single order
   public Link getLink(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Links, new String[] { COL_1,
                        COL_2, COL_3,COL_4 }, COL_1 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Link link = new Link(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),cursor.getString(3));
        // return order
        return link;
    }
*/


    public int getLinkID(String linkstr) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        int id =-1;
        try {
            String query="SELECT id FROM  Links_table WHERE Link= ?";
            cursor = db.rawQuery(query, new String[] {String.valueOf(linkstr)});
           // cursor = db.rawQuery("SELECT ID FROM  Links_table WHERE Link=?", new String[] {linkstr + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                id = cursor.getInt(cursor.getColumnIndex("ID"));
            }
            return id;
        }finally {
            cursor.close();
        }
    }



/*
    public void addLink(Link link) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //Col_1 is ID
        cv.put(COL_2,link.getLink() );
        cv.put(COL_3,link.getDescription() );
        cv.put(COL_4,link.getCategory() );
        // cv.put(COL_5,link.getTags());

        // Inserting Row
        db.insert(TABLE_Links, null, cv);
        db.close(); // Closing database connection

    }*/


    public ArrayList<Link> getAllLinksObjects() {
        ArrayList<Link> LinksObjList = new ArrayList<Link>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Links+ " ORDER BY link";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Link link = new Link();
                link.setID(cursor.getInt(0));
                link.setLink(cursor.getString(1));
                link.setDescription(cursor.getString(2));
                link.setCategory(cursor.getString(3));


                // Adding order to list
                LinksObjList.add(link);
            } while (cursor.moveToNext());
        }

        // return order list
        return LinksObjList;
    }


    // Getting All categories objects
    public ArrayList<Category> getAllCategoriesObj() {
        ArrayList<Category> CatObjList = new ArrayList<Category>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES+ " ORDER BY "+COLC_2;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category cat = new Category();
                cat.setID(Integer.parseInt(cursor.getString(0)));
                cat.setCategoryName(cursor.getString(1));
                cat.setDescription(cursor.getString(2));


                // Adding order to list
                CatObjList.add(cat);
            } while (cursor.moveToNext());
        }

        // return categories list
        return CatObjList;
    }


    //Get all links under a category..when clicked open in a browser ?

   /* public ArrayList<String> getLinksUnderCat( String id){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<String> links = new ArrayList<String>();
        // Select All Query
      //  String selectQuery = "SELECT  * FROM " + TABLE_Links+ " WHERE "+COL_4+" = " +id+ " ;";
        String query="SELECT "+ COL_2+" FROM  Links_table WHERE "+COL_4+"= ?";
        Cursor  cursor = db.rawQuery(query, new String[] {id});

       Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                // Adding links as strings to list
                links.add(cursor.getString(1));
                System.out.println("DatabaseHelper");
                System.out.println(cursor.getString(0));
                System.out.println(cursor.getString(1));
                System.out.println(cursor.getString(2));
            } while (cursor.moveToNext());
        }


        // return links under one category
        return links;

    }

*/

    // Getting All links as Strings
    public ArrayList<String> getAllLinks() {
        ArrayList<String> LinksList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  Link FROM " + TABLE_Links+ " ORDER BY link";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                LinksList.add(cursor.getString(1));




            } while (cursor.moveToNext());
        }

        // return order list
        return LinksList;
    }


    public void addCategory(Category category) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //Col_1 is ID



        cv.put(COLC_2,category.getCategoryName());
        cv.put(COLC_3,category.getDescription());



        // Inserting Row
        db.insert(TABLE_CATEGORIES, null, cv);
        db.close(); // Closing database connection

    }









    // Updating single order
    public void updateLink(Link link) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, link.getLink());
        values.put(COL_3, link.getDescription());
        values.put(COL_3, link.getCategory());

        // updating row
         db.update(TABLE_Links, values, COL_1 + " = ?",
                new String[] { String.valueOf(link.getID()) });

         db.close();
    }



    //get links list (linksOnly)? to be Added to a ListView
    //Update the links attributes..
    //


    //-----VoiceNotes

    public void addVoiceNote(VoiceNote vc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //Col_1 is ID , where ID of voicenote is the same as links

        cv.put(vCol_1,vc.getId());
        cv.put(vCol_2,vc.getFileName());

        // Inserting Row
        db.insert(TABLE_VoiceNotes, null, cv);
        db.close(); // Closing database connection

    }




    //---------Get All Voice notes from DB
    public ArrayList<VoiceNote> getAllVoiceNote() {
        ArrayList<VoiceNote> VoiceNotesList = new ArrayList<VoiceNote>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VoiceNotes+ " ORDER BY "+vCol_1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                VoiceNote vc = new VoiceNote();
                vc.setID(cursor.getString(0));
                vc.setFileName(cursor.getString(1));


                // Adding voiceNote to list
                VoiceNotesList.add(vc);
            } while (cursor.moveToNext());
        }

        // return order list
        return VoiceNotesList;
    }




}
