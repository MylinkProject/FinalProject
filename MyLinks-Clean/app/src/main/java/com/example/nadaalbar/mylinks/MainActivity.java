package com.example.nadaalbar.mylinks;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;



public class MainActivity extends AppCompatActivity {
    private TextView textadd;
    private TextView textlinks;
    private TextView textcategories;


    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Menu code here !


        Button addBtn= (Button) findViewById(R.id.addLink2);
        Button addCat= (Button)findViewById(R.id.addCat2);

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



        textadd = (TextView) findViewById(R.id.text_add);
        textlinks = (TextView) findViewById(R.id.text_links);
        textcategories = (TextView) findViewById(R.id.text_categories);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_Add:
                                textadd.setVisibility(View.VISIBLE);
                                textlinks.setVisibility(View.GONE);
                                textcategories.setVisibility(View.GONE);

                                Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                              //  Toast.makeText(getApplicationContext(),"Favorates",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_Links:
                                textadd.setVisibility(View.GONE);
                                textlinks.setVisibility(View.VISIBLE);
                                textcategories.setVisibility(View.GONE);

                                Intent linksListInent = new Intent(getApplicationContext(), LinksList.class);
                                startActivity(linksListInent);

                               // Toast.makeText(getApplicationContext(),"sch",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_Categories:
                               // Toast.makeText(getApplicationContext(),"music",Toast.LENGTH_LONG).show();
                                textadd.setVisibility(View.GONE);
                                textlinks.setVisibility(View.GONE);
                                textcategories.setVisibility(View.VISIBLE);
                                Intent CategoriesInent = new Intent(getApplicationContext(), CategoriesList.class);
                                startActivity(CategoriesInent);

                                break;


                        }
                        return false;
                    }
                });
//****Sensors

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
                Intent myIntent = new Intent(MainActivity.this, AddLink.class);
                startActivity(myIntent);

                Toast.makeText(getApplicationContext(), "Device was Shaken", Toast.LENGTH_SHORT).show();
                finish();
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
