package dim.vici.cubism;

import java.util.ArrayList;

public class Line {
    ArrayList<Point> points;

    int color;
    int strokeWidth;

    public Line(float startX, float startY, int color, int strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;

        points = new ArrayList<Point>();
        points.add(new Point(startX, startY));
    }
}
