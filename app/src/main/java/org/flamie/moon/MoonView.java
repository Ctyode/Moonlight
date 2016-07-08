package org.flamie.moon;

import static org.flamie.moon.util.Dimen.dp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

public class MoonView extends View {

//    private int canvasWidth = this.getWidth() / 2;
//    private int canvasHeight = this.getHeight() / 2;
    private float radius = 200f;
    private float xMoon = 500f;
    private float yMoon = 500f;
    private float x;
    private float y;
    private float rectWidth;
    private float rectHeight;

    private Paint moonPaint;
    private Paint skyPaint;
    private Paint cloudPaint;
    private RectF farSideOfTheMoon;
    private RectF nearSideOfTheMoon;
    private RectF cloudRect;
    private RectF cloud;

    public MoonView(Context context) {
        super(context);
        moonPaint = new Paint();
        moonPaint.setColor(Color.YELLOW);
        moonPaint.setStrokeWidth(5f);
        moonPaint.setStyle(Paint.Style.STROKE);
        moonPaint.setAntiAlias(true);

        skyPaint = new Paint();
        skyPaint.setColor(Color.argb(255, 48, 63, 159));
        skyPaint.setStyle(Paint.Style.FILL);
        skyPaint.setAntiAlias(true);

        cloudPaint = new Paint();
        cloudPaint.setColor(Color.argb(255, 48, 63, 159));
        skyPaint.setStyle(Paint.Style.FILL);
        cloudPaint.setAntiAlias(true);

        cloud = new RectF();
        cloudRect = new RectF();
        farSideOfTheMoon = new RectF();
        nearSideOfTheMoon = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        farSideOfTheMoon.set(xMoon - radius, yMoon - radius, xMoon + radius, yMoon + radius);
        nearSideOfTheMoon.set((xMoon - 100f) - radius, (yMoon - 100f) - radius, (xMoon - 100f) + radius, (yMoon - 100f) + radius);

        canvas.drawRect(dp(1), dp(20), dp(10), dp(40), cloudPaint);
        canvas.drawArc(farSideOfTheMoon, 295f, 220f, false, moonPaint);
        canvas.drawArc(nearSideOfTheMoon, 335f, 140f, false, moonPaint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xMoon = event.getX();
                yMoon = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                xMoon = event.getX();
                yMoon = event.getY();
                invalidate();
                break;
        }
        return true;
    }

}
