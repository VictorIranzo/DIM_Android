package dim.vici.squaresapp;

public class Square {
    public float centerX, centerY, radius;
    public int color;

    public Square(float centerX, float centerY, float radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    // Code extracted more or less from: https://www.geeksforgeeks.org/check-if-a-point-lies-on-or-inside-a-rectangle-set-2/
    public boolean isPointInSquare(float x, float y) {
        return x < centerX + radius && x > centerX - radius && y < centerY + radius && y > centerY - radius;
    }
}
