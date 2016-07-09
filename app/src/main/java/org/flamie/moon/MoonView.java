package org.flamie.moon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;

public class MoonView extends View {

    private LinearGradient skyGradient;
    private float xSun;
    private float ySun;
    private float sunSpeed = 200f;
    private float targetPosition = 1200f;
    private float originalPosition = 4f;
    private float distance = targetPosition - originalPosition;

    private int canvasWidth;
    private int canvasHeight;
    private float radius = 50f;

    private Paint sunPaint;
    private Paint moonPaint;
    private Paint skyPaint;
    private Paint cloudPaint;

    private RectF farSideOfTheMoon;
    private RectF nearSideOfTheMoon;
    private RectF cloudRect;
    private RectF cloud;
    private RectF sky;

    private int skyRedTop = 90;
    private int skyGreenTop = 100;
    private int skyBlueTop = 190;

    private int skyRedBottom = 40;
    private int skyGreenBottom = 50;
    private int skyBlueBottom = 140;

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

        skyGradient = new LinearGradient(0, 0, 0, canvasHeight,
                                         Color.rgb(skyRedBottom, skyGreenBottom, skyBlueBottom),
                                         Color.rgb(skyRedBottom, skyGreenBottom, skyBlueBottom),
                                         Shader.TileMode.MIRROR);
        skyPaint = new Paint();
        skyPaint.setShader(skyGradient);
        skyPaint.setStyle(Paint.Style.FILL);
        skyPaint.setAntiAlias(true);

        cloudPaint = new Paint();
        cloudPaint.setColor(Color.argb(255, 48, 63, 159));
        cloudPaint.setStyle(Paint.Style.FILL);
        cloudPaint.setAntiAlias(true);

        cloud = new RectF();
        cloudRect = new RectF();
        farSideOfTheMoon = new RectF();
        nearSideOfTheMoon = new RectF();
        sky = new RectF();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO: remove this shit
        skyPaint.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                           Color.rgb(skyRedTop, skyGreenTop, skyBlueTop),
                           Color.rgb(skyRedBottom, skyGreenBottom, skyBlueBottom),
                           Shader.TileMode.MIRROR));
        canvas.drawPaint(skyPaint);

//        skyPaint.setColor(Color.argb(255, skyRedTop, skyGreenTop, skyBlueTop));
//        farSideOfTheMoon.set(xMoon - radius, yMoon - radius, xMoon + radius, yMoon + radius);
//        nearSideOfTheMoon.set((xMoon - 100f) - radius, (yMoon - 100f) - radius, (xMoon - 100f) + radius, (yMoon - 100f) + radius);
        canvas.drawCircle(xSun - 100f, -ySun + 1000f, radius, sunPaint);

//        canvas.drawRect(dp(1), dp(20), dp(10), dp(40), cloudPaint);
//        canvas.drawArc(farSideOfTheMoon, 295f, 220f, false, moonPaint);
//        canvas.drawArc(nearSideOfTheMoon, 335f, 140f, false, moonPaint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sunMoving();
                colorTop();
                colorBottom();
                sunrise();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                sunMoving();
                colorTop();
                colorBottom();
                sunrise();
                invalidate();
                break;
        }
        return true;
    }

    public void sunMoving() {
        if(xSun < 2000f) {
            xSun += distance / sunSpeed;
        } else {
            xSun =- distance / sunSpeed;
            skyRedTop = 90;
            skyGreenTop = 100;
            skyBlueTop = 190;
            skyRedBottom = 40;
            skyGreenBottom = 50;
            skyBlueBottom = 140;
        }
        ySun = (float) (500 * Math.sin(Math.PI * (Math.abs(xSun + originalPosition)) / distance));
    }

    public void sunrise() {
        if(ySun > 150 && ySun < 220) {
            skyRedBottom += 10;
            skyGreenBottom += 5;
        } else if(ySun < 150 && skyRedBottom > 40 && skyGreenBottom > 40) {
            skyRedBottom += -10;
            skyGreenBottom += -5;
        }
    }

    public void colorBottom() {
         if(skyBlueBottom < 240 && ySun > 200 && skyGreenBottom < 180) {
            // day
            skyBlueBottom += 10;
            skyGreenBottom += 10;
        } else if(skyBlueBottom > 140 && ySun < 150 && skyGreenBottom > 50) {
            // night
            skyBlueBottom += -10;
            skyGreenBottom += -10;
        }
    }

    public void colorTop() {
        if(skyBlueTop <= 240 && ySun > 200 && skyGreenTop <= 200 && skyRedTop <= 110) {
            // day
            skyBlueTop += 10;
            skyGreenTop += 10;
            skyRedTop += 5;
        } else if(skyBlueTop >= 190 && ySun <= 150 && skyRedTop >= 90 && skyGreenTop >= 100) {
            // night
            skyBlueTop += -10;
            skyGreenTop += -10;
            skyRedTop += -5;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        canvasWidth = getWidth();
        canvasHeight = getHeight();
    }

}
