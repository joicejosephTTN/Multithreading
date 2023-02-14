import java.util.Scanner;

public class WaitNotifyExample {
    public void produce() throws InterruptedException{
        synchronized (this) {
            System.out.println("producer started.......");
            wait();
            System.out.println("producer resumed");
        }
    }

    public void consume() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        synchronized (this) {
            System.out.println("consumer started.......");
            sc.nextLine();
            notify();
            Thread.sleep(3000);
            System.out.println("Now finally going to release the lock");
        }
    }

    public static void main(String[] args) {
        WaitNotifyExample w =new WaitNotifyExample();
        new Thread(()-> {
            try {
                w.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()-> {
            try {
                w.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
