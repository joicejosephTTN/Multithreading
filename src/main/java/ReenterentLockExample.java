import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterentLockExample {
    Lock l = new ReentrantLock();
    private Condition condition = l.newCondition();
    public void thread1() throws InterruptedException{
        l.lock(); // synchronised block
        System.out.println("thread 1");
        condition.await();
        System.out.println("thread 1 await complete");
        Thread.sleep(1000);
        l.unlock();
    }
    public void thread2() throws InterruptedException{
        l.lock();
        System.out.println("got the lock");
        condition.signal();
        l.unlock();
    }

    public static void main(String[] args) throws  Exception{
        ReenterentLockExample reenterentLockExample = new ReenterentLockExample();
        new Thread(()-> {
            try {
                reenterentLockExample.thread1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()-> {
            try {
                reenterentLockExample.thread2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
