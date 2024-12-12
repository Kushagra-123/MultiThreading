import java.awt.*;

class Thread1 implements Runnable{
    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon thread is getting executed!!");
        }

    }
}

class Thread2 implements Runnable{
    public void run(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Normal thread is getting executed!!");

    }
}

public class DaemonThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());

        t1.setDaemon(true);
        t2.setDaemon(false); //default

        t1.start();
        t2.start();
    }
}






