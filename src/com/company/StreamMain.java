package com.company;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class
 *
 * @author advortco
 */
public class StreamMain {
    public static void main(String[] args) {
//        Optional<Double> result = inverse(х).flatMap(StreamMain::squareRoot);


        Stream<String> words = Arrays.stream(new String[] {"E", "jgjg"});
        IntStream lengths = words.mapToInt(String::length);

//        System.out.println(Arrays.toString(lengths.toArray()));
        System.out.println(lengths.average());

    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
