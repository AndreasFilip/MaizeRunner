package com.example.umyhfilian.maizerunner;


import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class PlayerCircle  {

    public Bitmap playerCircle;
    float xSpeed;   //speed is in dp, converted to pix
    float ySpeed;
    Rect rect;      //built in hitbox class.
    MainActivity activity;

    public  PlayerCircle (Bitmap playerCircle, int x, int y,float w, float h, MainActivity act){
        this.activity = act;
        this.playerCircle = playerCircle;
        this.rect = new Rect(x,y,x+(int)activity.convertDpToPix(w),y+(int)activity.convertDpToPix(h));  //note it is x+width for width, and y+height for height
        Log.i("MY_TAG",String.format("BALL: pix w %d h %d",this.rect.width(),this.rect.height()));
        this.xSpeed = activity.convertDpToPix(xSpeed);
        this.ySpeed = activity.convertDpToPix(ySpeed);
        Log.i("MY_TAG",String.format("BALL SPEEDS: x %.2f y %.2f",this.xSpeed,this.ySpeed));
        this.activity = activity;
    }





}
