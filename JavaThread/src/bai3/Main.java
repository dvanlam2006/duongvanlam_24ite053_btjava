package bai3;

public class Main {
    public static void main(String[] args) {
        DiningTable table = new DiningTable();
        Thread chefThread = new Thread(new Chef(table));
        Thread customerThread = new Thread(new Customer(table));

        chefThread.start();
        customerThread.start();
    }
}
