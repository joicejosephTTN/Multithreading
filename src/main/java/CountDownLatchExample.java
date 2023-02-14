import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample implements Runnable{
    private List<String> list;
    private CountDownLatch countDownLatch;
    CountDownLatchExample(List<String> list, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.list = list;
    }

    @Override
    public void run() {
        list.add("Countdown incremented");
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws Exception {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0;i<3;i++) {
            executorService.submit(new CountDownLatchExample(list, latch));
        }
        latch.await();
        list.add("finished");
        System.out.println("All finished");
        System.out.println(list);
    }
}
