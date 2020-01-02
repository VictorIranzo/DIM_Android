package dim.vici.squaresapp;

import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Random;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private MyView view;

    // Generate random numbers.
    Random randomNumberGenerator = new Random();

    public GestureListener(MyView view)
    {
        this.view = view;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        view.squares.add(
            new Square(e.getX(), e.getY(), 50, randomNumberGenerator.nextInt()));

        view.invalidate();

        return true;
    }
}