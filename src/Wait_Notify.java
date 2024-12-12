class Process{
    public void produce( ) throws InterruptedException{
        synchronized (this){
            System.out.println("Running the produce method");
            wait(); //will release the lock
            System.out.println("Again in the producer method");
        }
    }
    public void consume() throws InterruptedException{
        synchronized (this) {
            System.out.println("Running the consume method");
            notify(); //will notify other method to complete its execution
        }
    }
}
public class Wait_Notify {
    public static void main(String[] args) {
        Process process = new Process();

        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2= new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
