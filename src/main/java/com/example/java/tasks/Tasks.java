package com.example.java.tasks;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Tasks {
}

class LeapYear {
    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) return false;

        return (year % 4 == 0 && year % 100 != 0)
                || (year % 4 == 0 && year % 100 == 0 && year % 400 == 0);
    }
}

class DecimalComparator {
    public static boolean areEqualByThreeDecimalPlaces(double num, double num1) {
        DecimalFormat df = new DecimalFormat("###.###");
        double curNum = Double.parseDouble(df.format(num));
        double curNum1 = Double.parseDouble(df.format(num1));

        return curNum == curNum1;
    }

    public static void main(String[] args) {
        System.out.println(areEqualByThreeDecimalPlaces(3.175, 3.176));
    }
}

class DecimalComparator1 {
    public static boolean areEqualByThreeDecimalPlaces(double num, double num1) {
        int res = (int) (num * 1000);
        int res1 = (int) (num1 * 1000);

        return res - res1 == 0;
    }

    public static void main(String[] args) {
        System.out.println(areEqualByThreeDecimalPlaces(3.176, 3.175));
    }
}

class SecondsAndMinutesChallenge {
    private static final String INVALID_VALUE = "Invalid value";

    public static void main(String[] args) {
        System.out.println(getDuration(1000000L)); // 11d 13h 46m 46s
        System.out.println(getDuration(100L, 20L)); // 1h 40m 20s
    }

    public static String getDuration(long minutes, long seconds) {
        if ((minutes < 0) || (seconds < 0) || (seconds > 59)) return INVALID_VALUE;

        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;
        long days = hours / 24;
        long remainingHours = hours % 24;

        return days + "d " + remainingHours + "h " + remainingMinutes + "m " + seconds + "s";
    }

    public static String getDuration(long seconds) {
        if (seconds < 0) return INVALID_VALUE;

        long minutes = seconds / 60;
        long remainingSeconds = minutes % 60;

        return getDuration(minutes, remainingSeconds);
    }
}

class MinutesToYearsDaysCalculator {
    public static final String INVALID_VALUE = "Invalid Value";

    public static void main(String[] args) {
        printYearsAndDays(1440);
    }

    public static void printYearsAndDays(long minutes) {
        if (minutes < 0) {
            System.out.println(INVALID_VALUE);
            return;
        }

        long years = minutes / 60 / 24 / 365;
        long remainingYears = minutes % (60 * 24 * 365);
        long days = remainingYears / 24 / 60;

        System.out.println(minutes + " min = " + years + " y and " + days + " d");
    }
}

class NumberOfDaysInMonth {
    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) return false;

        return (year % 4 == 0 && year % 100 != 0)
                || (year % 4 == 0 && year % 100 == 0 && year % 400 == 0);
    }

    public static int getDaysInMonth(int month, int year) {
        if (month < 1 || month > 12) return -1;
        if (year < 1 || year > 9999) return -1;

        switch (month) {
            case 2: {
                return NumberOfDaysInMonth.isLeapYear(year) ? 29 : 28;
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                return 30;
            }
            default: {
                return 31;
            }
        }
    }
}

class IsPrime {
    public static void main(String[] args) {
        System.out.println(IsPrime.isPrime(2));
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }

        return true;
    }
}

class SumTask {
    public static void main(String[] args) {
        System.out.println(SumTask.countSum(1000));
    }

    public static int countSum(int n) {
        int counter = 0;
        int sum = 0;
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (counter > 5) break;

            if (i % 3 == 0 || i % 5 == 0) {
                counter++;
                sum += i;
                arr.add(i);
            }
        }

        System.out.println(arr);

        return sum;
    }
}