package chapter5_dates_strings_localization;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Dates
 *
 * @author advortco
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());

        System.out.println("================================================");
//        To begin, let’s create just a chapter5.date with no time. Both of these examples create the same chapter5.date:
        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2015, 1, 20);

        LocalTime time1 = LocalTime.of(6, 15); // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds

        System.out.println("================================================");
//        You can combine dates and times into one object:
        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);

        System.out.println("================================================");
//        In order to create a ZonedDateTime, we first need to get the desired time zone. We will
//        use US/Eastern in our examples:
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20,
                6, 15, 30, 200, zone); //Notice that there isn’t an option to pass in the Month enum.
        // This seems like an oversight
//        from the API creators and something that will be fixed in future versions of Java.
        ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
        ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);

        System.out.println("================================================");
//        Finding out your time zone is easy. You can just print out ZoneId.systemDefault() or print
//        out a sorted list of the ones that are potential candidates:
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("RU") || z.contains("Volgograd"))
                .sorted().forEach(System.out::println);

        System.out.println("================================================");
        System.out.println(LocalDate.of(2096, Month.FEBRUARY, 29)); //leap
//        System.out.println(LocalDate.of(2097, Month.FEBRUARY, 29));
//        System.out.println(LocalDate.of(2098, Month.FEBRUARY, 29));
//        System.out.println(LocalDate.of(2099, Month.FEBRUARY, 29));
//        System.out.println(LocalDate.of(2100, Month.FEBRUARY, 29));
//        System.out.println(LocalDate.of(2101, Month.FEBRUARY, 29));
//        System.out.println(LocalDate.of(2102, Month.FEBRUARY, 29));
        System.out.println("===================Period=======================");
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        Period period = Period.ofMonths(1); // create a period
        performAnimalEnrichment(start, end, period);

        System.out.println("================================================");
//        There are five ways to create a Period class:
        Period annually = Period.ofYears(1); // every 1 year
        Period quarterly = Period.ofMonths(3); // every 3 months
        Period everyThreeWeeks = Period.ofWeeks(3); // every 3 weeks
        Period everyOtherDay = Period.ofDays(2); // every 2 days
        Period everyYearAndAWeek = Period.of(1, 0, 7); // every year and 7 days

        Period wrong = Period.ofYears(1).ofWeeks(1); // every week
//        This tricky code is really like writing the following:
        Period wrong1 = Period.ofYears(1);
        wrong1 = Period.ofWeeks(1);
//        This is clearly not what you intended! That’s why the of() method allows you to pass in
//        the number of years, months, and days. They are all included in the same period. You will
//        get a compiler warning about this.


        System.out.println("================================================");
        // period format:
        System.out.println(Period.of(1, 2, 3)); // P1Y2M3D
        System.out.println(Period.ofMonths(3)); // P3M
        System.out.println(Period.of(0, 20, 47));// The output is P20M47D. There are no years,
        // so that part is skipped. It’s OK to have more
//        days than are in a month. Also it is OK to have more months than are in a year. Java uses
//        the measures provided for each.
        System.out.println(Period.ofWeeks(3)); // P21D.


        System.out.println("================================================");
        LocalDate date = LocalDate.of(2015, 1, 20);
        LocalTime time = LocalTime.of(6, 15);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Period period1 = Period.ofMonths(1);
        System.out.println(date.plus(period1).plus(period1)); // 2015–03–20
        System.out.println(dateTime.plus(period1)); // 2015–02–20T06:15
//        System.out.println(time.plus(period1)); // UnsupportedTemporalTypeException


        System.out.println("=====================Duration========================");
//        We can create a Duration using a number of different granularities:
        Duration daily = Duration.ofDays(2); // PT24H
        System.out.println(daily);
        Duration hourly = Duration.ofHours(1); // PT1H
        Duration everyMinute = Duration.ofMinutes(1); // PT1M
        Duration everyTenSeconds = Duration.ofSeconds(10); // PT10S
        Duration everyMilli = Duration.ofMillis(1); // PT0.001S
        Duration everyNano = Duration.ofNanos(1); // PT0.000000001S

        System.out.println("================================================");
//        Duration includes another more generic factory method. It takes a number and a
//        TemporalUnit. The idea is, say, something like “5 seconds.” However, TemporalUnit is an
//        interface. At the moment, there is only one implementation named ChronoUnit.
//        The previous example could be rewritten as this:
        daily = Duration.of(1, ChronoUnit.DAYS);
        hourly = Duration.of(1, ChronoUnit.HOURS);
        everyMinute = Duration.of(1, ChronoUnit.MINUTES);
        everyTenSeconds = Duration.of(10, ChronoUnit.SECONDS);
        everyMilli = Duration.of(1, ChronoUnit.MILLIS);
        everyNano = Duration.of(1, ChronoUnit.NANOS);
        Duration halfDays = Duration.of(1, ChronoUnit.HALF_DAYS); // represent 12 hours.
        System.out.println(daily);
        System.out.println(hourly);
        System.out.println(everyMinute);
        System.out.println(everyTenSeconds);
        System.out.println(everyMilli);
        System.out.println(everyNano);
        System.out.println(halfDays);

        System.out.println("====================between=========================");
//        ChronoUnit is a great way to determine how far apart two Temporal values are. Temporal
//        includes LocalDate, LocalTime, and so on.
        LocalTime one = LocalTime.of(5, 15);
        LocalTime two = LocalTime.of(6, 30);
        LocalDate date3 = LocalDate.of(2016, 1, 20);
        System.out.println(ChronoUnit.HOURS.between(one, two)); // 1
        System.out.println(ChronoUnit.HOURS.between(two, one)); // -1
        System.out.println(ChronoUnit.MINUTES.between(one, two)); // 75
