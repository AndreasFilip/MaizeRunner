package com.example.umyhfilian.maizerunner;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Administratör on 2016-05-24.
 */
public class Goal {

    public Bitmap goal;
    Rect rect;      //built in hitbox class.
    MainActivity activity;

    public Goal (Bitmap goal, int x, int y,float w, float h, MainActivity act){


        this.activity = act;
        this.goal = goal;
        this.rect = new Rect(x,y,x+(int)activity.convertDpToPix(w),y+(int)activity.convertDpToPix(h));




    }


}
