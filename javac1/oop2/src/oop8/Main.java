package oop8;

public class Main {
    public static void main(String[] args) {

        MyTriangle triangle1 = new MyTriangle(0, 0, 3, 0, 3, 4);
        System.out.println("Triangle 1: " + triangle1);
        System.out.println("Perimeter: " + triangle1.getPerimeter());
        System.out.println("Type: " + triangle1.getType());

        MyPoint p1 = new MyPoint(1, 1);
        MyPoint p2 = new MyPoint(4, 1);
        MyPoint p3 = new MyPoint(1, 4);
        MyTriangle triangle2 = new MyTriangle(p1, p2, p3);
        System.out.println("\nTriangle 2: " + triangle2);
        System.out.println("Perimeter: " + triangle2.getPerimeter());
        System.out.println("Type: " + triangle2.getType());

        MyTriangle triangle3 = new MyTriangle(0, 0, 2, 3, 4, 0);
        System.out.println("\nTriangle 3: " + triangle3);
        System.out.println("Perimeter: " + triangle3.getPerimeter());
        System.out.println("Type: " + triangle3.getType());
    }
}

