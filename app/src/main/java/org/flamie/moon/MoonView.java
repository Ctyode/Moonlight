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

    private float xSun;
    private float ySun;
    private float sunSpeed = 200f;
    private float targetPosition = 900f;
    private float originalPosition = 2f;
    private float distance = targetPosition - originalPosition;

//    private int canvasWidth = this.getWidth() / 2;
//    private int canvasHeight = this.getHeight() / 2;
    private float radius = 50f;
    private float xMoon = 500f;
    private float yMoon = 500f;
    private float x;
    private float y;
    private float rectWidth;
    private float rectHeight;

    private Paint sunPaint;
    private Paint moonPaint;
    private Paint skyPaint;
    private Paint cloudPaint;

    private RectF farSideOfTheMoon;
    private RectF nearSideOfTheMoon;
    private RectF cloudRect;
    private RectF cloud;

    private int skyRed = 26;
    private int skyGreen = 35;
    private int skyBlue = 126;

    public MoonView(Context context) {
        super(context);
        sunPaint = new Paint();
        sunPaint.setColor(Color.YELLOW);
        sunPaint.setStyle(Paint.Style.FILL);
        sunPaint.setAntiAlias(true);

        moonPaint = new Paint();
        moonPaint.setColor(Color.YELLOW);
        moonPaint.setStrokeWidth(5f);
        moonPaint.setStyle(Paint.Style.STROKE);
        moonPaint.setAntiAlias(true);

        skyPaint = new Paint();
//        skyPaint.setColor(Color.argb(255, 48, 63, 159));
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
        canvas.drawColor(Color.argb(255, skyRed, skyGreen, skyBlue));
//        skyPaint.setColor(Color.argb(255, skyRed, skyGreen, skyBlue));
//        farSideOfTheMoon.set(xMoon - radius, yMoon - radius, xMoon + radius, yMoon + radius);
//        nearSideOfTheMoon.set((xMoon - 100f) - radius, (yMoon - 100f) - radius, (xMoon - 100f) + radius, (yMoon - 100f) + radius);
        canvas.drawCircle(xSun, -ySun + 1000f, radius, sunPaint);

//        canvas.drawRect(dp(1), dp(20), dp(10), dp(40), cloudPaint);
//        canvas.drawArc(farSideOfTheMoon, 295f, 220f, false, moonPaint);
//        canvas.drawArc(nearSideOfTheMoon, 335f, 140f, false, moonPaint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sunMoving();
//                redColor();
                greenColor();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                sunMoving();
//                redColor();
                greenColor();
                invalidate();
                break;
        }
        return true;
    }

    public void sunMoving() {
        if(xSun < 1200f) {
            xSun += distance / sunSpeed;
        } else {
            xSun =- distance / sunSpeed;
        }
        ySun = (float) (500 * Math.sin(Math.PI * (Math.abs(xSun + originalPosition)) / distance));
    }

    public int greenColor() {
        if(skyGreen <= 80 && ySun > 200) {
            skyGreen += 1;
        } else if(skyGreen >= 20) {
            skyGreen += -1;
        }
        return skyGreen;
    }


}
