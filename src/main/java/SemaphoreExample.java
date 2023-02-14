import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {
    int id;
    SemaphoreExample(int id) {
        this.id = id;
    }
    SemaphoreExample(){

    }
    Semaphore semaphore = new Semaphore(3, true);
    int connections = 0;
    public void connect() throws InterruptedException {
        semaphore.acquire();
        System.out.println("lock acquired by thread"+ Thread.currentThread().getName());
        Thread.sleep(2000);
        semaphore.release();
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreExample semaphoreExample = new SemaphoreExample();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++) {
            executorService.submit(() -> {
                try {
                    semaphoreExample.connect();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
