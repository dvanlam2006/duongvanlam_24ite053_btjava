package oop2;

public class Main {
    public static void main(String[] args) {
        Cylinder cylinder = new Cylinder(2.5, "red", 5.0);
        System.out.println(cylinder);
        System.out.println("Volume: " + cylinder.getVolume());
    }
}
