package chapter4_functional_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author advortco
 */
public class Streams {
    public static void main(String[] args) {

        /**
         * Performs a <a href="package-summary.html#Reduction">reduction</a> on the
         * elements of this stream, using the provided identity, accumulation and
         * combining functions.  This is equivalent to:
         *
         *     U result = identity;
         *     for (T element : this stream)
         *         result = accumulator.apply(result, element)
         *     return result;
         *
         */
        System.out.println(Arrays.asList("duck", "chicken", "flamingo", "pelican")
                .parallelStream().parallel() // q1
                .reduce(0,
                        (i, c2) -> i + c2.length(), // q2
                        (s1, s2) -> s1 + s2)); // q3
        System.out.println("=============================================!");
//
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        System.out.println(randoms);

        //====

//        primitive streams

        DoubleStream oneValue = DoubleStream.of(3.14);
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        oneValue.forEach(System.out::println);
        System.out.println();
        varargs.forEach(System.out::println);

        IntStream stream = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream.average();

        System.out.println(optional);
        System.out.println("====");
        optional.ifPresent(System.out::println);
        System.out.println(optional.getAsDouble());
        System.out.println(optional.orElseGet(() -> Double.NaN));

//        Statistic is just a big
//        word for a number that was calculated from data.

        System.out.println(range(IntStream.of(1, 2, 3, 4, 5)));

        List<String> cats = new ArrayList<>();
        cats.add("Annie");
        cats.add("Ripley");
        Stream<String> stream77 = cats.stream();
        cats.add("KC");
        System.out.println(stream77.count()); // 3


        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining(", "));
        System.out.println(result); // lions, tigers, bears

        Stream<String> ohMy1 = Stream.of("lions", "tigers", "bears");
        String result1 = ohMy1.collect(Collectors.joining(", "));
        System.out.println(result1); // lions, tigers, bears

//        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
//        TreeSet<String> result2 = ohMy2.filter(s -> s.startsWith("t").collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(result2); // [tigers]

        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map3 = ohMy3.collect(
                Collectors.toMap(s -> s, String::length));
        System.out.println(map3); // {lions=5, bears=5, tigers=6}

        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map4 = ohMy4.collect(Collectors.toMap(
                String::length, k -> k, (s1, s2) -> s1 + "," + s2));
        System.out.println(map4); // {5=lions,bears, 6=tigers}
        System.out.println(map4.getClass()); // class. java.util.HashMap

        Stream<String> ohMy5 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, String> map5 = ohMy5.collect(Collectors.toMap(
                String::length, k -> k, (s1, s2) -> s1 + "," + s2, TreeMap::new));
        System.out.println(map5); // // {5=lions,bears, 6=tigers}
        System.out.println(map5.getClass()); // class. java.util.TreeMap

        //Collecting Using Grouping, Partitioning, and Mapping
        Stream<String> ohMy6 = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map6 = ohMy6.collect(
                groupingBy(String::length));
        System.out.println(map6); // {5=[lions, bears], 6=[tigers]}

