package from_home;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
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

        //filter
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.filter(x -> x.startsWith("m")).forEach(System.out::println); // monkey

        //distinct
        Stream<String> d = Stream.of("duck", "duck", "duck", "goose");
        d.distinct().forEach(System.out::println); // duckgoose

        //limit() and skip()
        Stream<Integer> str = Stream.iterate(1, n -> n + 1);
        str.skip(5).limit(2).forEach(System.out::println); // 67

        Stream<String> st = Stream.of("monkey", "gorilla", "bonobo");
        st.map(x -> Double.valueOf(x.length())).forEach(System.out::println); // 676

        //flatMap
        List<String> zero = Arrays.asList();
        List<String> one = Arrays.asList("Bonobo");
        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);
        animals.flatMap(l -> l.stream()).forEach(System.out::println);

        System.out.println(zero);
        System.out.println(one);
        System.out.println(two);

        //

        Stream<String> s1 = Stream.of("brown-", "bear-");
        s1.sorted().forEach(System.out::print); // bear-brown-

        Stream<String> s2 = Stream.of("brown bear-", "grizzly-");
        s2.sorted(Comparator.reverseOrder())
                .forEach(System.out::print); // grizzly-brown bear

//        s2.sorted(Comparator::reverseOrder); // DOES NOT COMPILE


        //peek
        Stream<String> stream6 = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream6.filter(sla -> sla.startsWith("g"))
                .peek(System.out::println).count(); // grizzly
        System.out.println(count); // 1

        List<Integer> numbers = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');
        Stream<List<?>> stream7 = Stream.of(numbers, letters);
        stream7.map(List::size).forEach(System.out::print); // 11
//        We can add a proper peek() operation:
        StringBuilder builder = new StringBuilder();
        Stream<List<?>> good = Stream.of(numbers, letters);
        good.peek(l -> builder.append(l)).map(List::size).forEach(System.out::print); // 11
        System.out.println(builder); // [1][a]
//        In this example, you can see that peek() updates a StringBuilder variable that doesn’t
//        affect the result of the stream pipeline. It still prints 11. Java doesn’t prevent us from writing bad peek code:
        Stream<List<?>> bad = Stream.of(numbers, letters);
        bad.peek(l -> l.remove(0)).map(List::size).forEach(System.out::print); // 00
//        This example is bad because peek() is modifying the data structure that is used in the
//        stream, which causes the result of the stream pipeline to be different than if the peek
//        wasn’t present.


        System.out.println("============================================================================");
//using:

        List<String> list1 = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        list1.stream()
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);

    }
}
