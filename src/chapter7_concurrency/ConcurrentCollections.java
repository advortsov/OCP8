package chapter7_concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

/**
 * This class
 *
 * @author advortco
 */
public class ConcurrentCollections {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Object> foodData = new HashMap<String, Object>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);
//        for (String key : foodData.keySet())
//            foodData.remove(key);
//        This snippet will throw a ConcurrentModificationException at runtime, since the
//        iterator keyset() is not properly updated after the first element is removed. Changing the
//        first line to use a ConcurrentHashMap will prevent the code from throwing an exception at
//        runtime:
        Map<String, Object> foodData1 = new ConcurrentHashMap<String, Object>();
        foodData1.put("penguin", 1);
        foodData1.put("flamingo", 2);
        for (String key : foodData1.keySet())
            foodData1.remove(key);

        // collections:
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("zebra", 52);
        map.put("elephant", 10);
        System.out.println(map.get("elephant"));

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(31);
        System.out.println(queue.peek());
        System.out.println(queue.poll());

        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(10);
        deque.push(4);
        System.out.println(deque.peek());
        System.out.println(deque.pop());

        try {
            BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
            blockingQueue.offer(39);
            blockingQueue.offer(3, 4, TimeUnit.SECONDS);
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Handle interruption
        }
        System.out.println("=================blockingDeque============================");
//        This example creates a LinkedBlockingDeque and assigns it to a BlockingDeque reference.
//        Since BlockingDeque extends Queue, Deque, and BlockingQueue, all of the previously
//        defined queue methods are available for use.
        try { // 5 91 47 3
            BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
            blockingDeque.offer(91);
            blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
            blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
            blockingDeque.offer(3, 4, TimeUnit.SECONDS);
            System.out.println(blockingDeque.poll());
            System.out.println(blockingDeque.poll(950, TimeUnit.MILLISECONDS));
            System.out.println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
            System.out.println(blockingDeque.pollLast(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            // Handle interruption
        }

        System.out.println("===============addAndPrintItems=====================");
//        addAndPrintItems(new LinkedBlockingDeque<>(Arrays.asList(1, 6, 44, 3, 0, 7)));
        addAndPrintItems(new LinkedBlockingDeque<>());

        System.out.println();
        System.out.println("===================CopyOnWriteArrayList==========================");

        //CopyOnWrite
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        for (Integer item : list) {
            System.out.print(item + " ");
            list.add(9);
        }
        System.out.println();
        System.out.println("Size: " + list.size()); //Alternatively, if we had
//        used a regular ArrayList object, a ConcurrentModificationException would have been
//        thrown at runtime. With either class, though, we avoid entering an infinite loop in which
//        elements are constantly added to the array as we iterate over them.

//        While the methods in Table 7.12 synchronize access to the data elements, such as
//        the get() and set() methods, they do not synchronize access on any iterators that you
//        may create from the synchronized collection. Therefore, it is imperative that you use a
//        synchronization block if you need to iterate over any of the returned collections in Table
//        7.12, as shown in the following example:
        List<Integer> synchronizedList = Collections.synchronizedList(
                new ArrayList<>(Arrays.asList(4, 3, 52)));
        synchronized (synchronizedList) {
            for (int data : synchronizedList)
                System.out.print(data + " ");
        }

        Map<String, Object> foodData2 = new HashMap<String, Object>();
        foodData2.put("penguin", 1);
        foodData2.put("flamingo", 2);
        Map<String, Object> synchronizedFoodData = Collections.synchronizedMap(foodData);
//        for (String key : synchronizedFoodData.keySet()) // throws a ConcurrentModificationException at runtime, whereas our
//            example that used ConcurrentHashMap did not.
//            synchronizedFoodData.remove(key);

        //         synchronizedFoodData.keySet().forEach(synchronizedFoodData::remove);


        System.out.println("=================printConstants================");
        printConstants();
        System.out.println("===========================================");

    }

    public static void addAndPrintItems(BlockingDeque<Integer> deque) throws InterruptedException {
        deque.offer(103);
        deque.offerFirst(20, 1, TimeUnit.SECONDS);
        deque.offerLast(85, 7, TimeUnit.HOURS);
        System.out.print(deque.pollFirst(200, TimeUnit.NANOSECONDS));
        System.out.print(" " + deque.pollLast(1, TimeUnit.MINUTES));
    }

    /**
     * Even though the stream is processed in sequential order, the tasks are submitted
     * to a thread executor, which may complete the tasks in any order. Therefore, the output cannot
     * be determined ahead of time and F is correct, making A incorrect. Finally, the thread
     * executor is never shut down;
     */
    public static void printConstants() {
        ExecutorService service = Executors.newScheduledThreadPool(8);
        DoubleStream.of(3.14159, 2.71828) // b1
                .forEach(c -> service.submit( // b2
                        () -> System.out.println(10 * c))); // b3
        service.execute(() -> System.out.println("Printed")); // b4
        service.shutdown();
    }
}
