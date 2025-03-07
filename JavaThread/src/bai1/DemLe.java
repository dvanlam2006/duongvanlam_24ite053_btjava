package bai1;

public class DemLe extends Thread {
    public void run(){
        for(int i=1;i<=10;i=i+2){
            System.out.println("So le :"+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
