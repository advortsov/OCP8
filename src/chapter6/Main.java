package chapter6;

import java.io.Closeable;

/**
 * @author aldvc
 * @since 24.09.2016.
 */
public class Main {
    public static class StuckTurkeyCage implements Closeable {
        public void close()  {
//            throw new Exception("Cage door does not close");
        }

    }

    public static void main(String[] args) throws Exception {
        try (StuckTurkeyCage t = new StuckTurkeyCage()) { // DOES NOT COMPILE
            System.out.println("put turkeys in");
        }

//
//        What happens if the try block also throws an exception?
//        Java 7 added a way to accumulate exceptions. When multiple exceptions are thrown, all but
//        the first are called suppressed exceptions. The idea is that Java treats the first exception as
//        the primary one and tacks on any that come up while automatically closing, for example:
//        try (StuckTurkeyCage t = new StuckTurkeyCage()) {
//            throw new IllegalStateException("turkeys ran off");
//        } catch (IllegalStateException e) {
//            System.out.println("caught: " + e.getMessage());
//            for (Throwable t : e.getSuppressed())
//                System.out.println(t.getMessage());
//        }
        System.out.println("==============================================");
        try (StuckTurkeyCage t = new StuckTurkeyCage()) {
            throw new IllegalStateException("turkeys ran off");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println(t.getMessage());
        } finally {
            throw new RuntimeException("and we couldn't find them");
        }

    }
}
