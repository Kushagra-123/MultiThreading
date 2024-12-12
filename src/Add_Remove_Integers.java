import java.util.ArrayList;
import java.util.List;

class Processor{
    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private int value = 0;
    private final Object lock = new Object(); //we can use "ths", in synchronized method as well

    public void producer() throws InterruptedException{
        synchronized (lock){
            while (true){
                if(list.size() == UPPER_LIMIT){
                    System.out.println("Waiting for removing items...");
                    lock.wait(); // will release lock to execute the consumer thread
                }
                else{
                    System.out.println("Adding: "+value);
                    list.add(value);
                    value++;
                    // we can call notify-because other thread will be notified only when it is in waiting state
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException{
        synchronized (lock){
            while (true){
                if(list.size() == LOWER_LIMIT){
                    System.out.println("Waiting for adding items...");
                    value = 0;
                    lock.wait(); //will release lock to execute the producer thread
                }
                else{
                    System.out.println("Removing: "+list.remove(list.size()-1));
                    // we can call notify-because other thread will be notified only when it is in waiting state
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

}
public class Add_Remove_Integers {
    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

    }
}
