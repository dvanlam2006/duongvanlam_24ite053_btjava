package oop5;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(10.0);
        System.out.println("Initial Circle: " + circle);
        System.out.println("Area: " + circle.getArea());
        System.out.println("Perimeter: " + circle.getPerimeter());

        ResizableCircle resizableCircle = new ResizableCircle(10.0);
        System.out.println("Initial ResizableCircle: " + resizableCircle);
        resizableCircle.resize(50);
        System.out.println("After resizing by 50%: " + resizableCircle);
        System.out.println("Area: " + resizableCircle.getArea());
        System.out.println("Perimeter: " + resizableCircle.getPerimeter());

        resizableCircle.resize(200);
        System.out.println("After resizing by 200%: " + resizableCircle);
        System.out.println("Area: " + resizableCircle.getArea());
        System.out.println("Perimeter: " + resizableCircle.getPerimeter());
    }
}
