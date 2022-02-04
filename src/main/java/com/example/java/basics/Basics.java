package com.example.java.basics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Basics {
    public static void main(String[] args) {
    }
}

class ListsAndArrays {
    public static void main(String[] args) {
        /*Массивы*/
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("1", "2", "3");
        int[] intList1 = Stream.of("1", "2", "3").mapToInt(Integer::parseInt).toArray();
        List<Integer> intList2 = Stream.of("1", "2", "3").mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
//        Stream.of("1", "2", "3").mapToInt(Integer::parseInt).forEach(System.out::println);

        // это массив
        String[] arr1 = {"Max", "Aliya", "Lili", "Alice"};
        int[] arr2 = {11, 22, 33, 44};
        int[] arr3 = new int[5];

        int[] b = new int[10];
        boolean[] b1 = new boolean[10];
        Object[] b2 = new Object[10];

//        System.out.println(b[0]); // 0 - заполнится 0ми
//        System.out.println(b1[0]); // false
//        System.out.println(b2[0]); // null

//        public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        // копирование
        System.arraycopy(arr2, 0, arr3, 0, arr2.length);
//        for (int i : arr22) System.out.println(i); // 11 22 33 44 0

        // это уже не массив а коллекция)
        // фиксированная - не смогу сделать add
        List<String> list = List.of("Max", "Aliya", "Lili", "Alice");
        List<String> arraysAsList = Arrays.asList("Max", "Aliya", "Lili", "Alice");
        // расширяемая - не смогу сделать add
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new LinkedList<String>();

        arrayList.add("Max");
        arrayList.add("Aliya");
        arrayList.add("Lili");
        arrayList.add("Alice");

        linkedList.add("Max");
        linkedList.add("Aliya");
        linkedList.add("Lili");
        linkedList.add("Alice");

//        System.out.println(arrayList.subList(1,3));
//        System.out.println(linkedList.subList(1,3));
//
//        Object[] listToArray = list.toArray();
//
//        for (Object key : listToArray) {
//            System.out.println(key);
//        }
//
//        list.add("asd"); // ошибко
//        for (String key : list) {
//            System.out.println(key);
//        }
//
//        arrayList.add("asd"); // норм
//        for (String key : arrayList) {
//            System.out.println(key);
//        }
//
//        linkedList.add("asd"); // норм
//        for (String key : linkedList) {
//            System.out.println(key);
//        }


        /*Циклы*/
        // простой цикл
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("index " + i + " = " + list.get(i));
//        }

//        for (int i : arr2) {
//            System.out.println(i);
//        }

        // сложный цикл
//        for (int i = 0, j = arr1.length - 1; i < arr1.length && j >= 0; i++, j--) {
//            System.out.println("i " + i);
//            System.out.println("j " + j);
//        }
    }
}

class SpeedTest {
    public static void main(String[] args) {
        // проверка скорости работы
        // есть еще System.nanoTime(); до наносекунд но зачем?) достаточно мили
        List<String> listNano = new ArrayList<String>();
        List<String> linkedListNano = new LinkedList<String>();

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            listNano.add("" + i);
        }
        long t2 = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            linkedListNano.add("" + i);
        }
        long t3 = System.currentTimeMillis();

//        System.out.println("ArrayList " + (t2 - t1)); // 9 milli seconds
//        System.out.println("LinkedList " + (t3 - t2)); // 2 milli seconds
    }
}

class Regexps {
    public static void main(String[] args) {
        // regexps
        Pattern pattern = Pattern.compile("(.*?) ([A-Z][a-z]*), ([\\d]*?),\\s(.*)");
        Matcher matcher = pattern.matcher("Ivanov Pavel, 25, Moscow");
        if (matcher.matches()) {
            System.out.println("First name: " + matcher.group(1));
            System.out.println("Last name: " + matcher.group(2));
            System.out.println("Age: " + matcher.group(3));
            System.out.println("City: " + matcher.group(4));
        }
    }
}

class Numbers {
    public static final double Pound = 0.45359237d;

    public static double convertPoundsToKilos(double pounds) {
        return pounds * Numbers.Pound;
    }

