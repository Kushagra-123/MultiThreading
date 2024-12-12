import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable{

    @Override
    public void run() {
        System.out.println("Updating and downloading stock related data from web...");

    }
}
public class ScheduleExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService scheduleExecutor = Executors.newScheduledThreadPool(1); //define number of threads in the bracket

        scheduleExecutor.scheduleAtFixedRate(new StockMarketUpdater(), 1000, 2000, TimeUnit.MILLISECONDS);

    }
}
