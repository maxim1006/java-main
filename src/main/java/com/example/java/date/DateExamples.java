package com.example.java.date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateExamples {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        System.out.println(sdf.format(System.currentTimeMillis()));

        // локальная дата без смещения относительно UTC
        LocalDateTime nowLocal = LocalDateTime.now();
        System.out.println("Current LocalDateTime: " + nowLocal);

        // так будет ошибка так как LocalDateTime нельзя в смещение offset
        System.out.println("ISO 8601 nowLocal: " + nowLocal.format(DateTimeFormatter.ISO_LOCAL_DATE));

        // Дата со смещением относительно UTC
        OffsetDateTime nowOffset = OffsetDateTime.now();
        System.out.println("Current OffsetDateTime: " + nowOffset);
        System.out.println("ISO 8601 nowOffset: " + nowOffset.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
