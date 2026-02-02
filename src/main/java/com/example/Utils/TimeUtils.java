package com.example.Utils;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeUtils {

    public Duration getDurationBetween(LocalDateTime firstTime, LocalDateTime secondTime){
        return Duration.between(firstTime, secondTime);
    }

    public String durationFormat(Duration duration){
        var hours = duration.toHours();
        duration = duration.minusHours(hours);
        var minutes = duration.toMinutes();
        return hours + ":" + minutes;
    }
}
