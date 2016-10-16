package chapter4_functional_programming;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

/**
 * @author advortco
 */
public class Main {
    public static void main(String[] args) {
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();
        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();
        System.out.println(d1);
        System.out.println(d2);


        Supplier<StringBuilder> sb1 = StringBuilder::new;
        Supplier<StringBuilder> sb2 = () -> new StringBuilder();
        System.out.println(sb1.get());
        System.out.println(sb2.get());

        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);
        c1.accept("Annie"); //accept - performs this operation on the given argument.
        c2.accept("Annie");

//        BiConsumer is called with two parameters. They don’t have to be the same type. For
//        example, we can put a key and a value in a map using this interface:
        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        System.out.println(map);

        System.out.println("===========================================================================");
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = x -> x.isEmpty();
        System.out.println(p1.test(""));
        System.out.println(p2.test(""));
//        This prints true twice. More interesting is a BiPredicate. This example also prints true
//        twice:
        BiPredicate<String, String> biPredicate1 = String::startsWith;
        BiPredicate<String, String> biPredicate2 = (string, prefix) -> string.startsWith(prefix);
        BiPredicate<String, String> biPredicate3 = biPredicate2.and((string, prefix) -> string.length() < 1);

        System.out.println(biPredicate1.test("chicken", "chick"));
        System.out.println(biPredicate2.test("chicken", "chick"));
        System.out.println(biPredicate3.test("chicken", "chick"));


//        For example, this function converts a String to the length of the String:
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();
        System.out.println(f1.apply("cluck")); // 5
        System.out.println(f2.apply("cluck")); // 5
//        This function turns a String into an Integer. Well, technically it turns the String into
//        an int, which is autoboxed into an Integer. The types don’t have to be different.
//        The following combines two String objects and produces another String:
        BiFunction<String, String, String> function1 = String::concat;
        BiFunction<String, String, String> function2 = (string, toAdd) -> string.concat(toAdd);
        System.out.println(function1.apply("baby ", "chick")); // baby chick
        System.out.println(function2.apply("baby ", "chick")); // baby chick

        System.out.println("===========================================================================");

        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();
        System.out.println(u1.apply("chirp"));
        System.out.println(u2.apply("chirp"));
//        This prints CHIRP twice. We don’t need to specify the return type in the generics because
//        UnaryOperator requires it to be the same as the parameter. And now for the binary example:
        BinaryOperator<String> binaryOperator1 = String::concat;
        BinaryOperator<String> binaryOperator2 = (string, toAdd) -> string.concat(toAdd);
//        There are five type parameters here. The first four supply the types of the four motors.
//        Ideally these would be the same type, but you never know. The fifth is the return type.
//                Java’s built-in interfaces are meant to facilitate the most common functional interfaces
//        that you’ll need. It is by no means an exhaustive list. Remember that you can add any
//        functional interfaces you’d like, and Java matches them when you use lambdas or
//        method references.
        System.out.println(binaryOperator1.apply("baby ", "chick")); // baby chick
        System.out.println(binaryOperator2.apply("baby ", "chick")); // baby chick

        System.out.println("==============================a -> b -> a - b============================================");

        IntFunction<IntUnaryOperator> fo = a -> (b -> a - b);
        int x = operate(fo.apply(20)); //2       
        System.out.println(x);

        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(1, "a");
        hm.put(2, "b");
        hm.put(3, "c");
        hm.forEach((key, value) -> System.out.printf("%d %s ", key, value));

    }

    public static int operate(IntUnaryOperator iuo) {
        return iuo.applyAsInt(5);
    }

}