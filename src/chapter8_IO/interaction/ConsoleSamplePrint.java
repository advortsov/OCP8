package chapter8_IO.interaction;

import java.io.Console;
import java.io.IOException;

public class ConsoleSamplePrint {
    public static void main(String[] args) throws NumberFormatException,
            IOException {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("Our zoo has 391 animals and employs 25 people.");
            console.writer().println();
            console.printf("The zoo spans 128.91 acres.");
        }
//    } else {
//        console.writer().print("How excited are you about your trip today? ");
//        console.flush();
//        String excitementAnswer = console.readLine();
//        String name = console.readLine(“Please enter your name: “);
//        Integer age = null;
//        console.writer().print("What is your age? ");
//        console.flush();
//        BufferedReader reader = new BufferedReader(console.reader());
//        String value = reader.readLine();
//        age = Integer.valueOf(value);
//        console.writer().println();
//        console.format("Your name is "+name);
//        console.writer().println();
//        console.format("Your age is "+age);
//        console.printf("Your excitement level is: "+excitementAnswer);
//    }


//    } else {
//        char[] password = console.readPassword("Enter your password: ");
//        console.format("Enter your password again: ");
//        console.flush();
//        char[] verify = console.readPassword();
//        boolean match = Arrays.equals(password,verify);
//// Immediately clear passwords from memory
//        for(int i=0; i<password.length; i++) { // Array.fill(password,'x')
//            password[i]='x';
//        }
//        for(int i=0; i<verify.length; i++) {
//            verify[i]='x';
//        }
//        console.format("Your password was "+(match ? "correct": "incorrect"));
//
//    }
    }
}

