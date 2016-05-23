package com.example.umyhfilian.maizerunner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.TimerTask;



/**
 * Created by Administratör on 2016-05-09.
 */
public class Renderer extends View {



    PlayerCircle rendererPlayerCircle;
    StagePiece rendererStagePiece;
    public ImageView scene;
    public int screenX;
    public int screenY;
    int i2 = 0;
    Paint paint;


    MainActivity act;

    PlayerCircle playerCircle;
    Context context;
    int i = 1;

    public Renderer(Context context) {
        super(context);

        setup(context);
    }

    public Renderer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public Renderer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    public void setup(Context context) {
        defineSize();
        act = ((MainActivity)context);
        rendererPlayerCircle = act.currentPlayer;
        rendererStagePiece = act.stagePiece;
        Bitmap playerCircleBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_maize_runner_pc_huge);
        act = ((MainActivity)context);
        rendererPlayerCircle = act.currentPlayer;
        rendererStagePiece = act.stagePiece;
        rendererPlayerCircle = new PlayerCircle(playerCircleBitmap,screenX-(screenX/5),0,85,85,act);


        // Declare an object of type Bitmap
        Bitmap blankBitmap;
        // Make it 600 x 600 pixels in size and an appropriate format
        blankBitmap = Bitmap.createBitmap(screenX,screenY,Bitmap.Config.ARGB_8888);

        // Declare an object of type canvas
        /*Canvas canvas;
        // Initialize it by making its surface our previously created blank bitmap
        canvas = new Canvas(blankBitmap);*/

        // Initialize our previously declared member object of type ImageView
        scene = new ImageView(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scene.setLayoutParams(params);

        // Put our blank bitmap on ourView
        scene.setImageBitmap(blankBitmap);

        // We need something to draw with

        // Initialize it ready for painting our canvas
        paint = new Paint();
        paint.setColor(Color.argb(255,  249, 129, 0));

        // Declare an object of type Bitmap
        /*Bitmap blankBitmap;
        // Make it 600 x 600 pixels in size and an appropriate format
        Bitmap blankBitmap = Bitmap.createBitmap(screenX,screenY,Bitmap.Config.ARGB_8888);*/

        // Declare an object of type canvas
        /*Canvas canvas;
        // Initialize it by making its surface our previously created blank bitmap
        canvas = new Canvas(blankBitmap);*/

        // Initialize our previously declared member object of type ImageView
        /*scene = new ImageView(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scene.setLayoutParams(params);

        // Put our blank bitmap on ourView
        scene.setImageBitmap(blankBitmap);*/

        // We need something to draw with


    }


    /*public void run() {
        if (i == 1) {
            context = act.getContext();
            i = 2 ;
        }
        draw(context);
        i2++;
        //Log.i("TAG","i2 is :" + i2);


    }
*/
    public void defineSize (){


        screenX = getScreenWidth();
        screenY = getScreenHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rendererStagePiece.leftDistance,rendererStagePiece.topDistance,
                rendererStagePiece.rightDistance,rendererStagePiece.lowerDistance, paint);
        rendererPlayerCircle.rect.offset(-5,0);
        canvas.drawBitmap(rendererPlayerCircle.playerCircle, null, rendererPlayerCircle.rect, paint);
    }

    public void draw(Context context){



        // Make the canvas white
        //scene.drawColor(Color.argb(255, 255, 255, 255));






        scene.postInvalidate();
        // Back to onCreate method to set our canvas as the view
        Log.i("TAG", "LOL");

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}
