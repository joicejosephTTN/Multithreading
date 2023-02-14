import java.util.concurrent.atomic.AtomicInteger;

public class WithAtomicIntegerExample {
    public AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.getAndIncrement();
    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for(int i=0;i<10000;i++) {
               increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<10000;i++) {
                increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    public static void main(String[] args)  throws Exception {
        WithAtomicIntegerExample j1 = new WithAtomicIntegerExample();
        j1.doWork();
    }
}
