public class Synchronization {
    public static int counter = 0;

    //synchronized will make sure that this method will be incremneted
    public static synchronized  void increment(){
        counter++;
    }

    public static void process(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<50; i++){
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<50; i++){
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The counter is: "+counter);
    }

    public static void main(String[] args) {
        process();
    }
}
