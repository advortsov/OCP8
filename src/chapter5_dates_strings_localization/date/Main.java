package chapter5_dates_strings_localization.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

/**
 * This class
 *
 * @author advortco
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis();
        System.out.println(millis);


        LocalDate programmersDay1 = LocalDate.of(2016, 1, 1).plusDays(127);
        LocalDate programmersDay2 = LocalDate.of(2017, 1, 1).plusDays(255);

        System.out.println(programmersDay1);
        System.out.println(programmersDay2);


        TemporalAdjuster NEXT_WORKDAY = w -> {
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() > 5);
            return result;
        };

        LocalDate backToWork = LocalDate.now().minusDays(1).with(NEXT_WORKDAY);

        System.out.println(backToWork);


        String result = "3:41".replaceAll(
                "(\\d{1,2}):(?<minutes>\\d{2})",
                "$1 hours and ${minutes} minutes");
// Задает следующий результат: "3 hours and 45 minutes"
        System.out.println(result);

    }
}
