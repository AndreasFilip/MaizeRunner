package com.example.umyhfilian.maizerunner;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
    public DisplayMetrics metrics;
    Renderer renderer;
    Timer gameTimer;
    Context mainActivity;
    public float screen_width_pix;       //current screen width in pix
    public float screen_height_pix;      //screen height in pix

    public Context getContext(){
        Context context = this;
        return context;
}
    public Bitmap PlayerCircleBitmap;
    PlayerCircle currentPlayer;
    StagePiece stagePiece;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        mainActivity = getContext();
        getScreenSizeInPixels();
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
        //renderer = new Renderer();
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


        /**
         * Init the metrics like this so access density of screen
         */
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        renderer.defineSize();

        PlayerCircleBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_maize_runner_pc_huge);
        stagePiece = new StagePiece (50,450,500,550);

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();





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

    public float convertDpToPix(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,metrics);
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
                renderer = (Renderer) findViewById(R.id.canvas);
                //renderer.draw(this);
                //setContentView(renderer.scene);

                renderer.startAnimation(animation2);
                animation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //SPEL LOGIK SKA STARTA HÄR
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        View decorView = getWindow().getDecorView();
                        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN;
                        decorView.setSystemUiVisibility(uiOptions);

                        getScreenSizeInPixels();

                        /**
                         * Gameloop. ~30 times/sec
                         */
                        gameTimer = new Timer();
                        gameTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                renderer.rendererPlayerCircle.updateLocation();
                                renderer.postInvalidate();
                                Log.i("TAG","LOL2");
                            }
                        },33,33);
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
    public Context getContext (Context context){
        context = getContext();
        return context;
    }
    /**
     * grabbing the screen pixel size
     */
    public void getScreenSizeInPixels(){
        Configuration config = getResources().getConfiguration();
        screen_width_pix=convertDpToPix(config.screenWidthDp);
        screen_height_pix=convertDpToPix(config.screenHeightDp);
        Log.i("MY_TAG",String.format("w %.2f, h %.2f",screen_width_pix,screen_height_pix));
    }
}