package chapter7_concurrency;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Let’s say that we want to compute the sum of all weight values while processing the data.
 * Instead of extending RecursiveAction, we could extend the generic RecursiveTask to
 * calculate and return each sum in the compute() method. The following is an updated
 * implementation that uses RecursiveTask<Double>:
 *
 * @author advortco
 */
public class WeighAllAnimalTask extends RecursiveTask<Double> {
    private int start;
    private int end;
    private Double[] weights;

    public WeighAllAnimalTask(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    protected Double compute() {
        if (end - start <= 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal Weighed: " + i);
                sum += weights[i];
            }
            return sum;
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
            RecursiveTask<Double> otherTask = new WeighAllAnimalTask(weights, start, middle);
            //Since the invokeAll() method doesn’t return a value, we instead
//            issue a fork() and join() command to retrieve the recursive data.
//            The fork() method instructs the fork/join framework to complete the task in a separate thread, while the
//            join() method causes the current thread to wait for the results.
            otherTask.fork();
            return new WeighAllAnimalTask(weights, middle, end).compute() + otherTask.join();
            //For the exam, make sure that fork() is
//            called before the current thread begins a subtask and that join() is called after it finishes
//            retrieving the results, in order for them to be done in parallel.
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[32];
        ForkJoinTask<Double> task = new WeighAllAnimalTask(weights, 0, weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);
        System.out.println("Sum: " + sum);
    }

}
