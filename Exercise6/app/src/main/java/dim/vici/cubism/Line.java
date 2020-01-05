package dim.vici.cubism;

import java.util.ArrayList;

public class Line {
    ArrayList<Point> points;

    int color;

    public Line(float startX, float startY, int color) {
        this.color = color;

        points = new ArrayList<Point>();
        points.add(new Point(startX, startY));
    }
}
