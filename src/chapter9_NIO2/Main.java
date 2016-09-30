package chapter9_NIO2;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author aldvc
 * @date 29.09.2016.
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Path path1 = Paths.get("pandas/cuddly.png"); // creates a Path reference to a relative file in the current working directory.
        Path path2 = Paths.get("c:\\zooinfo\\November\\employees.txt"); // creates a Path reference to an absolute file in a Windows-based system.
        Path path3 = Paths.get("/home/zoodirector"); // creates a Path reference to an absolute directory in a Linux or Mac-based system.


        // it inserts the proper path separator for you.
        path1 = Paths.get("pandas", "cuddly.png");
        path2 = Paths.get("c:", "zooinfo", "November", "employees.txt");
        path3 = Paths.get("/", "home", "zoodirector");


//        A uniform resource identifier (URI) is a string of characters that identify a resource. It begins with
//        a schema that indicates the resource type, followed by a path value. Examples of schema
//        values include file://, http://, https://, and ftp://. The java.net.URI class is used to
//        create and manage URI values. (throws URISyntaxException)
        path1 = Paths.get(new URI("file://pandas/cuddly.png")); // THROWS EXCEPTION AT RUNTIME as URIs must reference absolute paths at runtime.
        path2 = Paths.get(new URI("file:///c:/zoo-info/November/employees.txt"));
        path3 = Paths.get(new URI("file:///home/zoodirectory"));
//        you should be aware that they exist.
//        Path path4 = Paths.get(new URI("http://www.wiley.com"));
//        Path path5 = Paths.get(new URI("ftp://username:password@ftp.the-ftp-server.com"));

//        URI uri4 = path4.toUri();

//The default file system creates objects that provide access to
// the file systems accessible to the Java virtual machine.
        path1 = FileSystems.getDefault().getPath("pandas/cuddly.png");
        path2 = FileSystems.getDefault().getPath("c:", "zooinfo", "November",
                "employees.txt");
        path3 = FileSystems.getDefault().getPath("/home/zoodirector");


//        FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
//        Path path = fileSystem.getPath("duck.txt");
//        System.out.println(path);

//        For backward compatibility, the Path interface also contains a method toFile() to
//        return a File instance:
        Path path = Paths.get("cuddly.png");
        File file = path.toFile();


        path = Paths.get("/land/hippo/harry.happy");
        System.out.println("The Path Name is: " + path);
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println(" Element " + i + " is: " + path.getName(i));
        }

        System.out.println(path.getName(0));

        System.out.println("========================Path is Absolute============================");

        Path path4 = Paths.get("C:\\birds\\egret.txt");
        System.out.println("Path1 is Absolute? " + path4.isAbsolute());
        System.out.println("Absolute Path1: " + path4.toAbsolutePath());
        Path path5 = Paths.get("birds/condor.txt");
        System.out.println("Path2 is Absolute? " + path5.isAbsolute());
        System.out.println("Absolute Path2 " + path5.toAbsolutePath());

        System.out.println("============================Subpath==================================");

        Path path6 = Paths.get("/mammal/carnivore/raccoon.image"); //Notice that the 0-indexed element is mammal in this example and not the root
//        directory;
        System.out.println("Path is: " + path6);
        System.out.println("Subpath from 0 to 3 is: " + path6.subpath(0, 3));
        System.out.println("Subpath from 1 to 3 is: " + path6.subpath(1, 3));
        System.out.println("Subpath from 1 to 2 is: " + path6.subpath(1, 2));

        System.out.println("==============================relativize===================================");
        path1 = Paths.get("fish.txt");
        path2 = Paths.get("birds.txt");
        System.out.println(path1.relativize(path2));
        System.out.println(path2.relativize(path1));

        System.out.println("===============================relativize to path=====================================");
        // the relativize() method
//        constructs the relative path between the two absolute path values within the file system.
        path3 = Paths.get("E:\\habitat");
        path4 = Paths.get("E:\\sanctuary\\raven");
        System.out.println(path3.relativize(path4));
        System.out.println(path4.relativize(path3));

        System.out.println("==============================resolve======================================");

        path1 = Paths.get("/cats/../panther");
        path2 = Paths.get("food");
        System.out.println(path1.resolve(path2));

        System.out.println(path4.resolve(path3)); //?
        System.out.println(path3.resolve(path4)); //?


        System.out.println("==============================normalize()=================================");

        path3 = Paths.get("E:\\data");
        path4 = Paths.get("E:\\user\\home");
        Path relativePath = path3.relativize(path4);
        System.out.println("relativize(): " + relativePath);
        System.out.println("resolve(): " + path3.resolve(relativePath));
        System.out.println("normalize(): " + path3.resolve(relativePath).normalize());

        System.out.println("========================================================================");



        System.out.println("========================================================================");
        System.out.println("========================================================================");
        System.out.println("========================================================================");


    }
}
