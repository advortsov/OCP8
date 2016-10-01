package chapter7_concurrency.problems;


import java.util.concurrent.*;


/**
 * In this example, Foxy obtains the food and then moves to the other side of the environment to obtain the water.
 * Unfortunately, Tails already drank the water and is waiting for
 * the food to become available. The result is that our program outputs the following and it
 * hangs indefinitely:
 * Got Food!
 * Got Water!
 */
public class DeadlockFoxes {

    public void eatAndDrink(Food food, Water water) {
        synchronized (food) {
            System.out.println("Got Food!");
            move();
            synchronized (water) {
                System.out.println("Got Water!");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized (water) {
            System.out.println("Got Water!");
            move();
            synchronized (food) {
                System.out.println("Got Food!");
            }
        }
    }

    public void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
// Handle exception
        }
    }

    public static void main(String[] args) {
        // Create participants and resources
        DeadlockFoxes foxy = new DeadlockFoxes();
        DeadlockFoxes tails = new DeadlockFoxes();
        Food food = new Food();
        Water water = new Water();
        // Process data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        } finally {
            if (service != null) service.shutdown();
        }
    }

    static class Food {
    }

    static class Water {
    }
}