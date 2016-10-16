package chapter3_generics_and_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class
 *
 * @author advortco
 */
public class ComputeIf {
    public static void main(String[] args) {

        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        BiFunction<String, Integer, Integer> mapper = (k, v) -> v + 1;
        Integer jenny = counts.computeIfPresent("Jenny", mapper);
        // interface Map<K,V>. computeIfPresent - If the value for the specified key is present and non-null, attempts to
        // compute a new mapping given the key and its current mapped value.
        Integer sam = counts.computeIfPresent("Sam", mapper);
        System.out.println(counts); // {Jenny=2}
        System.out.println(jenny); // 2
        System.out.println(sam); // null

        System.out.println("=======================================================");


        Map<String, Integer> counts2 = new HashMap<>();
        counts2.put("Jenny", 15);
        counts2.put("Tom", null);
        Function<String, Integer> mapper2 = (k) -> 1;
        Integer jenny2 = counts2.computeIfAbsent("Jenny", mapper2); // 15
        Integer sam2 = counts2.computeIfAbsent("Sam", mapper2); // 1
        Integer tom2 = counts2.computeIfAbsent("Tom", mapper2); // 1
        System.out.println(counts2); // {Tom=1, Jenny=15, Sam=1}

        System.out.println("=======================================================");

//
//        If the mapping function is called and returns null, the key is removed from the map for
//        computeIfPresent(). For computeIfAbsent(), the key is never added to the map in the
//        first place, for example:
        Map<String, Integer> counts3 = new HashMap<>();
        counts3.put("Jenny", 1);
        counts3.computeIfPresent("Jenny", (k, v) -> null);
        counts3.computeIfAbsent("Sam", k -> null);
        System.out.println(counts3); // {}
    }
}
