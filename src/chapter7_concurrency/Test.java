package chapter7_concurrency;

/**
 * @author aldvc
 * @date 14.10.2016.
 */
public class Test extends Thread {
    static int x, y;

    public synchronized void run() {
        for (; ; ) {
            x++;
            y++;
            System.out.println(this.getName() + " " + x + " " + y);
        }
    }

    public static void main(String[] args) {
        Test first = new Test();
        first.setName("Thread 1");
        first.start();

        Test second = new Test();
        second.setName("Thread 2");
        second.start();


    }
}

