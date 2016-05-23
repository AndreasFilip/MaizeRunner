package com.example.umyhfilian.maizerunner;

/**
 * Created by umyhfilian on 5/23/2016.
 */

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Bollen on 5/14/2016.
 */
public class Ball {
    Bitmap bmp;
    float xSpeed;   //speed is in dp, converted to pix
    float ySpeed;
    Rect rect;      //built in hitbox class.
    MainActivity activity;

    public Ball(Bitmap bmp, int x, int y, float w, float h, float xSpeed, float ySpeed, MainActivity activity){
        this.bmp = bmp;
        this.rect = new Rect(x,y,x+(int)activity.convertDpToPix(w),y+(int)activity.convertDpToPix(h));  //note it is x+width for width, and y+height for height
        Log.i("MY_TAG",String.format("BALL: pix w %d h %d",this.rect.width(),this.rect.height()));
        this.xSpeed = activity.convertDpToPix(xSpeed);
        this.ySpeed = activity.convertDpToPix(ySpeed);
        Log.i("MY_TAG",String.format("BALL SPEEDS: x %.2f y %.2f",this.xSpeed,this.ySpeed));
        this.activity = activity;
    }

    /**
     * Just checking one thing here, the ball will move from left to right and then stop at border.
     * use offset to move, and set to position at specified location
     */
    public void updateLocation(){
        if (rect.left+xSpeed+rect.width() < activity.screen_width_pix){
            rect.offset((int)xSpeed,0);
            Log.i("MY_TAG","offsetting");
        }
        else{
            rect.set((int)activity.screen_width_pix-rect.width(),rect.top,(int)activity.screen_width_pix,rect.top+rect.height());
            Log.i("MY_TAG", "setting to border");
        }
        Log.i("MY_TAG",String.format("Ball pos: x:%d y:%d w:%d h:%d ",this.rect.left,this.rect.top, this.rect.width(),this.rect.height()));
    }
}

