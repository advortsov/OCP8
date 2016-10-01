package chapter7_concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class
 *
 * @author advortco
 */
public class ParallelStreams {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .forEachOrdered(s -> System.out.print(s + " ")); // works, but performance is low(


        WhaleDataCalculator calculator = new WhaleDataCalculator();
        // Define the data
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) data.add(i);
        // Process the data
        long start = System.currentTimeMillis();
        calculator.processAllData(data);
        double time = (System.currentTimeMillis() - start) / 1000.0;
        // Report results
        System.out.println("\nTasks completed in: " + time + " seconds");

        // Avoiding Stateful Operations
        System.out.println("=========================Avoiding Stateful Operations==================================");
        List<Integer> data1 = Collections.synchronizedList(new ArrayList<>());
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream()
                .map(i -> {
                    data1.add(i);
                    return i;
                }) // AVOID STATEFUL LAMBDA EXPRESSIONS!
                .forEachOrdered(i -> System.out.print(i + " "));
        System.out.println();
        for (Integer e : data1) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println("===========================================================");
//        When you call findAny() on a parallel stream, the JVM selects the first thread to finish the
//        task and retrieves its data:
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().findAny().get());

        System.out.println("===========================================================");

        System.out.println(Arrays.asList('w', 'o', 'l', 'f')
                .stream()
                .reduce("", (c, s1) -> c + s1,
                        (s2, s3) -> s2 + s3));

        System.out.println("=======================NOT AN ASSOCIATIVE ACCUMULATOR==============================");
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallelStream()
                .reduce(0, (a, b) -> (a - b))); // NOT AN ASSOCIATIVE ACCUMULATOR


        System.out.println("=======================an identity parameter that is not truly an identity value==============================");
//        You can see other problems if we use an identity parameter that is not truly an identity value.
        System.out.println(Arrays.asList("w", "o", "l", "f")
                .parallelStream()
                .reduce("X", String::concat));


        System.out.println("==================================================");
//        Also like reduce(), the accumulator and combiner operations must be associative and
//        stateless, with the combiner operation compatible with the accumulator operator, as previously discussed. In this manner, the three-argument version of collect() can be performed
//        as a parallel reduction, as shown in the following example:
        Stream<String> stream = Stream.of("a", "c", "b", "d").parallel();
        SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new, Set::add,
                Set::addAll);
        System.out.println(set); // [a, b, c, d]
//        Recall that elements in a ConcurrentSkipListSet are sorted according to their natural
//        ordering.

        System.out.println("==================================================");
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, String> map = ohMy
                .collect(Collectors.toConcurrentMap(String::length, k -> k,
                        (s1, s2) -> s1 + "," + s2));
        System.out.println(map); // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap


        System.out.println("==================================================");

        Stream<String> ohMy1 = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> map1 = ohMy1.collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(map1); // {5=[lions, bears], 6=[tigers]}
        System.out.println("==================================================");


        System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("==================================================");

    }

    static class WhaleDataCalculator {
        public int processRecord(int input) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // Handle interrupted exception
            }
            return input + 1;
        }

        public void processAllData(List<Integer> data) {
//            data.stream().map(a -> processRecord(a)).count(); // 40 sec
            data.parallelStream().map(a -> processRecord(a)).count(); // 10 sec
        }

    }
}
