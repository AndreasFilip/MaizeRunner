package com.example.umyhfilian.maizerunner;
import android.app.Activity;
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
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements SensorEventListener
{
    protected float maxRange;   //Max range for Gyroscope
    protected float valueX;     //X-value for gyroscope
    protected float valueY;     //Y-value for gyroscope
    private SensorManager sManager; //Needed to an instance of the Gyroscope
    private Animation animation2;   //Animation for transition to game screen
    private DisplayMetrics metrics; //Needed to get screen size
    private Renderer renderer;      // Class responsible for drawing graphics
    private Timer gameTimer;        //Game timer
    protected float screen_width_pix;   //current screen width in pix
    protected float screen_height_pix;  //screen height in pix
    public Bitmap PlayerCircleBitmap;   //Image file for player object
    PlayerCircle currentPlayer;         //Instance of player object
    StagePiece stagePiece;              //Instance of obstacles


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivity();
        getScreenSizeInPixels();
    }

    /**
     * Just a setup class that creates everything that's needed instead of filling up the OnCreate method
     */
    public void setupActivity(){

        Button startButton;
        //Locks in landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // remove titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_screen);
        //Button for transition
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeFromStartToGameView();
            }
        });
        //get a hook to the sensor service
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        maxRange = sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION).getMaximumRange();
        /**
         * Init the metrics like this so access density of screen
         */
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        PlayerCircleBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_maize_runner_pc_huge);
        stagePiece = new StagePiece (50,450,500,550);
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
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //if sensor is unreliable, return void
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }
        valueX = event.values[2];
        valueY = event.values[1];
    }

    public float convertDpToPix(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,metrics);
    }

    public void fadeFromStartToGameView(){
        LinearLayoutCompat linearLayout = (LinearLayoutCompat) findViewById(R.id.linearLayoutStartScreen);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        linearLayout.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                setContentView(R.layout.activity_main);
                renderer = (Renderer) findViewById(R.id.canvas);
                renderer.startAnimation(animation2);
                animation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
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
                                renderer.rendererPlayerCircle.updateLocation(valueX,valueY);
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
    /**
     * grabbing the screen pixel size
     */
    public void getScreenSizeInPixels(){
        Configuration config = getResources().getConfiguration();
        screen_width_pix=convertDpToPix(config.screenWidthDp)+112;
        screen_height_pix=convertDpToPix(config.screenHeightDp)+80;
        Log.i("MY_TAG",String.format("w %.2f, h %.2f",screen_width_pix,screen_height_pix));
    }
}