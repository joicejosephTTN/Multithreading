public class InterruptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()-> {
            for (int i=0;i<100000;i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread inturrpted"+ Thread.currentThread().getState());
                    break;
                }
            }
        });
        System.out.println(t.getState());
        t.start();
        t.interrupt();
        t.join();
        System.out.println("yeahhhh");
        System.out.println(t.getState());
    }
}
