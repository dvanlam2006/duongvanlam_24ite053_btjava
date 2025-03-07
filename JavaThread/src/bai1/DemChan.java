package bai1;

public class DemChan extends Thread {
    public void run() {
        for(int i = 0; i<=10; i+=2){
            System.out.println("So chan: "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