//        System.out.println(ChronoUnit.MINUTES.between(one, date3)); // DateTimeException


        System.out.println("================================================");
//        Remember that Period and Duration are not equivalent. This example shows a Period
//        and Duration of the same length:
        LocalDate date31 = LocalDate.of(2015, 5, 25);
        Period period3 = Period.ofDays(1);
        Duration days = Duration.ofDays(1);
        System.out.println(date31.plus(period3)); // 2015–05–26
//        System.out.println(date31.plus(days)); // Unsupported unit: Seconds

//        Since we are working with a LocalDate, we are required to use Period. Duration has time
//        units in it, even if we don’t see them and they are meant only for objects with time.

        System.out.println("==================Instant==========================");
//        The Instant class represents a specific moment in time in the GMT time zone.

        Instant now = Instant.now();
        Thread.sleep(11); // do something time consuming
        Instant later = Instant.now();
        Duration duration = Duration.between(now, later);
        System.out.println(duration.toMillis());

//        If you have a ZonedDateTime, you can turn it into an Instant:
        LocalDate date4 = LocalDate.of(2015, 5, 25);
        LocalTime time4 = LocalTime.of(11, 55, 00);
        ZoneId zone4 = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date4, time4, zone4);
        Instant instant = zonedDateTime.toInstant(); // 2015–05–25T15:55:00Z
        System.out.println(zonedDateTime); // 2015–05–25T11:55–04:00[US/Eastern]
        System.out.println(instant); // 2015–05–25T15:55:00Z
//        The ZonedDateTime includes a time zone. The Instant gets rid of the time zone and turns
//        it into an Instant of time in GMT.

        System.out.println("================================================");
//        If you have the number of seconds since 1970, you can also create an Instant that way:
        long epochSeconds = 234234234L;
        Instant instant1 = Instant.ofEpochSecond(epochSeconds);
        System.out.println(instant1); // 2015–05–25T15:55:00Z

//        Using that Instant, you can do math. Instant allows you to add any unit day or smaller,
//        for example:
        Instant nextDay = instant1.plus(1, ChronoUnit.DAYS);
        System.out.println(nextDay); // 2015–05–26T15:55:00Z
        Instant nextHour = instant1.plus(1, ChronoUnit.HOURS);
        System.out.println(nextHour); // 2015–05–25T16:55:00Z
//        Instant nextWeek = instant.plus(1, ChronoUnit.WEEKS); // UnsupportedTemporalTypeException: Unsupported unit: Weeks

//        It’s weird that an Instant displays a year and month while preventing you from doing
//        math with those fields. Unfortunately, you need to memorize this fact.

        System.out.println("================Accounting for Daylight Savings Time======================");
//        For example, on March 13, 2016, we move our clocks forward an hour and jump from
//        2:00 a.m. to 3:00 a.m. This means that there is no 2:30 a.m. that day. If we wanted to
//        know the time an hour later than 1:30, it would be 3:30.

        LocalDate date5 = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time5 = LocalTime.of(1, 30);
        ZoneId zone5 = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime5 = ZonedDateTime.of(date5, time5, zone5);
        System.out.println(dateTime5); // 2016–03–13T01:30–05:00[US/Eastern]
        dateTime5 = dateTime5.plusHours(1);
        System.out.println(dateTime5); // 2016–03–13T03:30–04:00[US/Eastern]


//        Similarly in November, an hour after the initial 1:30 is also 1:30 because at 2:00 a.m.
//        we repeat the hour. This time, try to calculate the GMT time yourself for all three times to
//        confirm that we really do move back only one hour at a time.
        LocalDate date6 = LocalDate.of(2016, Month.NOVEMBER, 6);
        LocalTime time6 = LocalTime.of(1, 30);
        ZoneId zone6 = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime6 = ZonedDateTime.of(date6, time6, zone6);
        System.out.println(dateTime6); // 2016–11–06T01:30–04:00[US/Eastern]
        dateTime6 = dateTime6.plusHours(1);
        System.out.println(dateTime6); // 2016–11–06T01:30–05:00[US/Eastern]
        dateTime6 = dateTime6.plusHours(1);
        System.out.println(dateTime6); // 2016–11–06T02:30–05:00[US/Eastern]
        //Did you get it? We went from 5:30 GMT to 6:30 GMT to 7:30 GMT.


        System.out.println("=================a time that doesn’t exist=========================");
        // Finally, trying to create a time that doesn’t exist just rolls forward:
        LocalDate date7 = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time7 = LocalTime.of(2, 30);
        ZoneId zone7 = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime7 = ZonedDateTime.of(date7, time7, zone7);
        System.out.println(dateTime7); // 2016–03–13T03:30–04:00[US/Eastern]


//        System.out.println("===========Creating a Java Class Resource Bundle=============");

        System.out.println("=================Test======================");
        String m1 = Duration.of(1, ChronoUnit.MINUTES).toString();
        String m2 = Duration.ofMinutes(1).toString();
        String s = Duration.of(60, ChronoUnit.SECONDS).toString();
        String d = Duration.ofDays(1).toString();
        String p = Period.ofDays(1).toString();
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(s);
        System.out.println(d);
        System.out.println(p);
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end,
                                                Period period) { // uses the generic period
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plus(period); // adds the period
        }
    }
}
