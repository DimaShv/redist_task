package task.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@FunctionalInterface
public interface DateTimeProvider {
    long currentMillis();

    default LocalDateTime getCurrentDateTime() {
        return getLocalDateTimeFromMillis(currentMillis());
    }

    static LocalDateTime getLocalDateTimeFromMillis(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }

    static long getMillisFromLocalDateTime(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
