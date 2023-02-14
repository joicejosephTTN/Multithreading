import java.util.ArrayList;
import java.util.List;

public class WithoutSynchronisationAddElementToList {
    public List<Integer> list1 = new ArrayList<>();
    public void doWork() {
        for(int i=0;i<1000;i++) {
            list1.add(i);
        }
    }

    public static void main(String[] args)  throws Exception {
        WithoutSynchronisationAddElementToList j1 = new WithoutSynchronisationAddElementToList();
        Thread t1 = new Thread(()-> {
           j1.doWork();
        });
        Thread t2 = new Thread(()-> {
            j1.doWork();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(j1.list1.size());
    }
}
