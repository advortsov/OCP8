package chapter8_IO;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * This class
 *
 * @author advortco
 */
public class Main {
    public static void main(String[] args) throws IOException {

        //test
        String line;
        Console c = System.console();
        Writer w = c.writer();
        if ((line = c.readLine()) != null)
            w.append(line);
        w.flush();


        //        file.mkdirs()
//        PrintWriter is the only Writer class that you need to know that doesn’t have a complementary
//        Reader class
//        PrintWriter
//        PrintReader
    }

    /**
     * C, F. The code compiles, so D and E are incorrect. There is a bug in the method in that
     * file.delete() should be executed at the end of the method for both files and directories
     * alike. As written, the method will delete all files within a directory but none of the directories
     * themselves. Therefore, A and B are incorrect and C is correct. F is correct, because
     * most methods in the File class that interact with the file system are capable of throwing an
     * exception at runtime, such as when the directory does not exist.
     */
    public static void deleteTree(File file) {
        if (!file.isFile())
            for (File entry : file.listFiles())
                deleteTree(entry);
        else file.delete(); // There is a bug in the method in that
//        * file.delete() should be executed at the end of the method for both files and directories
//        * alike


    }


}
