package chapter7_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {
    //    private int sheepCount = 0;
    private AtomicInteger sheepCount = new AtomicInteger(0);

    /**
     * Unlike our previous sample output, the numbers 1 through 10 will always be output. As
     * you might notice, the results are still not ordered, although we’ll get to that soon enough.
     */
    private void incrementAndReport() {
//        System.out.print(sheepCount.incrementAndGet()+" ");
        synchronized (this) {
            System.out.print(sheepCount.incrementAndGet() + " "); // ok!
        }
    }
//    private void incrementAndReport() {
//        System.out.print((++sheepCount) + " ");
//    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 1000; i++)
                service.submit(() -> manager.incrementAndReport());

//                synchronized(manager) { //Does not fix the problem! Can you spot the problem? We’ve
////                    synchronized the creation of the threads but not the execution of the threads.
//                    service.submit(() -> manager.incrementAndReport());
//                }

        } finally {
            if (service != null) service.shutdown();
        }
    }
}