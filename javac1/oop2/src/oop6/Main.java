package oop6;

public class Main {
    public static void main(String[] args) {
        MyPoint p1 = new MyPoint(3, 4);
        MyPoint p2 = new MyPoint(6, 8);

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);

        System.out.println("Distance from p1 to (0,0): " + p1.distance());
        System.out.println("Distance from p1 to p2: " + p1.distance(p2));
        System.out.println("Distance from p1 to (6,8): " + p1.distance(6, 8));
    }
}
