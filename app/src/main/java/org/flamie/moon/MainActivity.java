package org.flamie.moon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    private class DrawView extends View {

        private float radius = 200f;
        private float x = 500f;
        private float y = 500f;

        private Paint paint;
        private RectF farSideOfTheMoon;
        private RectF nearSideOfTheMoon;

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(5);
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

            canvas.drawArc(farSideOfTheMoon, 295, 220, false, paint);
            canvas.drawArc(nearSideOfTheMoon, 335, 140, false, paint);

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

}
