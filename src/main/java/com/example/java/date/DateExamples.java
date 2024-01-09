package com.example.java.date;

import java.text.SimpleDateFormat;

public class DateExamples {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        System.out.println(sdf.format(System.currentTimeMillis()));
    }
}
