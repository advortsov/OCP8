package chapter9_NIO2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 1 f == +
 * 2 b c == +
 * 3 a ? == d -
 * 4 c == +
 * 5 b c d == +
 * 6 c ? == +
 * 7 e == f -
 * 8 b ? == a -
 * 9 b c (?a) == +
 * 10 d ? == c e -
 * 11 b == a -
 * 12 a f == +
 * 13 b ? == +
 * 14 e ? == +
 * 15 c d f == d e f -
 * 16 e f == f -
 * 17 g == a g -
 * 18 b == d -
 * 19 a c e == +
 * 20 d == b -
 *
 * @author advortco
 */
public class Test9 {


    public static void main(String[] args) throws IOException {
        // 3
//        Path path = Paths.get("sloth.schedule");
//        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
//        if (attributes.size() > 0 && attributes.creationTime().toMillis() > 0) {
////            attributes.setTimes(null, null, null); //The setTimes() method is available only on BasicFileAttributeView, not the readonly
////            BasicFileAttributes class
//        }

        //7
        Path path = Paths.get("turkey");
//        if (Files.isSameFile(path, Paths.get("/zoo/turkey"))) // x1
//            Files.createDirectory(path.resolve("info")); // x2

        //8
        //First, the resolve() method does not normalize any path symbols,
//        so C and D are not correct. Second, calling resolve() with an absolute path as a
//        parameter returns the absolute path, so A is correct and B is incorrect.

        //A. /pets/../cat.txt/./dog.txt
//        /pets/../cat.txt
//        B. /pets/../cat.txt/./dog.txt
//                ./dog.txt/pets/../cat.txt
        Path path1 = Paths.get("/pets/../cat.txt");
        Path path2 = Paths.get("./dog.txt");
        System.out.println(path1.resolve(path2));
        System.out.println(path2.resolve(path1));

        //10
//        Files.move(Paths.get("monkey.txt"), Paths.get("/animals"),
//                StandardCopyOption.ATOMIC_MOVE,
//                LinkOption.NOFOLLOW_LINKS); //the NOFOLLOW_LINKS option means that
//        if the source is a symbolic link, the link itself and not the target will be copied at runtime

        // moving always preserves the metadata even if the COPY_ATTRIBUTES flag is not set.


        //11

        /*
            Even though the file is copied with attributes preserved, the file is considered
            a separate file, so the output is false
         */
//        path1 = Paths.get("./goat.txt").normalize(); // k1
//        path2 = Paths.get("mule.png");
//        Files.copy(path1, path2, StandardCopyOption.COPY_ATTRIBUTES); //k2
//        System.out.println(Files.isSameFile(path1, path2)); //k3

        //20
//        The normalize() method does not convert a relative path into an absolute path;
        path = Paths.get(".").normalize(); // h1
        int count = 0;
        for(int i=0; i<path.getNameCount(); ++i) {
            count++;
            System.out.println(path.getFileName());
        }
        System.out.println(count);
    }

  /*

            17. A, G. The code compiles without issue, so B, C, and D are incorrect. The first line
    actually resolves to the root path since .. and getParent()are conceptually equivalent.
    Therefore, G is correct and E and F are incorrect. A is also correct since it may encounter
    a file that it does not have access to read, which is common when trying to read an entire
    file system.
            18. D. The code compiles and runs without issue, so F is incorrect. The one thing to notice
    about these paths is that they represent the same path within the file system. Therefore,
    isSameFile() would return true and B and C are incorrect. The second output is false,
    because Path.equals() does not resolve the path within the file system, so A is incorrect.
    Finally, the normalized paths are equals(), since all extra symbols have been removed;
    therefore D is correct and E is incorrect.
            19. A, C, E. While all of the answers are applicable to the NIO.2, only A, C, and E are options
    that are not supported by the legacy java.io.File class and therefore give NIO.2 an
    advantage over java.io.File.
    20. B. The normalize() method does not convert a relative path into an absolute path; therefore,
    the path value after the first line is just the current directory symbol. The for() loop
    iterates the name values, but since there is only one entry, the loop terminates after a single
    iteration. Therefore, B is correct and the rest of the answers are incorrect.
*/

}
