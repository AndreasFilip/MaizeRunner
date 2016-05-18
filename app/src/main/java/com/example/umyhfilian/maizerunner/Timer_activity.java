package com.example.umyhfilian.maizerunner;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Timer_activity extends AppCompatActivity {

    TextView txtV;
    Button stateBtn;
    Timer timer;
    boolean state;
    Date startDate;
    Date endDate;
    double tiodelarAvSekund;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //Låser telefonen i landskap

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.timer_test_layout);
        txtV = (TextView) findViewById(R.id.showTime);
        stateBtn = (Button) findViewById(R.id.stateButton);
        stateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == false) {
                    stateBtn.setText("Stop");
                    calendar = Calendar.getInstance();
                    startDate = calendar.getTime();
                    state = true;
                    tiodelarAvSekund = 0;
                    setTimer();
                } else {
                    timer.cancel();
                    stateBtn.setText("Start");
                    calendar = Calendar.getInstance();
                    endDate = calendar.getTime();
                    state = false;
                    getTimeDifference();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
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
}