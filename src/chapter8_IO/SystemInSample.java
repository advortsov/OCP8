package chapter8_IO;

import java.io.*;

/**
 * Interacting with Users: old way
 */
public class SystemInSample {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput = reader.readLine();
        System.out.println("You entered the following: " + userInput);
//        Notice that we did not close the stream, as closing
//        System.in would prevent our application from accepting user input for the remainder of
//        the application execution.
    }
}