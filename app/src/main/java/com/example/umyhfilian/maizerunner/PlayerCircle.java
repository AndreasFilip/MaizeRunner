package com.example.umyhfilian.maizerunner;


import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

/**
 * Class that handles player movement
 */
public class PlayerCircle  {

    protected Bitmap playerCircle;
    private float xSpeed;   //speed is in dp, converted to pix
    private float ySpeed;   //speed is in dp, converted to pix
    private float maxXSpeed = 80;   //Variable that controls the players max-speed
    private float maxYSpeed = 80;   //Variable that controls the players max-speed
    protected Rect rect;      //built in hitbox class.
    private MainActivity activity;

    public  PlayerCircle (Bitmap playerCircle, int x, int y,float h,float w,float xSpeed, float ySpeed, MainActivity act){
        this.activity = act;
        this.playerCircle = playerCircle;
        this.rect = new Rect(x,y,x+(int)activity.convertDpToPix(w),y+(int)activity.convertDpToPix(h));  //note it is x+width for width, and y+height for height
        Log.i("MY_TAG",String.format("BALL: pix w %d h %d",this.rect.width(),this.rect.height()));
        this.xSpeed = activity.convertDpToPix(xSpeed);
        this.ySpeed = activity.convertDpToPix(ySpeed);
        Log.i("MY_TAG1",String.format("BALL SPEEDS: x %.2f y %.2f",this.xSpeed,this.ySpeed));
    }

    /**
     * Just checking one thing here, the ball will move from left to right and then stop at border.
     * use offset to move, and set to position at specified location
     */
    public void updateLocation(float x,float y){
        //RIGHT
        int collisionOffsetValue = 4; // The offset value which the player is offset when hitting a wall
        if (rect.right+xSpeed < activity.screen_width_pix){
            //LEFT
            if (rect.left+xSpeed > 0){
                //BOTTOM
                if (rect.bottom+xSpeed < activity.screen_height_pix){
                     //TOP
                    if (rect.top+xSpeed > 0){
                    }
                    //touches top
                    else{

                        rect.set(rect.left+(int)xSpeed, collisionOffsetValue,rect.left+(int)xSpeed+rect.width(), collisionOffsetValue +rect.height());
                        xSpeed = 0;
                        ySpeed = 0;
                        return;
                    }
                }
                //touches bottom
                else{
                    rect.set(rect.left+(int)xSpeed,(int)activity.screen_height_pix-rect.height()- collisionOffsetValue,rect.left+(int)xSpeed+rect.width(),(int)activity.screen_height_pix- collisionOffsetValue);
                    xSpeed = 0;
                    ySpeed = 0;
                    return;
                }
            }
            //touches left
        else{
                rect.set(collisionOffsetValue,rect.top+(int)ySpeed, collisionOffsetValue +rect.width(),rect.top+rect.height()+(int)ySpeed);
                xSpeed = 0;
                ySpeed = 0;
                return;
            }
        }
        //Touches right
        else{
            rect.set((int)activity.screen_width_pix-rect.width()- collisionOffsetValue,rect.top+(int)ySpeed,(int)activity.screen_width_pix- collisionOffsetValue,rect.top+(int)ySpeed+rect.height());
            xSpeed = 0;
            ySpeed = 0;
            return;
        }

        xSpeed = maxXSpeed * (activity.valueX / activity.maxRange);
        ySpeed = maxYSpeed * (activity.valueY / activity.maxRange);

        rect.offset((int)-ySpeed,(int)xSpeed);
        Log.d("MY_TAG",String.format("Ball pos: x:%d y:%d w:%d h:%d ",this.rect.left,this.rect.top, this.rect.width(),this.rect.height()));

    }
}
