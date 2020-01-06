package dim.vici.vectors;

import androidx.annotation.Nullable;

public class Point {
    public float x,y;

    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    // Code from: https://www.baeldung.com/java-distance-between-two-points
    public float calculateDistanceToPoint(Point point)
    {
        return (float) Math.sqrt((this.y - point.y) * (this.y - point.y) + (this.x - point.x) * (this.x - point.x));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Float.compare(point.x, x) != 0) return false;
        return Float.compare(point.y, y) == 0;
    }
}
