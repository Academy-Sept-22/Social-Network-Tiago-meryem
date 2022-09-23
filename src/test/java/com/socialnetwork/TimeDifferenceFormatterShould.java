package com.socialnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeDifferenceFormatterShould {

    private TimeDifferenceFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = new TimeDifferenceFormatter();
    }

    @Test
    void return_1_second_ago() {

        LocalDateTime time = LocalDateTime.now();
        assertEquals("1 second ago",
                formatter.formatTimeDifference(time, time.plusSeconds(1)));

    }

    @Test
    void return_5_seconds_ago() {

        LocalDateTime time = LocalDateTime.now();
        assertEquals("5 seconds ago",
                formatter.formatTimeDifference(time, time.plusSeconds(5)));

    }

    @Test
    void return_1_minute_ago() {

        LocalDateTime time = LocalDateTime.now();
        assertEquals("1 minute ago",
                formatter.formatTimeDifference(time, time.plusMinutes(1)));

    }

    @Test
    void return_5_minutes_ago() {

        LocalDateTime time = LocalDateTime.now();
        assertEquals("5 minutes ago",
                formatter.formatTimeDifference(time, time.plusMinutes(5)));

    }

    @Test
    void return_90_minutes_ago() {

        LocalDateTime time = LocalDateTime.now();
        assertEquals("90 minutes ago",
                formatter.formatTimeDifference(time, time.plusMinutes(90)));

    }
}