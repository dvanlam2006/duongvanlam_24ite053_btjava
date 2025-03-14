package oop5;

public class Customer {
    private String name;
    private int id;
    private char gender;
    public Customer(String name, int id, char gender) {
        this.name = name;
        this.id = id;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public char getGender() {
        return gender;
    }
    public String toString() {
        return name + "(" + id + ")";
    }
}
