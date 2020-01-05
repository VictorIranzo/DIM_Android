package dim.vici.cubism;

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
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

public class MyView extends View {

    public TypeDraw TypeDraw = dim.vici.cubism.TypeDraw.SQUARES;
    public int SelectedColor = android.graphics.Color.BLACK;

    // Gesture detectors.
    GestureDetector detector;
    ScaleGestureDetector scaleGestureDetector;

    // Stores graphic properties of the drawn line.
    Paint paint = new Paint();

    Paint paintForFigureFirstPoint = new Paint();

    Hashtable<Integer, Line> linesInProcess = new Hashtable<Integer, Line>();
    ArrayList<Line> linesFinished = new ArrayList<Line>();

    ArrayList<Square> squares = new ArrayList<Square>();

    ArrayList<Figure> figuresFinished = new ArrayList<Figure>();
    Figure figureInProcess;

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
        paint.setColor(this.SelectedColor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        paintForFigureFirstPoint.setColor(Color.RED);
        paintForFigureFirstPoint.setStrokeWidth(12f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawSquares(canvas);

        drawLines(canvas, linesInProcess.values());
        drawLines(canvas, linesFinished);

        drawFigures(canvas);
    }

    private void drawFigures(Canvas canvas) {
        if (figureInProcess != null)
        {
            canvas.drawPoint(figureInProcess.firstPoint.x, figureInProcess.firstPoint.y, this.paintForFigureFirstPoint);

            for (Point point : figureInProcess.points)
            {
                canvas.drawPoint(point.x, point.y, this.paint);
            }
        }

        for(Figure figure : figuresFinished)
        {
            canvas.drawPath(figure.getPath(), this.paint);
        }
    }

    private void drawLines(Canvas canvas, Collection<Line> linesToDraw) {
        // Solution found it in: https://stackoverflow.com/questions/16748146/android-canvas-drawline
        for (Line line : linesToDraw)
        {
            // Establish the color in which the line is drawn.
            paint.setColor(line.color);

            for (int i = 0; i < line.points.size() - 1; i++)
            {
                Point startPoint = line.points.get(i);
                Point endPoint = line.points.get(i+1);

                // Draws the line.
                canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, this.paint);
            }
        }
    }

    private void drawSquares(Canvas canvas) {
        for (Square square : squares) {
            // Establish the color in which the rectangle is drawn.
            paint.setColor(square.color);

            // Draws the rectangle.
            canvas.drawRect(
                square.center.x - square.radius,
                square.center.y + square.radius,
                square.center.x + square.radius,
                square.center.y - square.radius,
                this.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(this.TypeDraw)
        {
            case FREE:
                return onTouchFreeDraw(event);
            case SQUARES:
                return onTouchSquares(event);
            case FIGURE:
                return onTouchFigure(event);
        }

        return true;
    }

    private boolean onTouchFreeDraw(MotionEvent event) {
        int pointerIndex = event.getPointerId(event.getActionIndex());

        switch(event.getActionMasked())
        {
            // Starts a new pointer (main or additional one).
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                linesInProcess.put(pointerIndex, new Line(event.getX(pointerIndex), event.getY(pointerIndex), this.SelectedColor));

                break;
            case MotionEvent.ACTION_MOVE:
                for (Map.Entry<Integer, Line> lineEntry : linesInProcess.entrySet())
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
                linesFinished.add(linesInProcess.get(pointerIndex));

                // Removes the line for the in process collection.
                linesInProcess.remove(pointerIndex);

                // Invalidates the view to show the drawn line.
                // This line is required in order to remove the drawn line when the touch ends.
                this.invalidate();

                break;
        }

        return true;
    }

    private boolean onTouchSquares(MotionEvent event) {
        detector.onTouchEvent(event);

        scaleGestureDetector.onTouchEvent(event);

        if (scaleGestureDetector.isInProgress()) {
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                draggedSquare = getSelectedSquare(event.getX(), event.getY());

                // If the touch point is not in the diameter of the square, not drag.
                if (draggedSquare == null || !draggedSquare.isPointInSquare(event.getX(), event.getY())) {
                    draggedSquare = null;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (draggedSquare == null) {
                    return true;
                }

                draggedSquare.center.x = event.getX();
                draggedSquare.center.y = event.getY();

                this.invalidate();

                break;
            case MotionEvent.ACTION_UP:
                draggedSquare = null;

                break;
        }

        return true;
    }

    private boolean onTouchFigure(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                Point point = new Point(event.getX(), event.getY());

                if (figureInProcess == null)
                {
                    figureInProcess = new Figure(point);
                }
                else
                {
                    if (figureInProcess.isClosingFigure(point))
                    {
                        figuresFinished.add(figureInProcess);
                        figureInProcess = null;
                    }
                    else
                    {
                        figureInProcess.points.add(point);
                    }
                }

                this.invalidate();

                break;
        }

        return true;
    }

    Square draggedSquare = null;

    public Square getSelectedSquare(float focusX, float focusY) {
        float minDistance = Float.MAX_VALUE;
        Square nearestSquare = null;

        Point focusPoint = new Point(focusX, focusY);

        for(Square square : squares)
        {
            float distance = focusPoint.calculateDistanceToPoint(square.center);

            if (distance < minDistance)
            {
                minDistance = distance;
                nearestSquare = square;
            }
        }

        return nearestSquare;
    }
}
