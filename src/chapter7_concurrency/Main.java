package chapter7_concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author advortco
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("begin");
        (new ReadInventoryThread()).start();
        (new Thread(new PrintData())).start();
        (new ReadInventoryThread()).start();
        System.out.println("end");


        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Runnable task1 = () -> System.out.println("Hello Zoo");
        Callable<String> task2 = () -> "Monkey";
        Future<?> result1 = service.schedule(task1, 3, TimeUnit.SECONDS);
        Future<?> result2 = service.schedule(task2, 8, TimeUnit.SECONDS);

        System.out.println(result1.get());
        System.out.println(result2.get());

//        The scheduleAtFixedRate() method creates a new task and submits it to the executor
//        every period, regardless of whether or not the previous task fi nished.
//          The following example executes a Runnable task every second, following an initial fi ve-minute delay:
        service.scheduleAtFixedRate(task1, 5, 1, TimeUnit.SECONDS); //Despite the fact that the task is still running, the
//        ScheduledExecutorService would submit a new task to be started every minute.

//        On the other hand, the scheduleAtFixedDelay() method creates a new task after the
//        previous task has finished. For example, if the first task runs at 12:00
//      and takes five minutes to finish, with a period of 2 minutes, then the second task will start at 12:07.
        service.scheduleWithFixedDelay(task1, 0, 2, TimeUnit.MINUTES);

//        This executor has subtle differences in the way that the
//        scheduleAtFixedRate() performs. For example, recall our previous example in which
//        tasks took five minutes to complete:
        ScheduledExecutorService service1 = Executors.newScheduledThreadPool(10);
        service1.scheduleAtFixedRate(task1, 3, 1, TimeUnit.MINUTES);
//        Whereas with a single-thread executor and a five-minute task execution time, an endless
//        set of tasks would be scheduled over time. With a pooled executor, this can be avoided. If
//        the pool size is sufficiently large, 10 for example, then as each thread finishes, it is returned
//        to the pool and results in new threads available for the next tasks as they come up.



//        ExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        service.scheduleWithFixedDelay(() -> { // w1
//            System.out.println("Open Zoo");
//            return null; // w2
//        }, 0, 1, TimeUnit.MINUTES);
//        Future<?> result = service.submit(() -> System.out.println("Wake Staff")); // w3
//        System.out.println(result.get()); // w4
    }
}
