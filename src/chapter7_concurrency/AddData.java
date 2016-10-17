package chapter7_concurrency;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AddData {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(() -> 30 + 11);
            System.out.println(result.get(1, TimeUnit.MILLISECONDS)); //Waits if necessary for at most the given time
            // for the computation to complete, and then retrieves its result, if available.
        } finally {
            if (service != null) service.shutdown();
        }
    }
}