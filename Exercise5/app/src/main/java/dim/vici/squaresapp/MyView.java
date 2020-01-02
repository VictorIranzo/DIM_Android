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

    // Gesture detectors.
    GestureDetector detector;
    ScaleGestureDetector scaleGestureDetector;

    // Stores graphic properties of the drawn line.
    Paint paint = new Paint();

    ArrayList<Square> squares = new ArrayList<Square>();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // TODO: ¿Pass only the list and an action for refreshing?
        GestureListener listener = new GestureListener(this);
        ScaleGestureListener scaleGestureListener = new ScaleGestureListener(this);

        detector = new GestureDetector(context, listener);
        scaleGestureDetector = new ScaleGestureDetector(context, scaleGestureListener);

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
            // Establish the color in which the rectangle is drawn.
            paint.setColor(square.color);

            // Draws the rectangle.
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
        scaleGestureDetector.onTouchEvent(event);

        return true;
    }

    public Square getSelectedSquare(float focusX, float focusY) {
        float minDistance = Float.MAX_VALUE;
        Square nearestSquare = null;

        for(Square square : squares)
        {
            float distance = calculateDistanceBetweenPoints(focusX, focusY, square.centerX, square.centerY);

            if (distance < minDistance)
            {
                minDistance = distance;
                nearestSquare = square;
            }
        }

        return nearestSquare;
    }

    // Code from: https://www.baeldung.com/java-distance-between-two-points
    private static float calculateDistanceBetweenPoints(
            float x1,
            float y1,
            float x2,
            float y2)
    {
        return (float) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
