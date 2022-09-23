package com.socialnetwork.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeDifferenceFormatter {

    public String formatTimeDifference(LocalDateTime dateTime, LocalDateTime currentTime) {
        Duration duration = Duration.between(dateTime, currentTime);
        if (duration.toSeconds() < 60) {
            if (duration.toSeconds() == 1) {
                return duration.toSeconds() + " second ago";
            }
            return duration.toSeconds() + " seconds ago";
        }
        if (duration.toMinutes() == 1) {
            return duration.toMinutes() + " minute ago";
        }
        return duration.toMinutes() + " minutes ago";
    }
}