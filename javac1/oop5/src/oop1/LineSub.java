package oop1;

public class LineSub extends Point {
    private Point end;

    public LineSub(int x, int y, int xEnd, int yEnd) {
        super(x, y);
        this.end = new Point(xEnd, yEnd);
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "LineSub from " + super.toString() + " to " + end;
    }
}

