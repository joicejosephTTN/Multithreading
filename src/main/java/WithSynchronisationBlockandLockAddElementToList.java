import java.util.ArrayList;
import java.util.List;

public class WithSynchronisationBlockandLockAddElementToList {
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();

    Object lock1 = new Object();
    Object lock2 = new Object();
    public void doWork1() throws InterruptedException{
        synchronized (lock1) {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(1);
                list1.add(i);
            }
        }
    }
    public void doWork2() throws InterruptedException {
        synchronized (lock2) {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(1);
                list2.add(i);
            }
        }
    }

    public static void main(String[] args)  throws InterruptedException {
        WithSynchronisationBlockandLockAddElementToList j1 = new WithSynchronisationBlockandLockAddElementToList();
        Thread t1 = new Thread(()-> {
            try {
                j1.doWork1();
                j1.doWork2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()-> {
            try {
                j1.doWork1();
                j1.doWork2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Long end = System.currentTimeMillis();
        System.out.println((end-start));
    }
}
