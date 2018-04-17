package com.example.nadaalbar.mylinks;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.content.Context;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Menu code here !


        Button addBtn= (Button) findViewById(R.id.addLink);
        Button addCat= (Button)findViewById(R.id.AddCat);
        Button viewLinks= (Button) findViewById(R.id.viewLinksId);
        Button viewCats= (Button)findViewById(R.id.viewCatId);

        int YOUR_REQUEST_CODE = 200; // could be something else..
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //check if permission request is necessary
        {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, YOUR_REQUEST_CODE);}


        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){Intent myIntent = new Intent(MainActivity.this, AddLink.class);
                startActivity(myIntent);}

        });

        addCat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

              Intent myIntent = new Intent(MainActivity.this, AddCategory.class);
                startActivity(myIntent);

            }

        });


        viewLinks.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                Intent myIntent = new Intent(MainActivity.this, LinksList.class);
                startActivity(myIntent);

            }

        });


        viewCats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                Intent myIntent = new Intent(MainActivity.this, CategoriesList.class);
                startActivity(myIntent);

            }

        });





        // Sensor Shake

/*
        SensorManager mSensorManager;
        ShakeEvent mSensorListener;

        mSensorListener = new ShakeEvent();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);


        mSensorListener.setOnShakeListener(new ShakeEvent.OnShakeListener() {

            public void onShake() {
                Intent i = new Intent(Shake.this, NEWACTIVITY.class);
                startActivity(i);
            }
        });*/

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){

            Toast.makeText(getApplicationContext(),"Portrait mode",Toast.LENGTH_SHORT).show();

        } else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(getApplicationContext(),"Landscape mode",Toast.LENGTH_SHORT).show();

        }
    }


}
