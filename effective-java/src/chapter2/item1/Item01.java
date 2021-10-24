package Ex01;

import java.lang.reflect.Array;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import static java.util.concurrent.TimeUnit.HOURS;


public class Item01 {
   // static으로 성능을 향상하자!
    public static void main(String[] args) {

        Object newArray = Array.newInstance();
        FileStore fs = Files.getFileStore("/");

    }

    public static LocalTime of(int hour, int minute) {
        ChronoField.HOUR_OF_DAY.checkValidValue((long) hour);
        if (minute == 0) {
            return HOURS[hour];
        } else {
            ChronoField.MINUTE_OF_HOUR.checkValidValue((long) minute);
            return new LocalTime(hour, minute, 0, 0);
        }
    }

}
