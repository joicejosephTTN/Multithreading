import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService e = Executors.newCachedThreadPool();
        Future<Integer> f = e.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 10;
            }
        });

        System.out.println(f.get());

    }
}
