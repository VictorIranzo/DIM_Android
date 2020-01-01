package dim.vici.multiparallelcurvilineapp;

public class Line {
    float startX, startY, stopX, stopY;
    int color;

    public Line(float startX, float startY, int color) {
        this.startX = startX;
        this.startY = startY;
        this.color = color;

        this.stopX = -1;
        this.stopY = -1;
    }
}
