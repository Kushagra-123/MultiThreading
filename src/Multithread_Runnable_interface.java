class hi implements Runnable
{
    public void run(){
        for(int i = 1; i<=5; i++){
            System.out.println("Hi");

            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
            }
        }
    }
}

class hello implements Runnable
{
    public void run(){
        for(int i = 1; i<=5; i++){
            System.out.println("Hello");

            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
            }
        }
    }
}
public class Multithread_Runnable_interface{
    public static void main(String args[]) throws Exception {
        Runnable obj1 = new hi();
        Runnable obj2 = new hello();

        //we dont have start method for runnable so we will call start with thread class

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        t1.start();
        try{Thread.sleep(10);}catch (Exception e){}
        t2.start();
    }
}
