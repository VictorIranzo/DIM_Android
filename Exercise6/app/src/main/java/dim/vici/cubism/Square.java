package dim.vici.cubism;

public class Square {
    public float radius;
    public int color;
    public Point center;
    public boolean useStroke;

    public Square(Point center, float radius, int color, boolean useStroke) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.useStroke = useStroke;
    }

    // Code extracted more or less from: https://www.geeksforgeeks.org/check-if-a-point-lies-on-or-inside-a-rectangle-set-2/
    public boolean isPointInSquare(float x, float y) {
        return x < center.x + radius && x > center.x - radius && y < center.y + radius && y > center.y - radius;
    }
}
