package chapter9_NIO2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class BasicFileAttributesSample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Dev/Git/LICENSE.txt");
        BasicFileAttributes data = Files.readAttributes(path,
                BasicFileAttributes.class);
        System.out.println("Is path a directory? " + data.isDirectory());
        System.out.println("Is path a regular file? " + data.isRegularFile());
        System.out.println("Is path a symbolic link? " + data.isSymbolicLink());
        System.out.println("Path not a file, directory, nor symbolic link? " + data.isOther()); // used to check for paths that are not
//        files, directories, or symbolic links, such as paths that refer to resources or devices in some file
//        systems.
        System.out.println("Size (in bytes): " + data.size());
        System.out.println("Creation chapter5.date/time: " + data.creationTime());
        System.out.println("Last modified chapter5.date/time: " + data.lastModifiedTime());
        System.out.println("Last accessed chapter5.date/time: " + data.lastAccessTime());
        System.out.println("Unique file identifier (if available): " + data.fileKey()); //returns a file system value that represents a unique
//        identifier for the file within the file system or null if it is not supported by the file system.

        changeAttrView();
    }

    private static void changeAttrView() throws IOException {
        Path path = Paths.get("/Dev/Git/LICENSE.txt");
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes data = view.readAttributes();
        FileTime lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000);
        view.setTimes(lastModifiedTime, null, null);
    }



}