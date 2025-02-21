package oop4;

public class Main {
    public static void main(String[] args) {

        Point p = new Point(2.0f, 3.0f);
        System.out.println("Point: " + p);

        MovablePoint mp1 = new MovablePoint(2.0f, 3.0f, 1.0f, 1.5f);
        System.out.println("MovablePoint (Before Move): " + mp1);
        mp1.move();
        System.out.println("MovablePoint (After Move): " + mp1);

        MovablePoint mp2 = new MovablePoint();
        mp2.setXY(5.0f, 5.0f);
        mp2.setSpeed(2.0f, 3.0f);
        System.out.println("MovablePoint (Before Move): " + mp2);
        mp2.move();
        System.out.println("MovablePoint (After Move): " + mp2);
    }
}

