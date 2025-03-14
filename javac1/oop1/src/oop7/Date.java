package oop7;

import java.util.Arrays;

public class Date {
    private int[] day = new int[31];
    private int[] month = new int[12];
    private int[] year = new int[9999];

    public Date() {}
    public Date(int day, int month, int year) {
        this.day[0] = day;
        this.month[0] = month;
        this.year[0] = year;
    }

    public int[] getDay() {
        return day;
    }

    public void setDay(int[] day) {
        this.day = day;
    }

    public int[] getMonth() {
        return month;
    }

    public void setMonth(int[] month) {
        this.month = month;
    }

    public int[] getYear() {
        return year;
    }

    public void setYear(int[] year) {
        this.year = year;
    }
    public void setDate(int day, int month, int year) {
        this.day[0] = day;
        this.month[0] = month;
        this.year[0] = year;
    }

    @Override
    public String toString() {
        return day[0] + "/" + month[0] + "/" + year[0];
    }
}
