package oop2;
import java.util.ArrayList;
import java.util.List;

public class PolyLine {
    private List<Point> points = new ArrayList<>();

    public PolyLine() {
    }

    public PolyLine(List<Point> points) {
        this.points.addAll(points);
    }

    public void appendPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public void appendPoint(Point point) {
        points.add(point);
    }

    public double getLength() {
        double length = 0.0;
        for (int i = 1; i < points.size(); i++) {
            length += points.get(i - 1).distanceTo(points.get(i));
        }
        return length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Point point : points) {
            sb.append(point.toString());
        }
        sb.append("}");
        return sb.toString();
    }
}

