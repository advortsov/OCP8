package chapter8_IO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class
 *
 * @author advortco
 */

public class ReadFileInformation {
    public static void main(String[] args) {
//        File file = new File("C:\\Dev\\Git\\LICENSE.txt");
        File file = new File("C:\\Dev\\");
        System.out.println("File Exists: " + file.exists());
        if (file.exists()) {
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Parent Path: " + file.getParent());
            if (file.isFile()) {
                System.out.println("File size: " + file.length());
                System.out.println("File LastModified: " + file.lastModified());
            } else {
                for (File subfile : file.listFiles()) {
                    System.out.println("\t" + subfile.getName());
                }
            }
        }


        try (ObjectInputStream objectStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("zoo-data.txt")))) {
            System.out.println(objectStream.readObject());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
