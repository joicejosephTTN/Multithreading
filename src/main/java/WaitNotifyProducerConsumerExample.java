import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class WaitNotifyProducerConsumerExample {
    Object lock = new Object();
    LinkedList<Integer> list = new LinkedList<>();
    public void produce() throws InterruptedException{
        int value = 0;
        while(true) {
            synchronized (lock) {
                System.out.println("producer started.......");
                while(list.size() == 10) {
                    lock.wait();
                }
                list.add(++value);
                System.out.println("producer resumed");
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                System.out.println("consumer started.......");
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.println("Value removed " + list.removeFirst());
                lock.notify();
                Thread.sleep(random.nextInt(1000));
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyProducerConsumerExample w =new WaitNotifyProducerConsumerExample();
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
