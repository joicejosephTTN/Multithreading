import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorExample implements Runnable {

    private int id;
    ExecutorExample() {

    }
    ExecutorExample(int id){
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println("Started " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finished " + id);
    }

    public static void main(String[] args) throws Exception {
        Long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i=0;i<5;i++) {
            executorService.submit(new ExecutorExample(i));
        }
        executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        executorService.shutdown();
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
