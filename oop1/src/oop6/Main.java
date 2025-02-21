package oop6;

public class Main {
    public static void main(String[] args) {
        Account a1 = new Account("A101", "Tan Ah Teck", 88);
        System.out.println(a1);;
        Account a2 = new Account("A102", "Kumar");
        System.out.println(a2);
        System.out.println("ID: " + a1.getId());
        System.out.println("Name: " + a1.getName());
        System.out.println("Balance: " + a1.getBalance());


    }
}
