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
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
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


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                Toast.makeText(getApplicationContext(), "Device Shaken", Toast.LENGTH_SHORT).show();
            }
        });

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

//***Shaking Code
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
