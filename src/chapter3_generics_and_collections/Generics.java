package chapter3_generics_and_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author aldvc
 * @date 04.10.2016.
 */
public class Generics {

    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }


    public static void main(String[] args) {
        List<? super A> numList = new ArrayList<>();
        numList.add(new A());
        numList.add(new B());
        numList.add(new C());

        List<Number> numList1 = new ArrayList<Number>();

        List<? super Number> foo3 = new ArrayList<Object>(); // Object is a "super" of Number
        List<? super Integer> foo4 = new ArrayList<>(); // Object is a "super" of Number
        foo4.add(new Integer(3));

        Number integ = new Integer(5);
        List<? extends Number> foo6 = new ArrayList<Integer>(); // Object is a "super" of Number
//        foo6.add(integ);
//        integ.

        numList1.add(4);
        numList1.add(4.0);
        numList1.add(4f);
        sum(numList1);

        List<String> list = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred)); // false
        System.out.println(infinite.anyMatch(pred)); // true

        Stream<Integer> stream = Stream.of(3, 5, 6);
        System.out.println(stream.reduce(1, (a, b) -> a * b));


    }

    public static Double sum(List<? extends Number> numList) {
        Double result = 0.0;
        for (Number num : numList) {
            result += num.doubleValue();
        }
        System.out.println(result);
        return result;
    }
}
