class Test1 extends Thread {
   public void run() {
       synchronized (this)
       {
           System.out.println(
                   Thread.currentThread().getName()
                           + "...starts");
           try {
               this.wait();
           }
           catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(
                   Thread.currentThread().getName()
                           + "...notified");
       }
   }
}
class Test2 extends Thread {
    Test1 test1;
    Test2(Test1 test1) {
        this.test1 = test1;
    }
    public void run() {
        synchronized (test1)
        {
            System.out.println(
                    Thread.currentThread().getName()
                            + "...starts");
            try {
                test1.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(
                    Thread.currentThread().getName()
                            + "...notified");
        }
    }
}
class Test3 extends Thread {
    Test1 test1;
    Test3(Test1 test1) {
        this.test1 = test1;
    }
    public void run() {
        synchronized (test1)
        {
            System.out.println(
                    Thread.currentThread().getName()
                            + "...starts");
//            test1.notify();
           test1.notifyAll();
            System.out.println(
                    Thread.currentThread().getName()
                            + "...notified");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class NotifyVsNotifyAll {
    public static void main(String[] args) throws Exception{
        Test1 test1 = new Test1();
        Test2 test2 = new Test2(test1);
        Test3 test3 = new Test3(test1);
        Thread t1 = new Thread(test1, "Thread-1");
        Thread t2 = new Thread(test2, "Thread-2");
        Thread t3 = new Thread(test3, "Thread-3");
        t1.start();
        t2.start();
        Thread.sleep(100);
        t3.start();
    }
}
