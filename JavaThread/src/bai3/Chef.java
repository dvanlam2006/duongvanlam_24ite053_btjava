package bai3;

class Chef implements Runnable {
    private final DiningTable table;
    private int dishNumber = 1;

    public Chef(DiningTable table) {
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.cook("Món ăn " + dishNumber++);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

