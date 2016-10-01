package chapter7_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The key
 * aspect to notice in the code is that a single-thread executor is used, meaning that no task
 * will be executed concurrently. Therefore, the results are valid and predictable with 100 100
 * being the output, and B is the correct answer. If a pooled thread executor was used with at
 * least two threads, then the sheepCount2++ operations could overwrite each other, making
 * the second value indeterminate at the end of the program.
 */
public class SheepManager2 {
    private static AtomicInteger sheepCount1 = new AtomicInteger(0); // w1
    private static int sheepCount2 = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor(); // w2
            for (int i = 0; i < 100; i++)
                service.execute(() ->
                {
                    sheepCount1.getAndIncrement();
                    sheepCount2++;
                }); // w3
            Thread.sleep(100);
            System.out.println(sheepCount1 + " " + sheepCount2);
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
