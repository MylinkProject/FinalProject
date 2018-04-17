package com.example.nadaalbar.mylinks;

/**
 * Created by nadaalbar on 4/8/18.
 */

public class Category {

    int ID;
    String CategoryName;
    String description;

    public Category(){

    }


    //-----------SET.........

    public void setID(int id){
        ID=id;
    }
    public void setCategoryName(String CN){
        CategoryName=CN;
    }

    public void setDescription(String des){
        description=des;
    }


    //----------Get.....
    public int getID(){
        return ID;

    }
    public String getCategoryName(){
        return CategoryName;

    }

    public String getDescription(){
        return description;

    }


}
