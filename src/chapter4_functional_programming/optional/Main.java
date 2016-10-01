package chapter4_functional_programming.optional;

import java.util.Optional;

/**
 *
 * @author advortco
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(average());
        System.out.println(average(65, 65, 40));


        Optional<Double> opt = average();
        System.out.println(opt.orElse(Double.NaN));
        System.out.println(opt.orElseGet(() -> Math.random()));
        System.out.println(opt.orElseThrow(() -> new IllegalStateException()));
    }


    public static Optional<Double> average(int... scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score : scores) sum += score;
        return Optional.of((double) sum / scores.length);
    }
}
