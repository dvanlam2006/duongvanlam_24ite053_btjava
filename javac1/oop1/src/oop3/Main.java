package oop3;

public class Main {
    public static void main(String[] args) {
        Rectangle rec1 = new Rectangle(1.2f, 3.4f);
        System.out.println(rec1);
        Rectangle rec2 = new Rectangle();
        System.out.println(rec2);
        Rectangle rec3 = new Rectangle(5.6f, 7.8f);
        System.out.println(rec3);
        System.out.println(rec3.getLength());
        System.out.println(rec3.getWidth());
        System.out.println(rec1.getArea());
        System.out.println(rec1.getPerimeter());
    }
}
