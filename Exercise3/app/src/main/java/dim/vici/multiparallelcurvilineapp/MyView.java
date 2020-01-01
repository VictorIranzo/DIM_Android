package dim.vici.multiparallelcurvilineapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class MyView extends View {

    // Generate random numbers.
    Random randomNumberGenerator = new Random();

    // Stores graphic properties of the drawn line.
    Paint paint = new Paint();

    Hashtable<Integer, Line> lines = new Hashtable<Integer, Line>();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Establish the graphic properties of the draw.
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Solution found it in: https://stackoverflow.com/questions/16748146/android-canvas-drawline
        for (Line line : lines.values())
        {
            // Establish the color in which the line is drawn.
            paint.setColor(line.color);

            for (Point point : line.points)
            {
                // Draws the line.
                canvas.drawPoint(point.x, point.y, this.paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getPointerId(event.getActionIndex());

        switch(event.getActionMasked())
        {
            // Starts a new pointer (main or additional one).
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                lines.put(pointerIndex, new Line(event.getX(pointerIndex), event.getY(pointerIndex), randomNumberGenerator.nextInt()));

                break;
            case MotionEvent.ACTION_MOVE:
                for (Map.Entry<Integer, Line> lineEntry : lines.entrySet())
                {
                    try {
                        Line endLine = lineEntry.getValue();

                        endLine.points.add(new Point(event.getX(lineEntry.getKey()), event.getY(lineEntry.getKey())));

                        // Invalidates the view to remove the drawn line.
                        this.invalidate();
                    }
                    catch (IllegalArgumentException args) {}
                }
                break;
            // Ends a pointer (main or additional one).
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                // Removes the line.
                lines.remove(pointerIndex);

                // Invalidates the view to show the drawn line.
                // This line is required in order to remove the drawn line when the touch ends.
                this.invalidate();

                break;

        }

        return true;
    }
}
