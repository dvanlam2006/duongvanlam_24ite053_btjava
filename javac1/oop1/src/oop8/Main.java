package oop8;

public class Main {
    public static void main(String[] args) {
        Time time  = new Time(1, 2, 3);
        System.out.println(time);
        time.setTime(2, 3, 4);
        System.out.println(time);
        System.out.println(time.nextSecond());
        System.out.println(time.previousMinute());
    }
}
