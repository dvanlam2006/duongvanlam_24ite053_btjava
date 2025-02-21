package oop6;

public class MyPoint {
    private int x = 0;
    private int y = 0;

    // Constructor không tham số
    public MyPoint() {
    }

    // Constructor với tham số x và y
    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter và Setter cho x
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Getter và Setter cho y
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Trả về mảng 2 phần tử {x, y}
    public int[] getXY() {
        return new int[] { x, y };
    }

    // Đặt giá trị x và y
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Trả về chuỗi định dạng "(x,y)"
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    // Tính khoảng cách từ điểm hiện tại đến (x, y) được truyền vào
    public double distance(int x, int y) {
        int dx = this.x - x;
        int dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Tính khoảng cách từ điểm hiện tại đến một đối tượng MyPoint khác
    public double distance(MyPoint another) {
        return distance(another.x, another.y);
    }

    // Tính khoảng cách từ điểm hiện tại đến gốc tọa độ (0, 0)
    public double distance() {
        return Math.sqrt(x * x + y * y);
    }
}
