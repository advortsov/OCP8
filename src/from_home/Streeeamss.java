package from_home;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author aldvc
 * @date 17.09.2016.
 */
public class Streeeamss {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred)); // false
        System.out.println(infinite.anyMatch(pred)); // true


        //reduce()
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("r", (s, c) -> s + c);
        System.out.println(word); // wolf

        Stream<String> stream2 = Stream.of("w", "o", "l", "f");
        word = stream2.reduce("", String::concat);
        System.out.println(word); // wolf

        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
//        System.out.println(stream3.reduce(3, (a, b) -> a * b));
        stream3.reduce((a, b) -> a * b).ifPresent(System.out::println);


        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty.reduce(op).ifPresent(System.out::println); // no output
        oneElement.reduce(op).ifPresent(System.out::println); // 3
        threeElements.reduce(op).ifPresent(System.out::println); // 90

        //collect
        Stream<String> stream4 = Stream.of("w", "o", "l", "f");
        StringBuilder word4 = stream4.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(word4.toString());

        Stream<String> stream5 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set = stream5.collect(TreeSet::new, TreeSet::add,
                TreeSet::addAll);
        System.out.println(set); // [f, l, o, w]

    }
}
