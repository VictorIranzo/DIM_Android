package dim.vici.multiparallelcurvilineapp;

public class Line {
    float startX, startY, stopX, stopY;
    int color;

    public Line(float startX, float startY, float stopX, float stopY, int color) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
        this.color = color;
    }
}
