package oop2;

public class Main {
    public static void main(String[] args) {
        PolyLine polyline = new PolyLine();
        polyline.appendPoint(1, 2);
        polyline.appendPoint(3, 4);
        polyline.appendPoint(5, 6);

        System.out.println(polyline);
        System.out.println("Length: " + polyline.getLength());
    }
}

