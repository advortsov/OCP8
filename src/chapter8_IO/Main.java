package chapter8_IO;

import java.io.*;

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

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("/zoo.log")));

        while (bis.read() != -1) {

        }


        /**
         * PrintWriter's print(int) method actually writes the string produced by String.valueOf(int).
         This string is translated into bytes according to the platform's default character encoding, and these bytes are written in using the write(int) method.

         Therefore, in this case, if the default character encoding is UTF-8, 2 bytes will be written.
         */
        try (OutputStream os = new FileOutputStream("dkd")) {

            BufferedOutputStream bos = new BufferedOutputStream(os);
            PrintWriter pw = new PrintWriter(bos);
            pw.print(99);
            pw.write(99);

        }

//
//        File f = new File("x");//1
//        BufferedReader bfr1 = new BufferedReader(new FileReader(f)); //2
//        BufferedReader bfr2 = new BufferedReader(new BufferedReader(bfr1)); //3
//        PrintWriter pw = new PrintWriter(new FileReader(f)); //4

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
