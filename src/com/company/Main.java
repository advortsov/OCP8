package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

/**
 * This class
 *
 * @author advortco
 */
public class Main {
    enum Flavors {
        VANILLA, CHOCOLATE, STRAWBERRY
    }
    public static void main(String[] args) {


            System.out.println(Flavors.CHOCOLATE.ordinal());

        List<String> words = new ArrayList<>();
        words.add("asd");
        words.add("csd");
        words.add(null);
        words.add("bsd");
        words.add("dsd");

        System.out.println(words.size());
        words.forEach(System.out::println);

        words.removeIf(Objects::isNull);
        System.out.println(words.size());


        String[] strings = {"isd", "zsd", "csd", "ksd", "dsd", "esd", "bsd"};


        Comparator<String> соmр = (first, second) -> first.length() - second.length();

        Arrays.sort(strings, (first, second) -> first.length() - second.length());

        Arrays.sort(strings, String::compareToIgnoreCase); //равнозначна лямбда-выражению (х, у) - > x . c o m p a r e T o Ig n o r e C a s e ( у ) .
        System.out.println(Arrays.toString(strings));

        Arrays.sort(strings, reverse(String::compareToIgnoreCase));
        System.out.println(Arrays.toString(strings));
        System.out.println("-------------------------------__----------------------------");
//==========================================================================
        List<String> names = new ArrayList<>();
        names.add("Petya");
        names.add("Vasya");

        Stream<Employee> stream = names.stream().map(Employee::new);
        Employee[] buttons = stream.toArray(Employee[]::new);

        System.out.println(Arrays.toString(buttons));

        repeat(10, i -> System.out.println("Countdown: " + (9 - i)));



    }


    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }


//    Bufferedlmage createImage(int width, int height, PixelFunction f) {
//        Bufferedlmage image = new Bufferedlmage(width, height, Bufferedlmage.TYPE_INT_RGB);
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                Color color = f.apply(x, y);
//                image.setRGB(x, y, color.getRGB());
//            }
//        }
//        return image;
//    }

    public static void repeatMessage(String text, int count, int threads) {
        Runnable r = () -> {
            while (count > 0) {
                //count--; // ОШИБКА: изменить захваченную переменную нельзя!
                System.out.println(text);
            }
        };
        for (int i = 0; i < threads; i++) new Thread(r).start();
    }

    public static Comparator<String> reverse(Comparator<String> comp) {
        return (x, y) -> comp.compare(x, y);


    }






//
//    Arrays.sort(people, Comparator
//            .comparing(Person::getLastName)
//            .thenComparing(Person::getFirstName));


}

@FunctionalInterface
interface PixelFunction {
    Color apply(int x, int y);
}

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}