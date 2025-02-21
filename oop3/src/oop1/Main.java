package oop1;

public class Main {
    public static void main(String[] args) {
        MyComplex c1 = new MyComplex(0.0, 0.0);
        MyComplex c2 = new MyComplex(0.0, 0.0);
        MyComplex c3 = new MyComplex(7.0, 8.0);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        System.out.println(c1.isReal());
        System.out.println(c2.isReal());
        System.out.println(c3.isReal());
        System.out.println(c1.isImaginary());
        System.out.println(c2.isImaginary());
        System.out.println(c3.isImaginary());

        System.out.println(c1.equals(c2));

        System.out.println(c1.magnitude());
    }
}
