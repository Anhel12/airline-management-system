package com.example.Utils;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeUtils {

    private TimeUtils() {}

    public static Duration getDurationBetween(LocalDateTime firstTime, LocalDateTime secondTime){
        return Duration.between(firstTime, secondTime);
    }

    public static String durationFormat(Duration duration){
        var hours = duration.toHours();
        duration = duration.minusHours(hours);
        var minutes = duration.toMinutes();
        return hours + ":" + minutes;
    }
}
