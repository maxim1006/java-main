package com.example.java.date;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateExamples {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        System.out.println(sdf.format(System.currentTimeMillis()));

        // локальная дата без смещения относительно UTC
        LocalDateTime nowLocal = LocalDateTime.now();
        System.out.println("Current LocalDateTime: " + nowLocal);
        System.out.println("ISO 8601 nowLocal: " + nowLocal.format(DateTimeFormatter.ISO_LOCAL_DATE));

        // Дата со смещением относительно UTC
        OffsetDateTime nowOffset = OffsetDateTime.now();
        System.out.println("Current OffsetDateTime: " + nowOffset);
        System.out.println("ISO 8601 nowOffset: " + nowOffset.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        checkIsBeforeIsAfter();
    }

    private static void checkIsBeforeIsAfter() {
        LocalDateTime a = LocalDateTime.of(2012, 6, 30, 12, 00);
        LocalDateTime a1 = LocalDateTime.of(2012, 6, 30, 13, 00);
        LocalDateTime b = LocalDateTime.of(2012, 7, 1, 12, 00);

        System.out.println(a.isAfter(a1));

        System.out.println(Duration.between(parseISOToLocalDateTime(getNowLocalDate()), parseISOToLocalDateTime("2024-07-12")).toDays()); // поменяй дату на текущую чтобы посмотреть разницу, может быть и отрицательная
        System.out.println(parseISOToLocalDateTime(getNowLocalDate()).plusDays(10));
    }

    public static LocalDateTime parseISOToLocalDateTime(String date) {
        if (date == null) {
            return null;
        }
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        } catch (DateTimeException e) {
            log.warn("Can't parse/format date '{}'", date, e);
            return null;
        }
    }

    public static String getNowLocalDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
