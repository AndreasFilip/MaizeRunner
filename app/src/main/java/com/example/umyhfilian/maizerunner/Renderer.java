package com.example.umyhfilian.maizerunner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administratör on 2016-05-09.
 */
public class Renderer  {

    //public String scream;
    TextView textView;
    public ImageView scene;
    public int screenX;
    public int screenY;
    MainActivity mainActivity;
    Context context;

   /* public void Toaster (Context context){

        scream = "Aaaaaagh";
        Toast.makeText(context, scream, Toast.LENGTH_LONG).show();

    }*/

    public void defineSize (){
        screenX = getScreenWidth();
        screenY = getScreenHeight();


    }

    public void draw(Context context){

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
        // Put our blank bitmap on ourView
        scene.setImageBitmap(blankBitmap);

        // We now have a surface ready to draw on
        // But we need something to draw with

        // Declare an object of type Paint
        Paint paint;
        // Initialize it ready for painting our canvas
        paint = new Paint();

        // Make the canvas white
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        // Make the brush blue
        paint.setColor(Color.argb(255,  26, 128, 182));
        // We can change this around as well

        // Draw a line
        canvas.drawLine(50,50,250,250,paint);

        // Draw some text
        canvas.drawText("Game Code School", 50, 50, paint);

        // Draw a pixel
        canvas.drawPoint(40,50,paint);

        // Draw a circle
        canvas.drawCircle(350,250,100,paint);

        // Change the brush color
        paint.setColor(Color.argb(255,  249, 129, 0));

        // Draw a rectangle
        canvas.drawRect(50,450,500,550,paint);

        // Back to onCreate method to set our canvas as the view



    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}
