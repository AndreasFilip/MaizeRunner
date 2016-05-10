package com.example.umyhfilian.maizerunner;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements SensorEventListener
{
    private ImageView imageView;
    //a TextView
    private TextView tv;
    //the Sensor Manager
    private SensorManager sManager;
    private Button startButton;
    LinearLayoutCompat linearLayout;
    Animation animation1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Låser telefonen i landskap

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.start_screen);
        ButterKnife.bind(this);

        //get the TextView from the layout file
        tv = (TextView) findViewById(R.id.tv);
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TAG","YOU PRESSED BUTTON");
                fade();
            }
        });

        imageView = (ImageView)findViewById(R.id.imageView);
        //get a hook to the sensor service
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    //when this Activity starts
    @Override
    protected void onResume() {
        super.onResume();
        /*register the sensor listener to listen to the gyroscope sensor, use the
        callbacks defined in this class, and gather the sensor information as quick
        as possible*/
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
    }

    //When this Activity isn't visible anymore
    @Override
    protected void onStop() {
        //unregister the sensor listener
        sManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        //Do nothing.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //if sensor is unreliable, return void
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }

        /*//else it will output the Roll, Pitch and Yawn values
        tv.setText("Orientation X (Roll) :"+ Float.toString(event.values[2]) +"\n"+
                "Orientation Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+
                "Orientation Z (Yaw) :"+ Float.toString(event.values[0]));

        imageView.setX(300 - event.values[1]*4);
        imageView.setY(300 + event.values[2]*4);*/
    }
    public void fade(){
        linearLayout = (LinearLayoutCompat) findViewById(R.id.linearLayoutStartScreen);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        linearLayout.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                setContentView(R.layout.point_screen);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                //Functionality here
            }

            @Override
            public void onAnimationStart(Animation arg0) {
                //Functionality here
            }
        });
    }
}