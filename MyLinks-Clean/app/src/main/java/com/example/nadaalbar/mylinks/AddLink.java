package com.example.nadaalbar.mylinks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Button;

import android.widget.Toast;
//**Spinner
import java.util.ArrayList;
import java.util.List;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

/* Recorder Classes*/
import java.io.IOException;
import android.media.MediaRecorder;
import android.os.Environment;
import android.media.MediaPlayer;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;

import android.os.Build;

import android.Manifest;




public class AddLink extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Add voice note code...
    // Add toast " data was suc. added.....

    EditText  linkET, descET, catlink;
    Button savebtn, recorderBtn;
    DatabaseHelper db;
    Link link;//The link to be saved into the database
    String voiceFilename ;
    int SpinnerPosition=-1;

    // Spinner element
    Spinner spinner;

    /*Recorder Variables*/

    private static MediaRecorder mRecorder;
    private static MediaPlayer mPlayer;

    private static String FilePath;
    private static Button stopBtn;
    private static Button playBtb;
    private static Button recordBtn;

    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_link);


        db= new DatabaseHelper(this);

        //---Spinner Code

        // add act.> implement a spinner here !
        //bring Cat.s from Db --> add to Spinner

        spinner = (Spinner) findViewById(R.id.spinner2);

        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();
        //EditText

         linkET= (EditText) findViewById(R.id.linkId);
         descET= (EditText) findViewById(R.id.AddDescId);
        // catlink= (EditText) findViewById(R.id.categorylink);

         savebtn= (Button)findViewById(R.id.saveId);


        //Saving a link into DB....
        savebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                if(SpinnerPosition!=-1) {
                    String tempLink = linkET.getText().toString();
                    String tempdesc = descET.getText().toString();
                   // String tempCat = catlink.getText().toString();
                    link = new Link();
                    link.setLink(tempLink);
                    link.setDescription(tempdesc);
                    link.setCategory(Integer.toString(SpinnerPosition));
                    db.addLink(link);

                    //Toast Here.....
                    Toast.makeText(getApplicationContext(), " " + link.getLink() + " was sucessfully saved", Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(getApplicationContext(), " " + SpinnerPosition + "  SpinnerPosition", Toast.LENGTH_SHORT).show();
                 //   Toast.makeText(getApplicationContext(), " " + link.getCategory() + "  categoryInTable", Toast.LENGTH_SHORT).show();


                    //get link id to use it in saving the voiceNote

                    int idlink = db.getLinkID(link.getLink());


                    //add record id & path to record table

                    VoiceNote vc = new VoiceNote();
                    vc.setID(Integer.toString(idlink));
                    voiceFilename = "voicenote";
                    voiceFilename = voiceFilename + idlink;
                    vc.setFileName(voiceFilename);//**********
                    //add voiceNote object to database
                    db.addVoiceNote(vc);
                //    Toast.makeText(getApplicationContext(), voiceFilename + " added to db", Toast.LENGTH_SHORT).show();
                }else if(SpinnerPosition==-1){Toast.makeText(getApplicationContext(),  "Please select a category ", Toast.LENGTH_SHORT).show();}

            }

        });







        //Recorder code
        recorderBtn= (Button)findViewById(R.id.stopbtn);


        //**Recorder Code**//





/*
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_MICROPHONE);

        }

*/
        recordBtn=(Button) findViewById(R.id.startbtn);
        stopBtn = (Button) findViewById(R.id.stopbtn);
        playBtb=(Button) findViewById(R.id.playbtn);



        if (!hasMicrophone())
        {
            stopBtn.setEnabled(false);
            playBtb.setEnabled(false);
            recordBtn.setEnabled(false);
        } else {
            playBtb.setEnabled(false);
            stopBtn.setEnabled(false);
        }




        FilePath =
                Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/"+voiceFilename+".3gp";



        recordBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                try{
                recordAudio(arg0);
                    Toast.makeText(getApplicationContext(),"Recording audio",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Exception in record btn",Toast.LENGTH_SHORT).show();
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


        playBtb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

               try{ playAudio(arg0);
                   Toast.makeText(getApplicationContext(),"Playing the voice note",Toast.LENGTH_SHORT).show();
               }catch (Exception e){

                   Toast.makeText(getApplicationContext(),"Exception in play btn",Toast.LENGTH_SHORT).show();
               }

            }


        });

    }
//******Spinner Methods

    private void loadSpinnerData() {
        // database handler
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        ArrayList<Category> categories = db.getAllCategoriesObj();
        // Spinner Drop down elements
        List<String> lables = new ArrayList<String>();
        for (int i=0; i<categories.size(); i++){
            lables.add(categories.get(i).getCategoryName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
      /*  Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
        Toast.makeText(parent.getContext(), "position =: " + position,
                Toast.LENGTH_LONG).show();*/
        SpinnerPosition=position;

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

//**Recording Methods**//

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
        playBtb.setBackgroundColor(playBtb.getContext().getResources().getColor(R.color.colorAccent));
        recordBtn.setEnabled(false);
        recordBtn.setBackgroundColor(recordBtn.getContext().getResources().getColor(R.color.colorAccent));

        try {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(FilePath);
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
        stopBtn.setBackgroundColor(stopBtn.getContext().getResources().getColor(R.color.colorAccent));
        playBtb.setEnabled(true);
        playBtb.setBackgroundColor(playBtb.getContext().getResources().getColor(R.color.orange));

        if (isRecording)
        {
            recordBtn.setEnabled(false);
            recordBtn.setBackgroundColor(recordBtn.getContext().getResources().getColor(R.color.colorAccent));
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            isRecording = false;
        } else {
            mPlayer.release();
            mPlayer = null;
            recordBtn.setEnabled(true);
            recordBtn.setBackgroundColor(recordBtn.getContext().getResources().getColor(R.color.orange));
        }
    }//END of StopAudio


    public void playAudio (View view) throws IOException
    {
        playBtb.setEnabled(false);
        playBtb.setBackgroundColor(playBtb.getContext().getResources().getColor(R.color.colorAccent));
        recordBtn.setEnabled(false);
        recordBtn.setBackgroundColor(recordBtn.getContext().getResources().getColor(R.color.colorAccent));
        stopBtn.setEnabled(true);
        stopBtn.setBackgroundColor(stopBtn.getContext().getResources().getColor(R.color.orange));

        mPlayer = new MediaPlayer();
        mPlayer.setDataSource(FilePath);
        mPlayer.prepare();
        mPlayer.start();

    }


    private void initializeMediaRecord(){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mRecorder.setOutputFile(FilePath);
    }




}
