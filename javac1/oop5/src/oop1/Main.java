package oop1;

public class Main {
    public static void main(String[] args) {
        // Kiểm tra Line và Point (Composition)
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Line line = new Line(p1, p2);
        System.out.println(line);

        // Kiểm tra LineSub và Point (Kế thừa)
        LineSub lineSub = new LineSub(0, 0, 5, 5);
        System.out.println(lineSub);
    }
}
