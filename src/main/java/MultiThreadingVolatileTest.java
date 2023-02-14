public class MultiThreadingVolatileTest {
    public static volatile int count = 0;

    static class Test1 implements Runnable{
        public void run() {
            int local1 = count;
            while(local1 < 5) {
                if (local1 != count) {
                    System.out.println("hi inside test1  " + local1+"..."+count);
                    local1 = count;
                }
            }
        }
    }

    static class Test2 implements Runnable{
        public void run() {
            int local2 = count;
            while(count < 5) {
//                System.out.println("hi inside bottom test2  "+local2);
                count = ++local2;
                System.out.println("test2 value updated for count  "+count);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Test1());
        Thread t2 = new Thread(new Test2());
        t1.start();
        t2.start();
    }
}
