import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Thread id in output will remain same
//which means only single thread has been executed
class Task implements Runnable{
    private int id;

    public Task(int id) {
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
public class SingleThreadExecutor {
    public static void main(String[] args) {
        //It is a single thread that will execute tasks sequentially
        //one after another
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i<5; i++){
            executorService.execute(new Task(i));
        }

    }
}
