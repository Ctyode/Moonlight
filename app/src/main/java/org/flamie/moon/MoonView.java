package org.flamie.moon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

public class MoonView extends View {

    private float radius = 200f;
    private float x = 500f;
    private float y = 500f;

    private Paint paint;
    private RectF farSideOfTheMoon;
    private RectF nearSideOfTheMoon;

    public MoonView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        farSideOfTheMoon = new RectF();
        nearSideOfTheMoon = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        farSideOfTheMoon.set(x - radius, y - radius, x + radius, y + radius);
        nearSideOfTheMoon.set((x - 100f) - radius, (y - 100f) - radius, (x - 100f) + radius, (y - 100f) + radius);

        canvas.drawArc(farSideOfTheMoon, 295f, 220f, false, paint);
        canvas.drawArc(nearSideOfTheMoon, 335f, 140f, false, paint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                invalidate();
                break;
        }
        return true;
    }

}
