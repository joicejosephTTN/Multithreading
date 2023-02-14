import java.util.concurrent.atomic.AtomicInteger;

public class SynchronisationCounterExample {
    public int count =0;

    public synchronized void increment() {
        count++;
    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for(int i=0;i<100000;i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<100000;i++) {
                increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
//        t2.join();
        System.out.println(count);
        Thread.sleep(2000);
        System.out.println("Now count is ::" + count);
    }

    public static void main(String[] args)  throws Exception {
        SynchronisationCounterExample j1 = new SynchronisationCounterExample();
        j1.doWork();
    }
}
