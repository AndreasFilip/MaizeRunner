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
 * Class responsible for drawing graphics
 */
public class Renderer extends View {

    protected PlayerCircle rendererPlayerCircle;
    private int screenX;
    private int screenY;
    private Paint paint;

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
        MainActivity act = ((MainActivity) context);
        rendererPlayerCircle = act.currentPlayer;
        Bitmap playerCircleBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_maize_runner_pc_huge);
        act = ((MainActivity)context);
        rendererPlayerCircle = new PlayerCircle(playerCircleBitmap,100,100,85,85,0,0, act);
        Bitmap blankBitmap;
        blankBitmap = Bitmap.createBitmap(screenX,screenY,Bitmap.Config.ARGB_8888);
        ImageView scene = new ImageView(context);
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
        canvas.drawBitmap(rendererPlayerCircle.playerCircle, null, rendererPlayerCircle.rect, paint);
    }


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}
