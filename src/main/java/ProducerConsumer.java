import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
  static BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);
  public static void produce() throws InterruptedException{
      Random  random = new Random();
      while(true) {
          queue.put(random.nextInt(100));
      }
  }
    public static void consume() throws InterruptedException{
        while(true) {
            Thread.sleep(100);
            int val = queue.take();
            System.out.println("taken value "+val+" queue size " + queue.size());
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
