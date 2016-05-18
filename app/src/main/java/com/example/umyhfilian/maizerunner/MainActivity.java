package com.example.umyhfilian.maizerunner;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements SensorEventListener
{
    private ImageView imageView;
    private TextView tv;
    private SensorManager sManager;

    LinearLayoutCompat linearLayout;

    Animation animation1;
    Animation animation2;
    TextView txtV;
    Button stateBtn;
    Timer timer;
    boolean state;
    Date startDate;
    Date endDate;
    double tiodelarAvSekund;
    Calendar calendar;

    public static float density;
    Renderer renderer = new Renderer();
    Context mainActivity;

    public Context getContext(){
        Context context = this;
        return context;
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        setupTimer();
        mainActivity = getContext();
    }

    public void setupActivity(){

        Button startButton;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Låser telefonen i landskap

        // remove titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.start_screen);
        ButterKnife.bind(this);

        //get the TextView from the layout file
        tv = (TextView) findViewById(R.id.tv);

        //Button for transition
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

        //renderer.Toaster(this);
        density =  getResources().getDisplayMetrics().density;

        renderer.defineSize();
        renderer.draw(this);

    }

    private void setupTimer(){
        txtV = (TextView) findViewById(R.id.tv);


                    calendar = Calendar.getInstance();
                    startDate = calendar.getTime();
                    state = true;
                    tiodelarAvSekund = 0;
                    setTimer();

            }

    public void setTimer() {
        timer = new Timer();                //ni både skapa en ny timer varje gång här!
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tiodelarAvSekund += 0.1;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtV.setText(String.format("%.1f", tiodelarAvSekund));
                    }
                });

            }
        }, 100, 100);        //millisekunder
    }

    public void getTimeDifference() {
        Log.i("TAG", "" + (endDate.getTime() - startDate.getTime()));
        Log.i("TAG", DateUtils.formatElapsedTime(null, ((endDate.getTime() - startDate.getTime()) / 1000)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*register the sensor listener to listen to the gyroscope sensor, use the
        callbacks defined in this class, and gather the sensor information as quick
        as possible*/
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
    }

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

        //else it will output the Roll, Pitch and Yawn values
        tv.setText("Orientation X (Roll) :"+ Float.toString(event.values[2]) +"\n"+
                "Orientation Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+
                "Orientation Z (Yaw) :"+ Float.toString(event.values[0]));

        imageView.setX(300 - event.values[1]*4);
        imageView.setY(300 + event.values[2]*4);
    }
    public void fade(){
        linearLayout = (LinearLayoutCompat) findViewById(R.id.linearLayoutStartScreen);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        linearLayout.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                setContentView(R.layout.activity_main);
                setContentView(renderer.scene);
                renderer.scene.startAnimation(animation2);
                animation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //SPEL LOGIK SKA STARTA HÄR
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
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