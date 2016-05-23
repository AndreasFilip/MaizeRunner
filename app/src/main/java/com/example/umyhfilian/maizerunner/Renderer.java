package com.example.umyhfilian.maizerunner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.TimerTask;


/**
 * Created by Administratör on 2016-05-09.
 */
public class Renderer extends TimerTask {

    public ImageView scene;
    public int screenX;
    public int screenY;
    int i2 = 0;

    MainActivity act;
    PlayerCircle playerCircle;
    Context context;
    int i = 1;
    public void run() {
        if (i == 1) {
            context = act.getContext();
            i = 2 ;
        }
        draw(context);
        i2++;
        Log.i("TAG","i2 is :" + i2);

    }

    public void defineSize (){


        screenX = getScreenWidth();
        screenY = getScreenHeight();

    }

    public void draw(Context context){
        Log.i("TAG", "LOL");
        act = ((MainActivity)context);
        playerCircle = act.currentPlayer;

        // Declare an object of type Bitmap
        Bitmap blankBitmap;
        // Make it 600 x 600 pixels in size and an appropriate format
        blankBitmap = Bitmap.createBitmap(screenX,screenY,Bitmap.Config.ARGB_8888);

        // Declare an object of type canvas
        Canvas canvas;
        // Initialize it by making its surface our previously created blank bitmap
        canvas = new Canvas(blankBitmap);

        // Initialize our previously declared member object of type ImageView
        scene = new ImageView(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scene.setLayoutParams(params);

        // Put our blank bitmap on ourView
        scene.setImageBitmap(blankBitmap);

        // We now have a surface ready to draw on
        // But we need something to draw with

        // Declare an object of type Paint
        Paint paint;
        // Initialize it ready for painting our canvas
        paint = new Paint();

        int color = 10;

        // Make the canvas white
        canvas.drawColor(Color.argb(color++, 255, 255, 255));


        canvas.drawBitmap(playerCircle.playerCircle, null, playerCircle.rect, paint);

        // Back to onCreate method to set our canvas as the view



    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}
