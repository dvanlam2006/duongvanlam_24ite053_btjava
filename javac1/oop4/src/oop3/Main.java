package oop3;

public class Main {
    public static void main(String[] args) {
        Point2D p2d = new Point2D();
        System.out.println("Default Point2D: " + p2d);

        p2d.setX(1.5f);
        p2d.setY(2.5f);
        System.out.println("Updated Point2D: " + p2d);

        Point2D p2dParam = new Point2D(3.5f, 4.5f);
        System.out.println("Parameterized Point2D: " + p2dParam);

        Point3D p3d = new Point3D();
        System.out.println("Default Point3D: " + p3d);

        p3d.setXYZ(5.5f, 6.5f, 7.5f);
        System.out.println("Updated Point3D: " + p3d);

        Point3D p3dParam = new Point3D(8.5f, 9.5f, 10.5f);
        System.out.println("Parameterized Point3D: " + p3dParam);
    }
}

    