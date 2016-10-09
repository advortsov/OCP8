package chapter7_concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * This program illustrates the fundamental behavior of sharing data among multiple threads without proper synchronization.
 * Variable counter is accessed by three threads ( t1, t2, and the main thread). Without proper synchronization, when one
 * thread updates counter, there is no guarantee that other threads will see the updated value. Further, the operation
 * counter++ or counter-- is not atomic i.e. it is possible that while one thread is performing counter++, another
 * thread corrupts the value by doing counter--.
 * <p>
 * <p>
 * Because of the above, main thread may see any value for counter. It may even see and print 0.
 *
 * @author aldvc
 * @date 08.10.2016.
 */

public class RunTest {
    public static int counter = 0;

    static class RunnerDec implements Runnable {

        public void run() {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }
    }

    static class RunnerInc implements Runnable {

        public void run() {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        RunnerDec rd = new RunnerDec();
        RunnerInc ri = new RunnerInc();
        Thread t1 = new Thread(rd);
        Thread t2 = new Thread(ri);
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(counter);


        /**
         * 1. In the given code, stream refers to a parallel stream. This implies that the JVM is free to break up the
         * original stream into multiple smaller streams, perform the operations on these pieces in parallel, and finally
         * combine the results.
         2. Here, the stream consists of 8 elements. It is, therefore, possible for a JVM running on an eight core machine
         to split this stream into 8 streams (with one element each) and invoke the filter operation on each of them. If
         this happens, ai will be incremented 8 times.
         3. It is also possible that the JVM decides not to split the stream at all. In this case, it will invoke the
         filter predicate on the first element (which will return true) and then invoke the allMatch predicate (which
         will return false because "old".indexOf("o") is 0). Since allMatch is a short circuiting terminal operation,
         it knows that there is no point in checking other elements because the result will be false anyway. Hence, in
         this scenario, ai will be incremented only once.
         4. The number of pieces that the original stream will be split into could be anything between 1 and 8 and by
         applying the same logic as above, we can say that ai will be incremented any number of times between 1 and 8.
         */
        AtomicInteger ai = new AtomicInteger();
        Stream<String> stream = Stream.of("old", "king", "cole", "was", "a", "merry", "old", "soul").parallel();
        stream.filter(e -> {
            ai.incrementAndGet();
            return e.contains("o");
        }).allMatch(x -> x.indexOf("o") > 0);
        System.out.println("AI = " + ai);

    }
}