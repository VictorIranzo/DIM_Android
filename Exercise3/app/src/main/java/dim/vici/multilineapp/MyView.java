package dim.vici.multiparallelcurvilineapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MyView extends View {

    // Generate random numbers.
    Random randomNumberGenerator = new Random();

    // Stores graphic properties of the drawn line.
    Paint paint = new Paint();

    // Stores the initial and final position of the line.
    float prevX, prevY, newX, newY;

    // Stores the the color in which the line is drawn.
    int color = Color.BLACK;

    ArrayList<Line> lines = new ArrayList<Line>();

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
        for (Line line : lines)
        {
            // Establish the color in which the line is drawn.
            paint.setColor(line.color);

            // Draws the line.
            canvas.drawLine(line.startX, line.startY, line.stopX, line.stopY, this.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                this.prevX = event.getX();
                this.prevY = event.getY();

                // Updates the color, that is an integer.
                // It is only called there because it is the starting point of the drawing action.
                this.color = randomNumberGenerator.nextInt();

                break;
            case MotionEvent.ACTION_MOVE:
                this.newX = event.getX();
                this.newY = event.getY();

                // Invalidates the view to show the drawn line.
                this.invalidate();

                break;
            case MotionEvent.ACTION_UP:
                Line newLine = new Line(this.prevX, this.prevY, this.newX, this.newY, this.color);
                lines.add(newLine);

                this.prevX = -1;
                this.prevY = -1;
                this.newX = -1;
                this.newY = -1;

                // Invalidates the view to show the drawn line.
                // This line is required in order to remove the drawn line when the touch ends.
                this.invalidate();

                break;
        }

        return true;
    }
}
