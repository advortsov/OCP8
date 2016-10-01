package chapter7_concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class
 *
 * @author advortco
 */
public class SingleThreadExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        With a single-thread executor, results are guaranteed to be executed in the order in which
//        they are added to the executor service. Notice that the end text is output while our thread
//        executor tasks are still running. This is because the main() method is still an independent
//        thread from the ExecutorService, and it can perform tasks while the other thread is running.
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            service.execute(() -> System.out.println("Printing zoo inventory"));
            service.execute(() -> {
                        for (int i = 0; i < 3; i++)
                            System.out.println("Printing record: " + i);
                    }
            );
            service.execute(() -> System.out.println("Printing zoo inventory"));
            System.out.println("end");


            //
            Future<?> future = service.submit(() -> System.out.println("Hello Zoo"));
//            System.out.println(future.get());

        } finally {
            if (service != null) service.shutdown();
        }
    }
}

