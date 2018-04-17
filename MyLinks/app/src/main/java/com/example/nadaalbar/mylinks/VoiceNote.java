package com.example.nadaalbar.mylinks;

/**
 * Created by nadaalbar on 4/11/18.
 */

public class VoiceNote {
    String Id;
    String Path;


   public VoiceNote(){

    }

    public void setID(String id){
       Id= id;
    }

    public void setFileName(String path){
        Path= path;
    }

    public String getId(){
        return Id;
    }

    public String getFileName(){
        return Path;
    }


}
