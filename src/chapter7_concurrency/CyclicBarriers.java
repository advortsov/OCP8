package chapter7_concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author advortco
 */
public class CyclicBarriers {

    static class LionPenManager {
        private void removeAnimals() {
            System.out.println("Removing animals");
        }

        private void cleanPen() {
            System.out.println("Cleaning the pen");
        }

        private void addAnimals() {
            System.out.println("Adding animals");
        }

        public void performTask() {
            removeAnimals();
            cleanPen();
            addAnimals();
        }
    }

    static class LionPenManagerWithCyclicBarrier {
        private void removeAnimals() {
            System.out.println("Removing animals");
        }

        private void cleanPen() {
            System.out.println("Cleaning the pen");
        }

        private void addAnimals() {
            System.out.println("Adding animals");
        }

        public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
            try {
                removeAnimals();
                c1.await();
                cleanPen();
                c2.await();
                addAnimals();
            } catch (InterruptedException | BrokenBarrierException e) {
                // Handle checked exceptions here
            }
        }

        public static void main(String[] args) {
//            ExecutorService service = null;
//            try {
//                service = Executors.newFixedThreadPool(4);
//                LionPenManager manager = new LionPenManager();
//                for (int i = 0; i < 4; i++)
//                    service.submit(() -> manager.performTask());
//            } finally {
//                if (service != null) service.shutdown();
//            }


            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            ExecutorService service1 = null;
            try {
                service1 = Executors.newFixedThreadPool(4);
//                service1 = Executors.newFixedThreadPool(2); // deadlock!

                LionPenManagerWithCyclicBarrier manager = new LionPenManagerWithCyclicBarrier();
                CyclicBarrier c1 = new CyclicBarrier(4);
                CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));
                for (int i = 0; i < 4; i++)
                    service1.submit(() -> manager.performTask(c1, c2));
            } finally {
                if (service1 != null) service1.shutdown();
            }
        }
    }

}
