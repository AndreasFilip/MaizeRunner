package com.example.umyhfilian.maizerunner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;




/**
 * Created by Administratör on 2016-05-09.
 */
public class Renderer extends View {



    PlayerCircle rendererPlayerCircle;
    StagePiece rendererStagePiece;
    public ImageView scene;
    public int screenX;
    public int screenY;
    Paint paint;


    MainActivity act;

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
        Bitmap playerCircleBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_maize_runner_pc_huge);
        act = ((MainActivity)context);
        rendererStagePiece = act.stagePiece;
        rendererPlayerCircle = new PlayerCircle(playerCircleBitmap,100,100,85,85,0,0,act);
        Bitmap blankBitmap;
        blankBitmap = Bitmap.createBitmap(screenX,screenY,Bitmap.Config.ARGB_8888);
        scene = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scene.setLayoutParams(params);
        scene.setImageBitmap(blankBitmap);
        paint = new Paint();
        paint.setColor(Color.argb(255,  249, 129, 0));
        paint.setTextSize(act.convertDpToPix(32));
    }


    public void defineSize (){


        screenX = getScreenWidth();
        screenY = getScreenHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("X is:" + act.valueX,50,50,paint);
        canvas.drawText("Y is:" + act.valueY,100,100,paint);
        canvas.drawRect(rendererStagePiece.leftDistance,rendererStagePiece.topDistance,
                rendererStagePiece.rightDistance,rendererStagePiece.lowerDistance, paint);
        canvas.drawBitmap(rendererPlayerCircle.playerCircle, null, rendererPlayerCircle.rect, paint);
    }


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}
