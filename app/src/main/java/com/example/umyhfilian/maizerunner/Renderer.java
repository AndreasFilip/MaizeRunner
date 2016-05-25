package com.example.umyhfilian.maizerunner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Renderer  {

    public ImageView scene;
    public int screenX;
    public int screenY;
    MainActivity act;
    PlayerCircle rendererPlayerCircle;
    StagePiece rendererStagePiece;
    Goal rendererGoal;

    StagePiece rendererStagePiece1;
    StagePiece rendererStagePiece2;
    StagePiece rendererStagePiece3;
    StagePiece rendererStagePiece4;
    StagePiece rendererStagePiece5;







    public void defineSize (){

        screenX = getScreenWidth();
        screenY = getScreenHeight();

    }

    public void draw(Context context){
        act = ((MainActivity)context);
        rendererPlayerCircle = act.currentPlayer;
        rendererStagePiece = act.stagePiece;
        rendererGoal = act.currentGoal;

        rendererStagePiece1 = act.currentStage[0];
        rendererStagePiece2 = act.currentStage[1];
        rendererStagePiece3 = act.currentStage[2];
        rendererStagePiece4 = act.currentStage[3];
        rendererStagePiece5 = act.currentStage[4];




        // Declare an object of type Bitmap
        Bitmap blankBitmap;
        // Make it as big as the screen.
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


        // Declare an object of type Paint
        Paint paint;
        paint = new Paint();

        // Make the canvas white
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        paint.setColor(Color.argb(255,  249, 129, 0));


       drawStage(canvas,paint);

        canvas.drawBitmap(rendererGoal.goal, null, rendererGoal.rect,paint);

        canvas.drawBitmap(rendererPlayerCircle.playerCircle, null, rendererPlayerCircle.rect, paint);

        // Back to onCreate method to set our canvas as the view



    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public  void drawStage(Canvas canvas, Paint paint){ //Ritar upp alla bitar av Stage

        canvas.drawRect(rendererStagePiece1.leftDistance,rendererStagePiece1.topDistance,
                rendererStagePiece1.rightDistance,rendererStagePiece1.lowerDistance,paint);

        canvas.drawRect(rendererStagePiece2.leftDistance,rendererStagePiece2.topDistance,
                rendererStagePiece2.rightDistance,rendererStagePiece2.lowerDistance,paint);

        canvas.drawRect(rendererStagePiece3.leftDistance,rendererStagePiece3.topDistance,
                rendererStagePiece3.rightDistance,rendererStagePiece3.lowerDistance,paint);

        canvas.drawRect(rendererStagePiece4.leftDistance,rendererStagePiece4.topDistance,
                rendererStagePiece4.rightDistance,rendererStagePiece4.lowerDistance,paint);

        canvas.drawRect(rendererStagePiece5.leftDistance,rendererStagePiece5.topDistance,
                rendererStagePiece5.rightDistance,rendererStagePiece5.lowerDistance,paint);
    }


}
