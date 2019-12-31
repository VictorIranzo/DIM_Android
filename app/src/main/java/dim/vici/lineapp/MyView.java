package dim.vici.lineapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

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
        // Establish the color in which the line is drawn.
        paint.setColor(this.color);

        // Draws the line.
        canvas.drawLine(this.prevX, this.prevY, this.newX,this.newY, this.paint);
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
                this.prevX = -1;
                this.prevY = -1;
                this.newX = -1;
                this.newY = -1;

                break;
        }

        return true;
    }
}
