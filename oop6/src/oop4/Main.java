package oop4;

public class Main {
    public static void main(String[] args) {
        // Test MovablePoint
        MovablePoint point = new MovablePoint(0, 0, 2, 3);
        System.out.println("Initial Point: " + point);

        point.moveUp();
        System.out.println("After moveUp: " + point);

        point.moveRight();
        System.out.println("After moveRight: " + point);

        // Test MovableCircle
        MovableCircle circle = new MovableCircle(0, 0, 2, 3, 5);
        System.out.println("\nInitial Circle: " + circle);

        circle.moveDown();
        System.out.println("After moveDown: " + circle);

        circle.moveLeft();
        System.out.println("After moveLeft: " + circle);
    }
}

