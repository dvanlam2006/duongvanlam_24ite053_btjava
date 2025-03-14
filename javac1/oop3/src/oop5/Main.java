package oop5;

public class Main {
        public static void main(String[] args) {
            MyDate date1 = new MyDate(2023, 2, 28);
            System.out.println(date1);

            date1.nextDay();
            System.out.println(date1);

            date1.nextDay();
            System.out.println(date1);

            date1.nextMonth();
            System.out.println(date1);

            date1.nextYear();
            System.out.println(date1);

            date1.previousDay();
            System.out.println(date1);

            date1.previousMonth();
            System.out.println(date1);

            date1.previousYear();
            System.out.println(date1);
        }
    }

