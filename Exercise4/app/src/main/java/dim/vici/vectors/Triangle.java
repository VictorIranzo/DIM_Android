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

    public Triangle TransformToIsosceles()
    {
        if (this.getTriangleType() != TriangleType.Scalene)
        {
            return this;
        }

        float distanceP1toP2 = this.p1.calculateDistanceToPoint(p2);
        float distanceP2toP3 = this.p2.calculateDistanceToPoint(p3);
        float distanceP3toP1 = this.p3.calculateDistanceToPoint(p1);

        float differencesBetweenSegmentsOfP1 = Math.abs(distanceP1toP2 - distanceP3toP1);
        float differencesBetweenSegmentsOfP2 = Math.abs(distanceP1toP2 - distanceP2toP3);
        float differencesBetweenSegmentsOfP3 = Math.abs(distanceP3toP1 - distanceP2toP3);

        Point pointToMove = null;
        Point[] remainingPoints = new Point[2];

        if (differencesBetweenSegmentsOfP1 <= differencesBetweenSegmentsOfP2 && differencesBetweenSegmentsOfP1 <= differencesBetweenSegmentsOfP3)
        {
            pointToMove = p2;
            remainingPoints[0] = p1;
            remainingPoints[1] = p3;
        }

        if (differencesBetweenSegmentsOfP2 <= differencesBetweenSegmentsOfP1 && differencesBetweenSegmentsOfP2 <= differencesBetweenSegmentsOfP3)
        {
            pointToMove = p1;
            remainingPoints[0] = p2;
            remainingPoints[1] = p3;
        }

        if (differencesBetweenSegmentsOfP3 <= differencesBetweenSegmentsOfP1 && differencesBetweenSegmentsOfP3 <= differencesBetweenSegmentsOfP2)
        {
            pointToMove = p1;
            remainingPoints[0] = p2;
            remainingPoints[1] = p3;
        }

        return null;
    }
}