package org.flamie.moon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;

import org.flamie.moon.objects.Sun;

public class MoonView extends View {

    private int canvasHeight;

    private LinearGradient skyGradient;
    private Paint sunPaint;
    private Paint skyPaint;

    private Sun sun;

    public MoonView(Context context) {
        super(context);
        sun = new Sun();

        sunPaint = new Paint();
        sunPaint.setColor(Color.YELLOW);
        sunPaint.setStyle(Paint.Style.FILL);
        sunPaint.setAntiAlias(true);

        skyGradient = new LinearGradient(0, 0, 0, canvasHeight,
                Color.rgb(sun.getSkyRedBottom(), sun.getSkyGreenBottom(), sun.getSkyBlueBottom()),
                Color.rgb(sun.getSkyRedBottom(), sun.getSkyGreenBottom(), sun.getSkyBlueBottom()),
                Shader.TileMode.MIRROR);
        skyPaint = new Paint();
        skyPaint.setShader(skyGradient);
        skyPaint.setStyle(Paint.Style.FILL);
        skyPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO: remove this shit
        skyPaint.setShader(new LinearGradient(0, 0, 0, canvasHeight,
                           Color.rgb(sun.getSkyRedTop(), sun.getSkyGreenTop(), sun.getSkyBlueTop()),
                           Color.rgb(sun.getSkyRedBottom(), sun.getSkyGreenBottom(), sun.getSkyBlueBottom()),
                           Shader.TileMode.MIRROR));
        canvas.drawPaint(skyPaint);
        canvas.drawCircle(sun.getxSun() - 100f, -sun.getySun() + 1000f, 50f, sunPaint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sun.sunMoving();
                sun.colorTop();
                sun.colorBottom();
                sun.sunrise();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                sun.sunMoving();
                sun.colorTop();
                sun.colorBottom();
                sun.sunrise();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        canvasHeight = getHeight();
    }

}
