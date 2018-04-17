package com.example.nadaalbar.mylinks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by nadaalbar on 4/2/18.
 */

public class Link implements Parcelable {


    String Link;
    String Description;
    int ID;
    String Category;
    String tagsString;
    String Path;
    ArrayList<String>Tags= new ArrayList<>();

//Empty Constructor
    public Link(){

    }//end

    public Link (int id , String link, String desc, String cat){// add other values later !
        ID= id;
        Link=link;
        Description=desc;
        Category=cat;
    }


//When search use contain/like function in SQL ?!

//Set Methods.....

    public void setLink(String link){
        Link= link;
    }
    public void setDescription(String description){
        Description= description;
    }
    public void setID(int id){
        ID= id;
    }
    public void setCategory(String category){
        Category= category;
    }


    /*
    public void setTags(ArrayList<String> tags){
        Tags=Tags;

    }

    //set tags as strings separated by ,
    public void setTagsAsString(String tagsS){
        tagsString=tagsS;

    }
     public void setPath(String path){
        Path= path;
     }

*/
//Get Methods.....

    public String getLink(){
        return Link;
    }

    public int getID(){
        return ID;
    }

    public String getDescription(){
        return Description;
    }


    public String getCategory(){
        return Category;
    }

/*
    public ArrayList<String> getTags(){
        return Tags;
    }


    //get tags as String separated by comma
    public String getTagsAsString(){
        return tagsString;

    }

    public String getPath(){
        return Path;
    }
*/


//--------Parcel........

    public Link(Parcel in) {
        this.ID = in.readInt();
        this.Link = in.readString();

        this.Description=in.readString();
        this.Category=in.readString();

    }
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Link);
        dest.writeInt(ID);
        dest.writeString(Description);
        dest.writeString(Category);

    }

    public static final Parcelable.Creator<Link> CREATOR = new Parcelable.Creator<Link>() {

        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        public Link[] newArray(int size) {
            return new Link[size];
        }
    };


}
