package chapter4_functional_programming;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;

/**
 * @author aldvc
 * @date 09.10.2016.
 */
public class Classify {

    static class Book {
        private int id;
        private String title;
        private String genre;
        private String author;
        private double price;

        public Book() {
        }

        public Book(String title, String genre, String author) {
            this.title = title;
            this.genre = genre;
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(new Book("There is a hippy on the highway", "Thriller", "James Hadley Chase"),
                new Book("Coffin from Hongkong", "Thriller", "James Hadley Chase"),
                new Book("The Client", "Thriller", "John Grisham"),
                new Book("Gone with the wind", "Fiction", "Margaret Mitchell"));
        Map<String, Map<String, List<Book>>> classified = null;
        classified = books.stream().collect(
                Collectors
                        .groupingBy(x -> x.getGenre(),
                                Collectors.groupingBy(x -> x.getAuthor()))
        );
        System.out.println(classified);

        Map<String, Map<String, List<Book>>> classified1 = books.stream().collect(
                Collectors
                        .groupingBy(Book::getGenre,
                                Collectors.groupingBy(Book::getAuthor))
        );
        System.out.println(classified1);

        List<Double> dList = Arrays.asList(10.0, 12.0);
        Consumer<Double> df = x -> System.out.println(x);
        dList.stream().forEach(df);
        dList.stream().forEach(d -> System.out.println(d));
    }
}
