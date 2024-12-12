import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work implements Runnable{
    private int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id "+id+" is in work - thread id: " + Thread.currentThread().getName());
        long duration = (long)(Math.random()*5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class FixedThreadPool {
    public static void main(String[] args) {
        //It is a single thread that will execute tasks sequentially
        //one after another
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i<10; i++){
            executor.execute(new Work(i+1));
        }

    }
}

