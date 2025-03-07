package bai3;

class Customer implements Runnable {
    private final DiningTable table;

    public Customer(DiningTable table) {
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.eat();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

