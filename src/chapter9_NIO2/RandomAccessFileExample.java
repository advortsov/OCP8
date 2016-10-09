package chapter9_NIO2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author aldvc
 * @date 09.10.2016.
 */
public class RandomAccessFileExample {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("zoo.log", "rwd");
        //INSERT CODE HERE:
        raf.writeChars("hello world");

        raf.close();
        DataInputStream dis = new DataInputStream(new FileInputStream("zoo.log"));
        String value = dis.readUTF();
        System.out.print(value);
        dis.close();
    }
}
