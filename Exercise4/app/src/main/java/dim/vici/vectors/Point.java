package dim.vici.vectors;

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
}
