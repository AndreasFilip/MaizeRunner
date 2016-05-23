package com.example.umyhfilian.maizerunner;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by umyhfilian on 5/23/2016.
 */
public class Canvas extends View {

    MainActivity activity;
    Paint paint = new Paint();

    /**
     * Match all 3 constructors and save context reference as MainActivity. Constructor is run automatically when view is in xml
     * @param context
     */
    public Canvas(Context context) {
        super(context);
        activity = (MainActivity)context;
    }

    public Canvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (MainActivity)context;
    }

    public Canvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (MainActivity)context;
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);


        /**
         * Everything regarding canvas is always in pixel size, so, all actor's sizes needs to be declared as dp and then converted into pixel size
         * before being used on canvas. check Ball.java. Z depth is defined by drawing order.
         * Position is defined by the Rect class. Ball holds reference to this class
         */
        canvas.drawBitmap(activity.currentPlayer.playerCircle,null, activity.currentPlayer.rect,paint);
    }
}