package com.example.nadaalbar.mylinks;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ViewUpdateLink extends AppCompatActivity {
DatabaseHelper db;

    Button play,recorderBtn;
    Link link;
   // Link updatedLink;
    TextView LinkView, descView;
    String  ilink,idesc,icat;
    int iID;



//Play Voice note
private static MediaRecorder mRecorder;
    private static MediaPlayer mPlayer;

    private static String vFilePath;
    private static Button stopBtn;
    private static Button playBtb;
    private static Button recordBtn;

    private boolean isRecording = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update_link);


        db= new DatabaseHelper(this);
        //updatedLink= new Link();

        //Getting Link info from pre. Act.
        Intent i= getIntent();
        Bundle extraBundle= i.getExtras();
        LinkView   = (TextView)findViewById(R.id.vulinkId);
        descView= (TextView) findViewById(R.id.vuAddDescId);

        link = new Link();
        String iIDs="";
        String voiceNoteName="voicenote";
        if(extraBundle!=null){


           // Toast.makeText(getApplicationContext(),"There is a bundle",Toast.LENGTH_SHORT).show();
            Bundle bundle = getIntent().getExtras();

             iIDs = bundle.getString("iID");
             ilink = bundle.getString("ilink");
             idesc = bundle.getString("idesc");
             icat = bundle.getString("icat");

            iID= Integer.parseInt(iIDs);
            link.setID(iID);
            link.setLink(ilink);
            link.setDescription(idesc);
            link.setCategory(icat);


            LinkView.setText(ilink);
            descView.setText(idesc);

            voiceNoteName=voiceNoteName+iIDs;
           // Toast.makeText(getApplicationContext(),"iIDs: "+iIDs+"Link "+ilink,Toast.LENGTH_SHORT).show();
          //  Toast.makeText(getApplicationContext(),"VoiceNote Name "+voiceNoteName,Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(getApplicationContext(),"NO bundle was found",Toast.LENGTH_SHORT).show();

        }



/*

        if (!hasMicrophone())
        {
            stopBtn.setEnabled(false);
            playBtb.setEnabled(false);
           // recordBtn.setEnabled(false);
        } else {
            playBtb.setEnabled(false);
            stopBtn.setEnabled(false);
        }



        //  recordBtn=(Button) findViewById(R.id.startbtn);
        stopBtn = (Button) findViewById(R.id.vStop);
        playBtb=(Button) findViewById(R.id.vubtnVoiceNote);

        vFilePath =
                Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/"+voiceNoteName+".3gp";


        playBtb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                try{ playAudio(arg0);

                    Toast.makeText(getApplicationContext(),"Playing the voice note",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Exception in play btn",Toast.LENGTH_SHORT).show();
                }

            }


        });


        stopBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                try{ stopAudio(arg0);
                    Toast.makeText(getApplicationContext(),"Recorder stopped",Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Exception in stop",Toast.LENGTH_SHORT).show();
                }

            }


        });


        //The data is taken from oldLink object and added to the EditText Elements
       // LinkEdit.setText(ilink, EditText.BufferType.EDITABLE);
      //  descEdit.setText(idesc, EditText.BufferType.EDITABLE);

/*
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
               // db.updateLink(link);
              //  Toast.makeText(getApplicationContext(),"link was successfully updated ",Toast.LENGTH_SHORT).show();


            }

        });*/


    }



//**Recording Methods**//
    /*

    protected boolean hasMicrophone() {
        PackageManager pmanager = this.getPackageManager();
        return pmanager.hasSystemFeature(
                PackageManager.FEATURE_MICROPHONE);
    }

    public void recordAudio (View view) throws IOException
    {
        isRecording = true;
        stopBtn.setEnabled(true);
        playBtb.setEnabled(false);
        recordBtn.setEnabled(false);

        try {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(vFilePath);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{ mRecorder.start();}catch (Exception e){e.printStackTrace();  Toast.makeText(getApplicationContext(),"mRecorder.start() Exception",Toast.LENGTH_SHORT).show();}
    }//record audio end

    public void stopAudio (View view)
    {

        stopBtn.setEnabled(false);
        playBtb.setEnabled(true);

        if (isRecording)
        {
          //  recordBtn.setEnabled(false);
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            isRecording = false;
        } else {
            mPlayer.release();
            mPlayer = null;
           // recordBtn.setEnabled(true);
        }
    }//END of StopAudio


    public void playAudio (View view) throws IOException
    {
        playBtb.setEnabled(false);
        //recordBtn.setEnabled(false);
        stopBtn.setEnabled(true);

        mPlayer = new MediaPlayer();
        mPlayer.setDataSource(vFilePath);
        mPlayer.prepare();
        mPlayer.start();
    }


    private void initializeMediaRecord(){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mRecorder.setOutputFile(vFilePath);
    }

*/

}
