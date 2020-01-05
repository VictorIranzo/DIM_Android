package dim.vici.cubism;

import android.graphics.Path;

import java.util.ArrayList;

public class Figure {
    ArrayList<Point> points;
    Point firstPoint;
    boolean useStroke;
    int strokeWidth;
    int color;

    public Figure(Point firstPoint, int color, boolean useStroke, int strokeWidth)
    {
        this.firstPoint = firstPoint;
        points = new ArrayList<Point>();

        this.color = color;
        this.useStroke = useStroke;
        this.strokeWidth = strokeWidth;
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

    public boolean isPointInFigure(Point pointToCheck)
    {
        // Code from: https://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon
        int i, j;
        boolean result = false;

        for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            if ((points.get(i).y > pointToCheck.y) != (points.get(j).y > pointToCheck.y) &&
                    (pointToCheck.x < (points.get(j).x - points.get(i).x) * (pointToCheck.y - points.get(i).y) / (points.get(j).y - points.get(i).y) + points.get(i).x)) {
                result = !result;
            }
        }
        return result;
    }
}