    public static void main(String[] args) {
        /*Числа*/
//        int chinaPopulation = 1360000000;
//        int indiaPopulation = 1240000000;
//
//        long chinaPopulationL = 1360000000L;
//        long indiaPopulationL = 1240000000L;
//
//        System.out.println(chinaPopulation + indiaPopulation); // -1694967296 ))
//        System.out.println(chinaPopulationL + indiaPopulationL); // 2600000000

        int val = 1000;
        int minIntVal = Integer.MIN_VALUE;
        int maxIntVal = Integer.MAX_VALUE;

//        System.out.println(minIntVal);
//        System.out.println(maxIntVal);
//        System.out.println(maxIntVal + 1); // -2147483648 - это overflow
//        System.out.println(minIntVal - 1); // 2147483647 - это underflow

        long myLongValue = 1000L;
        long minLongValue = Long.MIN_VALUE;
        long maxLongValue = Long.MAX_VALUE;

//        System.out.println(minLongValue); // -9223372036854775808
//        System.out.println(maxLongValue); // 9223372036854775807
//        System.out.println(maxLongValue + 1);
//        System.out.println(minLongValue - 1);

        // если вдруг точно уверен, то можно кастовать типы в числах как тут:
        // без  (byte) будет ошибко тк типо меньше чем минимальное байт значение
        byte minByte = Byte.MIN_VALUE;
        byte myMinByteNum = (byte) (minByte / 2);

        float minFloatValue = Float.MIN_VALUE;
        float maxFloatValue = Float.MAX_VALUE;

        double minDoubleValue = Double.MIN_VALUE;
        double maxDoubleValue = Double.MAX_VALUE;

//        System.out.println(minFloatValue); // 1.4E-45
//        System.out.println(maxFloatValue); // 3.4028235E38 (32 bit)
//        System.out.println(minDoubleValue); // 4.9E-324
//        System.out.println(maxDoubleValue); // 1.7976931348623157E308 (64 bit)

        int testInt = 5;

        /* @deprecated */
        float testFloat = 5.25f;
        /* @deprecated */
        float testFloat1 = (float) 5.25; // без кастинга ошибко

        // В основном использую double!!!
        double testDouble = 5.25d;

        int intDevision = 5 / 2; // 2
        float floatDevision = 5f / 2f; // 2.5
        float floatDevision1 = 5f / 3f; // 1.6666666
        double doubleDevision = 5d / 2d; // 2.5
        double doubleDevision1 = 5d / 3d; // 1.6666666666666667

        System.out.println(intDevision);
        System.out.println(floatDevision);
        System.out.println(doubleDevision);
        System.out.println(floatDevision1);
        System.out.println(doubleDevision1);

        System.out.println(Numbers.convertPoundsToKilos(200d));
    }
}

class Primitives {
    public static void main(String[] args) {
        // char
        String name = "Max"; // 40*8 bits
        char testChar = 'M'; // 2*8 bits
        char testChar1 = '\u004D'; // M в unicode https://unicode-table.com/
        System.out.println(testChar); // M
        System.out.println(testChar1); // M

        // boolean // predicate
        boolean isBool = true;
        boolean isBool1 = false;
    }
}

/* String is not primitive it's a Class.
 * Strings are immutable - u cant change them, only create a new String */
class Strings {
    public static void main(String[] args) {
        String str = "Max";
        System.out.println(str);
        System.out.println(str + " " + str);
        System.out.println((str + " ").repeat(3).trim());
    }
}

class Operators {
    public static void main(String[] args) {
        int modulo = 4 % 3;
//        System.out.println(modulo); // 1

        int test = 1;
        int test1 = 1;
        System.out.println(++test); // 2
        System.out.println(test += 1); // 3
        System.out.println(test1++); // 1
        System.out.println(test1 += 1); // 3

        int val = 50;

        if (val == 50) {
            System.out.println(50);
        }

        boolean isVal = false;
        if (!isVal) System.out.println("!isVal");

        double val1 = 20d;
        double val2 = 80d;

        double res = (val1 + val2) * 100;
        double resModulo = res % 40d;

        System.out.println(res);
        System.out.println(resModulo);

        boolean isBoolean = resModulo == 0;

        System.out.println(isBoolean);

        if (!isBoolean) {
            System.out.println("Got some remainder");
        }
    }
}

class Cases {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.put("camelCase", "camelCase");
        map.put("PascalCase", "PascalCase");
        map.put("kebab-case", "kebab-case");
        map.put("snake_case", "snake_case");
    }
}

class Methods {
    @Data
    @AllArgsConstructor
    static class Member {
        String name;
        long score;
    }

    // тут сработает calculate так как это тоже static метод
    public static void main(String[] args) {
        // или так
        List<Member> members = Arrays.asList(new Member("Max", 1500L), new Member("Aliya", 900L), new Member("Lili", 400L), new Member("Alice", 50L) );

        members.forEach(val -> displayHighScorePosition(val.name, val.score));

        // или так
        displayHighScorePosition("Max", 1500L);
        displayHighScorePosition("Aliya", 900L);
        displayHighScorePosition("Lili", 400L);
        displayHighScorePosition("Alice", 50L);
    }

    public static int calculate(boolean gameOver, int level, int points) {
        if (gameOver) return level * points;

        return -1;
    }

    public static void displayHighScorePosition(String name, long score) {
        System.out.println(name + " managed to get into position " + calculateHighScorePosition(score));
    }

    public static int calculateHighScorePosition(long score) {
        if (score >= 1000L) return 1;
        if (score >= 500L) return 2;
        if (score >= 100L) return 3;

        return 4;
    }
}