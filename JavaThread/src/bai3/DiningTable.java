package bai3;

import java.util.LinkedList;
import java.util.Queue;

class DiningTable {
    private final int CAPACITY = 5;
    private final Queue<String> table = new LinkedList<>();

    public synchronized void cook(String dish) throws InterruptedException {
        while (table.size() == CAPACITY) {
            System.out.println("Bàn ăn đầy. Đợi khách hàng ăn...");
            wait();
        }
        table.add(dish);
        System.out.println("Đầu bếp nấu: " + dish);
        notify();
    }

    public synchronized void eat() throws InterruptedException {
        while (table.isEmpty()) {
            System.out.println("Bàn ăn trống. Đợi đầu bếp nấu...");
            wait();
        }
        String dish = table.poll();
        System.out.println("Khách hàng ăn: " + dish);
        notify();
    }
}
