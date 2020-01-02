package dim.vici.squaresapp;

import android.view.ScaleGestureDetector;

public class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    private MyView view;

    public ScaleGestureListener(MyView view) {
        this.view = view;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        // 1. Get the selected square.
        Square square = view.getSelectedSquare(detector.getFocusX(), detector.getFocusY());

        if (square == null)
        {
            return true;
        }

        // 2. Resize square.
        square.radius *= detector.getScaleFactor();

        // 3. Refresh.
        view.invalidate();

        return true;
    }
}
