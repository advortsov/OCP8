package chapter3_generics_and_collections;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

/**
 * @author advortco
 */
public class Main {
    public static void main(String[] args) {

        Collection<String> collection;
        Queue<Integer> queue = new ArrayDeque<>();

        System.out.println(queue.offer(10)); // true
        System.out.println(queue.offer(4)); // true

        System.out.println(queue);

        System.out.println(queue.peek()); // 10
        System.out.println(queue.poll()); // 10
        System.out.println(queue.poll()); // 4
        System.out.println(queue.peek()); // null
        System.out.println(queue.poll()); // null

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(4);
        System.out.println(stack.peek()); // 4
        System.out.println(stack.poll()); // 4
        System.out.println(stack.poll()); // 10
        System.out.println(stack.peek()); // null

        ArrayDeque<String> greetings = new ArrayDeque<String>();
        greetings.push("hello");
        greetings.push("hi");
        greetings.push("ola");
        String s = greetings.pop();
        greetings.peek();
        while (greetings.peek() != null)
            System.out.print(greetings.pop());


        List<String> list = new Vector<String>();

    }
}