        Stream<String> ohMy7 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map7 = ohMy7.collect(
                groupingBy(String::length, Collectors.toSet()));
        System.out.println(map7); // {5=[lions, bears], 6=[tigers]}
//        We can even change the type of Map returned through yet another parameter:
        Stream<String> ohMy8 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, Set<String>> map8 = ohMy8.collect(
                groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        System.out.println(map8); // {5=[lions, bears], 6=[tigers]}
//        This is very flexible. What if we want to change the type of Map returned but leave the
//        type of values alone as a List? There isn’t a method for this specifically because it is easy
//        enough to write with the existing ones:
        Stream<String> ohMy9 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, List<String>> map9 = ohMy9.collect(
                groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println(map9);

//        Partitioning is a special case of grouping. With partitioning, there are only two possible
//        groups—true and false. Partitioning is like splitting a list into two parts.
        Stream<String> ohMy10 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map10 = ohMy10.collect(
                Collectors.partitioningBy(s -> s.length() <= 5));
        System.out.println(map10); // {false=[tigers], true=[lions, bears]}


        Stream<String> ohMy11 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, Set<String>> map11 = ohMy11.collect(
                Collectors.partitioningBy(s -> s.length() <= 7, Collectors.toSet()));
        System.out.println(map11);// {false=[], true=[lions, tigers, bears]}
//        Unlike groupingBy(), we cannot change the type of Map that gets returned. However,
//                there are only two keys in the map, so does it really matter which Map type we use?
//                Instead of using the downstream collector to specify the type, we can use any of the
//        collectors that we’ve already shown. For example, we can group by the length of the animal
//        name to see how many of each length we have:
        Stream<String> ohMy12 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> map12 = ohMy12.collect(groupingBy(
                String::length, Collectors.counting()));
        System.out.println(map12); // {5=2, 6=1}

        // Suppose that we wanted to get the first letter of the first animal alphabetically of
//        each length.
//        Stream<String> ohMy13 = Stream.of("lions", "tigers", "bears");
//        Map<Integer, Optional<Character>> map13 = ohMy13.collect(
//                Collectors.groupingBy(
//                        String::length,
//                        Collectors.mapping(s -> s.charAt(0),
//                                Collectors.minBy(Comparator.naturalOrder()))));
//        System.out.println(map13); // {5=Optional[b], 6=Optional[t]}

//        Stream<String> ohMy14 = Stream.of("lions", "tigers", "bears");
//        Map<Integer, Optional<Character>> map14 = ohMy14.collect(
//                groupingBy(
//                        String::length,
//                        mapping(s -> s.charAt(0),
//                                minBy(Comparator.naturalOrder()))));
//        System.out.println(map14); // {5=Optional[b], 6=Optional[t]}

        Stream<String> streamq = Stream.iterate("", (s) -> s + "1");
        System.out.println(Arrays.toString(streamq.limit(2).map(x -> x + "2").toArray())); //[2, 12]


//        8. D, E. The sum() method returns an int rather than an OptionalInt because the sum
//        of an empty list is zero. Therefore, option E is correct. The findAny() method returns
//        an OptionalInt because there might not be any elements to find. Therefore, option D is
//        correct. The average() method returns an OptionalDouble since averages of any type can
//        result in a fraction. Therefore, options A and B are both incorrect.

//        Which of the following are true given the declaration IntStream is = IntStream.
//                empty()? (Choose all that apply.)
//        A. is.average() returns the type int.
//                B. is.average() returns the type OptionalInt.
//        C. is.findAny() returns the type int.
//                D. is.findAny() returns the type OptionalInt.
//        E. is.sum() returns the type int.
//                F. is.sum() returns the type OptionalInt.

        IntStream is = IntStream.empty();
        is.average(); //returns OptionalDouble!!!
//        is.sum(); // returns int,  because the sum of an empty list is zero.


//        9. Which of the following can we add after line 5 for the code to run without error and not
//        produce any output? (Choose all that apply.)

//        4: LongStream ls = LongStream.of(1, 2, 3);
//        5: OptionalLong opt = ls.map(n -> n * 10).filter(n -> n < 5).findFirst();
//        A. if (opt.isPresent()) System.out.println(opt.get());
//        B. if (opt.isPresent()) System.out.println(opt.getAsLong());
//        C. opt.ifPresent(System.out.println)
//        D. opt.ifPresent(System.out::println)
//        E. None of these; the code does not compile.
//        F. None of these; line 5 throws an exception at runtime.

        LongStream ls = LongStream.of(1, 2, 3);
        OptionalLong opt = ls.map(n -> n * 10).filter(n -> n < 5).findFirst();

        if (opt.isPresent())
            System.out.println(opt.getAsLong()); // A would work for a regular Stream. However, we have a LongStream and
//        therefore need to call getAsLong().
        opt.ifPresent(System.out::println);

        System.out.println("===========================================");


//      10.  Stream.generate(() -> "1")
//        L: .filter(x -> x.length() > 1)
//        M: .forEach(System.out::println)
//        N: .limit(10)
//        O: .peek(System.out::println)

        Stream.generate(() -> "1").limit(10).forEach(System.out::println);

        //12
//
//        6: ____________ x = String::new;
//        7: ____________ y = (a, b) -> System.out.println();
//        8: ____________ z = a -> a + a;
        Supplier<String> x = String::new;
        BiConsumer<String, String> y = (a, b) -> System.out.println();
        UnaryOperator<String> z = a -> a + a;
        System.out.println("===========================================");


//        13. F. If the map() and flatMap() calls were reversed, choice B would be correct. In this case,
//        the Stream created from the source is of type Stream<List<Integer>>. The Function in
//        map expects an Integer rather than a List<Integer>, so the code does not compile.
        List<Integer> l1 = Arrays.asList(1, 2, 3);
        List<Integer> l2 = Arrays.asList(4, 5, 6);
        List<Integer> l3 = Arrays.asList();
        Stream.of(l1, l2, l3).flatMap(za -> za.stream()).map(za -> za + 1)
                .forEach(System.out::print);
        System.out.println("===========================================");


//        14. D. Line 4 should obviously look OK. It creates a Stream and uses autoboxing to put the
//        Integer 1 inside. Line 5 converts to a primitive, again using autoboxing. Line 6 converts
//        to a double primitive, which works since double d = 1; would work. Line 7 is where it
//        all falls apart. Converting from a double to an int would require a cast inside the lambda.
//        Stream<Integer> s = Stream.of(1);
//        IntStream isq = s.mapToInt(xq -> xq);
//        DoubleStream ds = s.mapToDouble(xq -> xq);
//         Stream<Integer> s2 = ds.mapToInt(xq -> xq);
//         s2.forEach(System.out::print);

/*
        */

        System.out.println("===========================================");

//        16. What is the output of the following?
        Stream<String> s00 = Stream.empty();
        Stream<String> s2 = Stream.empty();
        Map<Boolean, List<String>> p = s00.collect(
                Collectors.partitioningBy(b -> b.startsWith("c")));
        Map<Boolean, List<String>> g = s2.collect(
                Collectors.groupingBy(b -> b.startsWith("c"))); //By contrast, groupingBy() returns only keys that are
//        actually needed.
        System.out.println(p + " " + g);

        // 17.
        UnaryOperator<Integer> u = q -> q * q;
        Function<Integer, Integer> f = q -> q * q; //eq

// 18.
        System.out.println("===========================================");

        DoubleStream s = DoubleStream.of(1.2, 2.4);
        s.peek(System.out::println).filter(x1 -> x1 > 2).count();

        DoubleStream s1 = DoubleStream.of(1.2, 2.4);
        System.out.println(s1.filter(x1 -> x1 > 2).count());

        System.out.println("===========================================");
        DoubleStream s12 = DoubleStream.of(1.2, 2.4);
        s12.filter(x1 -> x1 > 2).forEach(System.out::println);
        System.out.println("===========================================");

        // 20.
        List<Integer> l = IntStream.range(1, 6)
                .mapToObj(i -> i).collect(Collectors.toList());
        l.forEach(System.out::println);

        IntStream.range(1, 6)
                .forEach(System.out::println);

    }


    private static int range(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) throw new RuntimeException();
        return stats.getMax() - stats.getMin();
    }

    private static List<String> sort(List<String> list) {
        List<String> copy = new ArrayList<>(list);
//        7. F. The sorted() method is used in a stream pipeline to return a sorted Stream. A collector
//        is needed to turn the stream back into a List. The collect() method takes the desired
//        collector.
        return copy.stream()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());
    }

}
