package chapter4_functional_programming;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author aldvc
 * @date 20.09.2016.
 */
public class Test_4Chapter {
    public static void main(String[] args) {
        //3.
        Predicate<? super String> predicate = s -> s.length() > 3;
        Stream<String> stream = Stream.iterate("-", (s) -> s + s);
        stream.limit(5).forEach(s -> System.out.println(s + " "));
//        boolean b1 = stream.noneMatch(predicate);
//        boolean b2 = stream.anyMatch(predicate);
//        System.out.println(b1 + " " + b2);

        //6. A. Options C and D are incorrect because these methods do not take a Predicate
//        parameter and do not return a boolean. Options B and E are incorrect because they cause
//        the code to run infinitely. The stream has no way to know that a match won’t show up
//        later. Option A is correct because it is safe to return false as soon as one element passes
//        through the stream that doesn’t match.
        Stream<String> s = Stream.generate(() -> "meow");
        boolean match = s.allMatch(String::isEmpty);
        System.out.println(match);


    }
}
