package chapter3_generics_and_collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection method:
 * <p>
 * Removes all of the elements of this collection that satisfy the given
 * predicate.  Errors or runtime exceptions thrown during iteration or by
 * the predicate are relayed to the caller.
 *
 * @author advortco
 */
public class RemovingConditionally {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list); // [Magician, Assistant]
        list.removeIf(s -> s.startsWith("A"));
        System.out.println(list); // [Magician]
    }
}
