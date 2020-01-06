package dim.vici.vectors;

import android.text.method.HideReturnsTransformationMethod;

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

    public Line GetOrientedLine()
    {
        float distanceP1toP2 = this.p1.calculateDistanceToPoint(p2);
        float distanceP2toP3 = this.p2.calculateDistanceToPoint(p3);
        float distanceP3toP1 = this.p3.calculateDistanceToPoint(p1);

        float differencesBetweenSegmentsOfP1 = Math.abs(distanceP1toP2 - distanceP3toP1);
        float differencesBetweenSegmentsOfP2 = Math.abs(distanceP1toP2 - distanceP2toP3);
        float differencesBetweenSegmentsOfP3 = Math.abs(distanceP3toP1 - distanceP2toP3);

        Point pointOfLineAndTriangle = null;
        Point pointOfLineAndMiddleBase = null;

        if (differencesBetweenSegmentsOfP1 <= differencesBetweenSegmentsOfP2 && differencesBetweenSegmentsOfP1 <= differencesBetweenSegmentsOfP3)
        {
            pointOfLineAndTriangle = p1;
            pointOfLineAndMiddleBase = p2.getMidpoint(p3);
        }

        if (differencesBetweenSegmentsOfP2 <= differencesBetweenSegmentsOfP1 && differencesBetweenSegmentsOfP2 <= differencesBetweenSegmentsOfP3)
        {
            pointOfLineAndTriangle = p2;
            pointOfLineAndMiddleBase = p1.getMidpoint(p3);

        }

        if (differencesBetweenSegmentsOfP3 <= differencesBetweenSegmentsOfP1 && differencesBetweenSegmentsOfP3 <= differencesBetweenSegmentsOfP2)
        {
            pointOfLineAndTriangle = p3;
            pointOfLineAndMiddleBase = p1.getMidpoint(p2);
        }

        return new Line(pointOfLineAndMiddleBase, pointOfLineAndTriangle);
    }
}