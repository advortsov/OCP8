package chapter7_concurrency;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * the method in Callable can throw a checked
 * Exception. How do you tell lambda expressions for these two apart? The answer is
 * sometimes you can't.
 *
 * @author advortco
 */
public class AmbiguousLambdaSample {
    public static void useCallable(Callable<Integer> expression) {
    }

    public static void useSupplier(Supplier<Integer> expression) {
    }

    public static void use(Supplier<Integer> expression) {
    }

    public static void use(Callable<Integer> expression) {
    }


    //    The compiler does not take into account the fact that the body of
//    the lambda expression happens to throw an exception; therefore, it does not know how
//    to tell them apart.
    public static void main(String[] args) {
        use((Callable<Integer>) () -> {
            throw new IOException("");
        }); // COMPILES
        useCallable(() -> {
            throw new IOException();
        }); // COMPILES
//        useSupplier(() -> {
//            throw new IOException();
//        }); // DOES NOT COMPILE
//        use(() -> {
//            throw new IOException();
//        }); // DOES NOT COMPILE
    }
}