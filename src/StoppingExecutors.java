import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work2 implements Runnable{
    private int id;

    public Work2(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id "+id+" is in work - thread id: " + Thread.currentThread().getName());
        long duration = (long)(Math.random()*5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class StoppingExecutors {
    public static void main(String[] args) {
        //It is a single thread that will execute tasks sequentially
        //one after another
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            executor.execute(new Work2(i + 1));
        }

        //we prevent executor to execute any further tasks
        executor.shutdown();

        //terminate actual (running) tasks

        try{
            if(!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)){
                executor.shutdownNow();
            }
        } catch (InterruptedException e){
            executor.shutdownNow();
        }

    }
}