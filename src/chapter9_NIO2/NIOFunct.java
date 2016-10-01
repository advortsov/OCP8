package chapter9_NIO2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class
 *
 * @author advortco
 */
public class NIOFunct {
    public static void main(String[] args) {
        Path path = Paths.get("/Dev");
        try {
            Files.walk(path)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            // Handle file I/O exception...
        }


        path = Paths.get("/Dev");
        long dateFilter = 1420070400000L;
        try {
            Stream<Path> stream = Files.find(path, 10,
                    (p, a) -> p.toString().endsWith(".java")
                            && a.lastModifiedTime().toMillis() > dateFilter);
            stream.forEach(System.out::println);
        } catch (Exception e) {
            // Handle file I/O exception...
        }

        System.out.println("======================= Files.list===============================");
        try {
//            Path path = Paths.get("ducks");
            Files.list(path)
                    .filter(p -> Files.isDirectory(p))
                    .map(p -> p.toAbsolutePath())
                    .forEach(System.out::println);

        } catch (IOException e) {
            // Handle file I/O exception...
        }


//        We now present Files.lines(), which is equivalent to the previous Files.readAllLines().
//        It is also more performant on large files, since it
//        does not require the entire file to be read and stored in memory.
        path = Paths.get("/fish/sharks.log");
        try {
            Files.lines(path).forEach(System.out::println);

//            or:
//            System.out.println(Files.lines(path)
//                            .filter(s -> s.startsWith("WARN "))
//                            .map(s -> s.substring(5))
//                            .collect(Collectors.toList()));
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }
}
