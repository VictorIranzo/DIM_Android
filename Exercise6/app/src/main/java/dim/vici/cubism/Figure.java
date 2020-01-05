package dim.vici.cubism;

import android.graphics.Path;

import java.util.ArrayList;

public class Figure {
    ArrayList<Point> points;
    Point firstPoint;

    public Figure(Point firstPoint)
    {
        this.firstPoint = firstPoint;
        points = new ArrayList<Point>();
    }

    public Path getPath()
    {
        // Solution from: https://stackoverflow.com/a/3501643
        Path path = new Path();

        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(firstPoint.x, firstPoint.y);

        for (Point point : points)
        {
            path.lineTo(point.x, point.y);
        }

        path.lineTo(firstPoint.x, firstPoint.y);

        path.close();

        return path;
    }

    public boolean isClosingFigure(Point point) {
        return firstPoint.calculateDistanceToPoint(point) < 40;
    }
}
