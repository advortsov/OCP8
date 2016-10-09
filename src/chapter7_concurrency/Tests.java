package chapter7_concurrency;

/**
 * @author aldvc
 * @date 07.10.2016.
 */
public class Tests {

    static class Test extends Thread {
        static int x, y;

        public synchronized void run() {
            for (; ; ) {
                x++;
                y++;
                System.out.println(x + " " + y);
            }
        }

        public static void main(String[] args) {
            new Test().start();
            new Test().start();
        }
    }
}
