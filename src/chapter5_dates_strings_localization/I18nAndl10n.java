package chapter5_dates_strings_localization;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * internationalization and localization
 *
 * @author advortco
 */
public class I18nAndl10n {
    public static void main(String[] args) throws ParseException {
        Locale locale = Locale.getDefault();
        System.out.println(locale);

//        You can also use a Locale other than the default. There are three main ways of creating
//        a Locale. First, the Locale class provides constants for some of the most commonly used
//        locales:
        System.out.println(Locale.GERMAN); // de
        System.out.println(Locale.GERMANY); // de_DE
//        Notice that the first one is the German language and the second is Germany the country—
//        similar, but not the same. The other two main ways of creating a Locale are to use the
//        constructors. You can pass just a language or both a language and country:
        System.out.println(new Locale("fr")); // fr
        System.out.println(new Locale("hi", "IN")); // hi_IN

//        The following two Locales both represent en_US.
        Locale l1 = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();
        Locale l2 = new Locale.Builder()
                .setRegion("US")
                .setLanguage("en")
                .build();

        l2 = new Locale.Builder() // bad but legal
                .setRegion("us")
                .setLanguage("EN")
                .build();

        //Locale.Builder also lets you do other bad things like create a blank Locale. Please don’t.


//        You can set a new default right in Java:
        System.out.println(Locale.getDefault()); // en_US
        Locale locale1 = new Locale("fr");
        Locale.setDefault(locale1); // change the default
        System.out.println(Locale.getDefault()); // fr
//        Try it, and don’t worry—the Locale changes only for that one Java program. It does not
//        change any settings on your computer. It does not even change future programs. If you run
//        the previous code multiple times, the output will stay the same.

        System.out.println("================with bundle=======================");
        //with bundle
        Locale us = new Locale("en", "US");
        Locale france = new Locale("fr", "FR");
        printProperties(us);
        System.out.println();
        printProperties(france);


        System.out.println("===================a method to get a set of all keys=====================");
        // The ResourceBundle class provides a method to get a set of all keys:
        Locale us2 = new Locale("en", "US");
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", us2);
        Set<String> keys = rb.keySet();
        keys.stream()
                .map(k -> k + " -> " + rb.getString(k))
                .forEach(System.out::println);

//        Properties has some additional features, including being able to pass a default.
//        Converting from ResourceBundle to Properties is easy:

        System.out.println("==================Properties========================");
        Properties props = new Properties();
        rb.keySet().stream()
                .forEach(k -> props.put(k, rb.getString(k)));
        //Here we went through each key and used a Consumer to add it to the Properties object.
        //Now that we have Properties available, we can get a default value:
        System.out.println(props.getProperty("notReallyAProperty")); //null
        System.out.println(props.getProperty("notReallyAProperty", "123")); //123


        System.out.println("================Formatting nums===================");
//        The following shows printing out the same number in three
//        different locales:

        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;
        NumberFormat us3 = NumberFormat.getInstance(Locale.US);
        System.out.println(us3.format(attendeesPerMonth));
        NumberFormat g = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(g.format(attendeesPerMonth));
        NumberFormat ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(attendeesPerMonth));


        System.out.println("==================Currency========================");
        double price = 48;
        NumberFormat us4 = NumberFormat.getCurrencyInstance();
        System.out.println(us4.format(price));
//        When run with the default locale of en_US, the output is $48.00. Java automatically formats with two decimals and adds the dollar sign. This is convenient even if you don’t need
//        to localize your program!


        System.out.println("==================================================");
//        Let’s look at an example. The following code parses a discounted ticket price with different locales:
        NumberFormat en2 = NumberFormat.getInstance(Locale.US);
        NumberFormat fr2 = NumberFormat.getInstance(Locale.FRANCE);
        String s = "40.45";
        System.out.println(en2.parse(s)); // 40.45
        System.out.println(fr2.parse(s)); // 40
//        In the United States, a dot is part of a number and the number is parsed how you might
//        expect. France does not use a decimal point to separate numbers. Java parses it as a formatting character, and it stops looking at the rest of the number. The lesson is to make sure
//        that you parse using the right locale!

        System.out.println("===============DateTimeFormatter=======================");
//        What changes is the format. DateTimeFormatter is in the package
//        java.time.format.
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));


        System.out.println("===============SHORT-MEDIUM=======================");
//        There are two predefined formats that can show up on the exam: SHORT and MEDIUM. The
//        other predefined formats involve time zones, which are not on the exam.
        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time1 = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        DateTimeFormatter shortF = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(shortF.format(dateTime1)); // 1/20/20 11:12 AM
        System.out.println(mediumF.format(dateTime1)); // Jan 20, 2020 11:12:34 AM


        System.out.println("==================================================");
//        If you don’t want to use one of the predefined formats, you can create your own. For
//        example, this code spells out the month:
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
        System.out.println(dateTime.format(f)); // January 20, 2020, 11:12
        System.out.println("==================================================");
        System.out.println("==================================================");


        double amount = 123456.789;
        Locale fr = new Locale("fr", "FR");
        NumberFormat formatter = NumberFormat.getInstance(fr);
        String s1 = formatter.format(amount);
        formatter = NumberFormat.getInstance();
        Number amount2 = formatter.parse(s1);
        System.out.println(amount + " " + amount2);
    }


    public static void printProperties(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
        System.out.println(rb.getString("hello"));
        System.out.println(rb.getString("open"));
    }
}
