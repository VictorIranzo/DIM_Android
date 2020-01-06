package dim.vici.vectors;

import java.util.Collection;

public class Triangle {
    Point p1;
    Point p2;
    Point p3;

    public Triangle(Point p1, Point p2, Point p3)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(Collection<Point> points)
    {
        if (points.size() != 3)
        {
            throw new IllegalArgumentException();
        }

        Point[] pointsArray = points.toArray(new Point[3]);
        this.p1 = pointsArray[0];
        this.p2 = pointsArray[1];
        this.p3 = pointsArray[2];
    }

    public TriangleType getTriangleType()
    {
        float a = this.p1.calculateDistanceToPoint(p2);
        float b = this.p2.calculateDistanceToPoint(p3);
        float c = this.p3.calculateDistanceToPoint(p1);

        if (a == b && b == c)
        {
            return TriangleType.Equilateral;
        }
        else if (a == b || b == c || a == c)
        {
            return TriangleType.Isosceles;
        }

        return TriangleType.Scalene;
    }
}