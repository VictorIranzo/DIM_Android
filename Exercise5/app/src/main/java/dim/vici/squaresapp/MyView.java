package dim.vici.squaresapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {

    // Gesture detector.
    GestureDetector detector;
    ScaleGestureDetector scaleGestureDetector;

    // Stores graphic properties of the drawn line.
    Paint paint = new Paint();

    ArrayList<Square> squares = new ArrayList<Square>();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        GestureListener listener = new GestureListener(this);
        ScaleGestureListener scaleGestureListener = new ScaleGestureListener();

        detector = new GestureDetector(context, listener);
        ////scaleGestureDetector = new ScaleGestureDetector(context, scaleGestureListener);

        // Establish the graphic properties of the draw.
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Square square : squares) {
            // Establish the color in which the line is drawn.
            paint.setColor(square.color);

            // Draws the line.
            canvas.drawRect(
                square.centerX - square.radius,
                square.centerY - square.radius,
                square.centerX + square.radius,
                square.centerY + square.radius,
                this.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);

        return true;
    }
}
