package chapter8_IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class
 *
 * @author advortco
 */
public class CopyFileSample {
    public static void copy(File source, File destination) throws IOException {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        }
    }


    /**
     * Since these classes are buffered, you can expect better performance than
     * if you read/wrote each character one at a time.
     */
    static class CopyBufferFileSample {
        public static void bufferCopy(File source, File destination) throws IOException {
            try (InputStream in = new BufferedInputStream(new FileInputStream(source));
                 OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
                byte[] buffer = new byte[1024];
                int lengthRead;
                while ((lengthRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, lengthRead);
                    out.flush();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File("Zoo.ccc");
        File destination = new File("ZooCopy.ccc");
        copy(source, destination);
        CopyBufferFileSample.bufferCopy(new File("Zoo.ccc"), new File("bufferZooCopy.ccc"));
    }


}
