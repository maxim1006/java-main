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

class SumDigits {
    public static void main(String[] args) {
        System.out.println("sum of digits 125 = " + SumDigits.sumDigits(125)); // 8
        System.out.println("sum of digits 125 = " + SumDigits.sumDigits(4)); // -1
        System.out.println("sum of digits 125 = " + SumDigits.sumDigits(200)); // 2
    }

    private static int sumDigits(int num) {
        if (num < 10) return -1;

        int sum = 0;

        while (num > 0) {
            // так прикольно получать цифры по одной
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}

class NumberPalindrome {
    public static void main(String[] args) {
        System.out.println(NumberPalindrome.isPalindrome(-222));
    }

    public static boolean isPalindrome(int number) {
        int positiveNumber = Math.abs(number);

        StringBuffer reversedNumber = new StringBuffer(positiveNumber + "").reverse();
        return (positiveNumber + "").equals(reversedNumber.toString());
    }
}

class FirstLastDigitSum {
    public static void main(String[] args) {
        System.out.println(FirstLastDigitSum.sumFirstAndLastDigit(123));
    }

    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) return -1;

        String str = Integer.toString(number);
        String first = str.substring(0, 1);
        String last = str.substring(str.length() - 1);

        return Integer.parseInt(first) + Integer.parseInt(last);
    }
}

class EvenDigitSum {
    public static void main(String[] args) {
        System.out.println(EvenDigitSum.getEvenDigitSum(1234));
    }

    public static int getEvenDigitSum(int number) {
        if (number < 0) return -1;

        int sum = 0;

        while (number > 0) {
            // так прикольно получать цифры по одной
            int current = number % 10;

            if (current % 2 == 0) sum += current;

            number /= 10;
        }

        return sum;
    }
}

class SharedDigit {
    public static void main(String[] args) {
        System.out.println(SharedDigit.hasSharedDigit(12, 23));
    }

    public static boolean hasSharedDigit(int num, int num1) {
        if (num < 10 || num > 99) return false;
        if (num1 < 10 || num1 > 99) return false;

        String str = Integer.toString(num);
        String str1 = Integer.toString(num1);

        for (int i = 0; i < str.length(); i++) {
            if (str1.contains(str.charAt(i) + "")) {
                return true;
            }
        }

        return false;
    }
}

class LastDigitChecker {
    public static void main(String[] args) {
        System.out.println(LastDigitChecker.hasSameLastDigit(41, 22, 71));
    }

    public static boolean hasSameLastDigit(int num, int num1, int num2) {
        if (!LastDigitChecker.isValid(num) || !LastDigitChecker.isValid(num1) || !LastDigitChecker.isValid(num2))
            return false;

        char numChar = Integer.toString(num).charAt((num + "").length() - 1);
        char num1Char = Integer.toString(num1).charAt((num1 + "").length() - 1);
        char num2Char = Integer.toString(num2).charAt((num2 + "").length() - 1);

        if (numChar == num1Char || num1Char == num2Char || numChar == num2Char) return true;

        return false;
    }

    public static boolean isValid(int num) {
        return num > 9 && num < 1001;
    }
}

class GreatestCommonDivisor {
    public static void main(String[] args) {
        System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(1010, 10));
    }

    public static int getGreatestCommonDivisor(int first, int second) {
        if (first < 10 || second < 10) return -1;

        int divisor = 1;
        int counter = 1;

        while (counter <= first && counter <= second) {
            if (first % counter == 0 && second % counter == 0 && counter > divisor) {
                divisor = counter;
            }

            counter++;
        }

        return divisor;
    }
}

class FactorPrinter {
    public static void main(String[] args) {
        FactorPrinter.printFactors(6);
    }

    public static void printFactors(int num) {
        if (num < 1) {
            System.out.println("Invalid Value");
            return;
        }

        int counter = 1;

        while (counter <= num) {
            if (num % counter == 0) {
                System.out.println(counter);
            }

            counter++;
        }
    }
}

class PerfectNumber {
    public static void main(String[] args) {
        System.out.println(PerfectNumber.isPerfectNumber(6)); // true
        System.out.println(PerfectNumber.isPerfectNumber(7)); // false
    }

    public static boolean isPerfectNumber(int num) {
        if (num < 1) return false;

        int counter = 1;
        int sum = 0;

        while (counter < num) {
            if (num % counter == 0) {
                sum += counter;
            }

            counter++;
        }

        return sum == num;
    }
}

class NumberToWords {
    public static enum NumberToWordsEnum {
        Zero("Zero"),
        One("One"),
        Two("Two"),
        Three("Three"),
        Four("Four"),
        Five("Five"),
        Six("Six"),
        Seven("Seven"),
        Eight("Eight"),
        Nine("Nine");

        NumberToWordsEnum(String name) {
            this.name = name;
        }

        private final String name;

        public String getName() {
            return name;
        }
    }

    private static final String[] dayList = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    public static void main(String[] args) {
        NumberToWords.numberToWords(123);
    }

    public static void numberToWords(int num) {
        if (num < 0) {
            System.out.println("Invalid value");
            return;
        }

        String str = num + "";

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            int currentNum = Integer.parseInt(currentChar + "");
            System.out.println(dayList[currentNum]);
        }
    }
}

class FlourPacker {
    public static void main(String[] args) {
        System.out.println(FlourPacker.canPack(1, 0, 4));
        System.out.println(FlourPacker.canPack(1, 0, 5));
        System.out.println(FlourPacker.canPack(0, 5, 6));
        System.out.println(FlourPacker.canPack(4, 18, 19));
    }

    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal <= 0) return false;

        while (bigCount > 0 && goal > 0) {
            if (goal - 5 >= 0) goal -= 5;
            if (goal == 0) return true;

            --bigCount;
        }

        while (smallCount > 0 && goal > 0) {
            goal -= 1;

            if (goal == 0) return true;

            --smallCount;
        }

        return false;
    }
}

class LargestPrime {
    public static void main(String[] args) {
        System.out.println(LargestPrime.getLargestPrime(10));
    }

    public static int getLargestPrime(int num) {
        if (num <= 1) return -1;

        int counter = 1;
        int result = 0;

        class Local {
            boolean isPrime(int n) {
                if (n <= 1) return false;

                for (int i = 2; i <= n / 2; i++) {
                    if (n % i == 0) return false;
                }

                return true;
            }
        }

        Local local = new Local();

        while (num >= counter) {
            if (num % counter == 0 && local.isPrime(counter) && counter > result) result = counter;
            ++counter;
        }

        return result;
    }
}

class DiagonalStar {
    public static void main(String[] args) {
        DiagonalStar.printSquareStar(8);
    }

    public static void printSquareStar(int num) {
        if (num < 5) {
            System.out.println("Invalid value");
            return;
        }

        int row = 1;
        int column;

        while (num >= row) {
            column = 1;

            while (num >= column) {

                if (row == num
                    || row == 1
                    || column == 1
                    || column == num
                    || row == column
                    || column == (num - row + 1)
                ) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }

                ++column;
            }

            System.out.println();

            ++row;
        }
    }
}