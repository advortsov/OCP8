package chapter3_generics_and_collections;

import java.util.Arrays;
import java.util.List;

/**
 * This class
 *
 * @author advortco
 */
public class ReplaceAll {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.replaceAll(x -> x * 2);
        System.out.println(list); // [2, 4, 6]
    }
}
