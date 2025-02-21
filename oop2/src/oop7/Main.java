package oop7;

public class Main {
    public static void main(String[] args) {
        MyLine line1 = new MyLine(1, 2, 3, 4);
        System.out.println("Line 1: " + line1);
        System.out.println("Begin Point: " + line1.getBegin());
        System.out.println("End Point: " + line1.getEnd());
        System.out.println("Begin X: " + line1.getBeginX());
        System.out.println("Begin Y: " + line1.getBeginY());
        System.out.println("End X: " + line1.getEndX());
        System.out.println("End Y: " + line1.getEndY());
        System.out.println("Begin XY: [" + line1.getBeginXY()[0] + ", " + line1.getBeginXY()[1] + "]");
        System.out.println("End XY: [" + line1.getEndXY()[0] + ", " + line1.getEndXY()[1] + "]");
        System.out.println("Length: " + line1.getLength());
        System.out.println("Gradient: " + line1.getGradient());

        line1.setBeginXY(5, 6);
        line1.setEndXY(7, 8);
        System.out.println("\nAfter setting new Begin and End:");
        System.out.println("Line 1: " + line1);

        MyPoint p1 = new MyPoint(9, 10);
        MyPoint p2 = new MyPoint(11, 12);
        MyLine line2 = new MyLine(p1, p2);
        System.out.println("\nLine 2: " + line2);
        System.out.println("Length: " + line2.getLength());
        System.out.println("Gradient: " + line2.getGradient());

        line2.setBegin(new MyPoint(13, 14));
        line2.setEnd(new MyPoint(15, 16));
        System.out.println("\nAfter setting new Begin and End:");
        System.out.println("Line 2: " + line2);
    }
}

