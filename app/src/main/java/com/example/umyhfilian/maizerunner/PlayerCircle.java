package com.example.umyhfilian.maizerunner;


import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class PlayerCircle  {

    public Bitmap playerCircle;
    float xSpeed;   //speed is in dp, converted to pix
    float ySpeed;
    float maxXSpeed = 50;
    float maxYSpeed = 50;
    Rect rect;      //built in hitbox class.
    MainActivity activity;
    int margin = 5;

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
        //HÖGER
        if (rect.right+xSpeed < activity.screen_width_pix){
            //VÄNSTER
            if (rect.left+xSpeed > 0){
                //BOTTOM
                if (rect.bottom+xSpeed < activity.screen_height_pix){
                     //TOP
                    if (rect.top+xSpeed > 0){
                    }
                    //touches top
                    else{

                        rect.set(rect.left+(int)xSpeed,margin,rect.left+(int)xSpeed+rect.width(),margin+rect.height());
                        xSpeed = 0;
                        ySpeed = 0;
                        return;
                    }
                }
                //touches bottom
                else{
                    rect.set(rect.left+(int)xSpeed,(int)activity.screen_height_pix-rect.height()-margin,rect.left+(int)xSpeed+rect.width(),(int)activity.screen_height_pix-margin);
                    xSpeed = 0;
                    ySpeed = 0;
                    return;
                }
            }
            //touches left
        else{
                rect.set(margin,rect.top+(int)ySpeed,margin+rect.width(),rect.top+rect.height()+(int)ySpeed);
                xSpeed = 0;
                ySpeed = 0;
                return;
            }
        }
        //Touches right
        else{
            rect.set((int)activity.screen_width_pix-rect.width()-margin,rect.top+(int)ySpeed,(int)activity.screen_width_pix-margin,rect.top+(int)ySpeed+rect.height());
            xSpeed = 0;
            ySpeed = 0;
            return;
        }

        xSpeed = maxXSpeed * (activity.valueX / activity.maxRange);
        ySpeed = maxYSpeed * (activity.valueY / activity.maxRange);

        rect.offset((int)-ySpeed,(int)xSpeed);
        /*else{
            rect.set((int)activity.screen_width_pix-rect.width(),rect.top,(int)activity.screen_width_pix,rect.top+rect.height());
            Log.i("MY_TAG", "setting to border");
        }*/
        Log.i("MY_TAG",String.format("Ball pos: x:%d y:%d w:%d h:%d ",this.rect.left,this.rect.top, this.rect.width(),this.rect.height()));
    /*    //HÖGER
        if(x+rect.width()+xSpeed < activity.screen_width_pix){
            //VÄNSTER
        if (x+xSpeed > 0){
            //TOP
            if(y+ySpeed > 0){

            }
        }
    }*/
    }
}
