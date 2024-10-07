package com.example.utils;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
@Slf4j
public class ParsingUtils {
    public Integer parseIntegerFromString(String s) {
        if (s == null) {
            return null;
        }

        s = s.replaceAll("\\D", "");
        return s.isEmpty() ? null : Integer.parseInt(s);
    }

    public Double parseDoubleFromString(String s) {
        if (s == null) {
            return null;
        }

        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            String numberStr = matcher.group();
            return  Double.parseDouble(numberStr);
        }

        return null;
    }
}

class ParsingUtilsTest {
    public static void main(String[] args) {
        ParsingUtils parsingUtils = new ParsingUtils();

        System.out.println(parsingUtils.parseIntegerFromString("17.123 asd")); // 17123
        System.out.println(parsingUtils.parseDoubleFromString("17.123 asd")); // 17.123
    }
}