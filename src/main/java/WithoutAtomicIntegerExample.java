public class WithoutAtomicIntegerExample {
    public int count = 0;

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for(int i=0;i<10000;i++) {
                count++;
                System.out.println("thread 1::"+count);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<10000;i++) {
                count++;
                System.out.println("thread 2::"+count);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    public static void main(String[] args)  throws Exception {
        WithoutAtomicIntegerExample j1 = new WithoutAtomicIntegerExample();
        j1.doWork();
    }
}
