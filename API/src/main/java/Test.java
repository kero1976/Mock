import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task());

        try {
            System.out.println( LocalDateTime.now() + "  処理開始." );
            System.out.println(future.get(3, TimeUnit.SECONDS));
            System.out.println(LocalDateTime.now() + "  Finished!");
        } catch (TimeoutException e) {
            future.cancel(true);
            System.out.println( LocalDateTime.now()+ "  Terminated!");
        }

        executor.shutdownNow();
    }
}

class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(4000); // Just to demo a long running task of 4 seconds.
        return  LocalDateTime.now() + "Ready!";
    }
}