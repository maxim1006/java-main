package com.example.java.basics;

import com.example.java.enums.EnumEtalon;
import com.example.models.TestModel;
import com.example.utils.StubUtils;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.java.constants.InterfaceConstants.CONSTANT_FROM_LOCALIZATION1;
import static com.example.java.constants.LocalizationConstants.CONSTANT_FROM_LOCALIZATION;


public class Basics {
    public static void main(String[] args) {
    }
}

class VarAndConstantsClass {
    public static void main(String[] args) {
        // Слово var ставится вместо типа данных, а сам тип переменной выводится из того значения, которое ей присваивается
        var a = 10_10__10;
        System.out.println(a); // 101010

        final int NUM = 10; // не могу ничего присвоить
//        NUM = 11; // ошибко
    }
}

class ListsAndArrays {
    public static void main(String[] args) {
        /*Массивы*/
        // тоже что и String[] arr1 = new String[] {"Max", "Aliya", "Lili", "Alice"};
        String[] arr1 = {"Max", "Aliya", "Lili", "Alice"};
        int[] arr2 = {11, 22, 33, 44};
        int[] arr3 = new int[5];
        int[][] multiNum = {{0, 1}, {2, 3}};
        // первый столбец второй строки
        System.out.println(multiNum[1][0]); // 2
        multiNum[1][0] = 22;

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
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("1", "2", "3");
        int[] intList1 = Stream.of("1", "2", "3").mapToInt(Integer::parseInt).toArray();
        // тоже что и 3 только с .toList(
        List<Integer> intList2 = Stream.of("1", "2", "3").mapToInt(Integer::parseInt).boxed().toList();
        List<Integer> intList3 = Stream.of("1", "2", "3").mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
//        Stream.of("1", "2", "3").mapToInt(Integer::parseInt).forEach(System.out::println);

        // фиксированный массив - не могу сделать add
        List<String> list = List.of("Max", "Aliya", "Lili", "Alice");
        List<String> arraysAsList = Arrays.asList("Max", "Aliya", "Lili", "Alice"); // тут упадет если add сделать подсветки не будет от ide
        // расширяемый массив - смогу сделать add
        List<String> arrayList = new ArrayList<String>(); // использую его
        List<String> linkedList = new LinkedList<String>();

        // empty Lists
        System.out.println("empty " + Collections.emptyList()); // []

        arrayList.add("Max");
        arrayList.add("Aliya");
        arrayList.add("Lili");
        arrayList.add("Alice");

        linkedList.add("Max");
        linkedList.add("Aliya");
        linkedList.add("Lili");
        linkedList.add("Alice");
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

        // сделать из 1го айтема лист
        List<String> singletonList = Collections.singletonList("Max");

        List<String> streamedList = new ArrayList<>();

        streamedList.add("Max");

        // пример как найти первый и посмотреть что он есть
        streamedList.stream().findFirst().ifPresent(value -> {
            if (value != null) {
                System.out.println(value);
            }
        });

        // проверить есть ли в листе строке
//        System.out.println(list.stream().filter(i -> i.startsWith("Ma")).findFirst().orElse(null) != null);
//        System.out.println(list.stream().filter(i -> i.contains("Ma")).findFirst().orElse(null) != null);
//        System.out.println(list.stream().filter(i -> i.endsWith("Ma")).findFirst().orElse(null) != null);

        System.out.println(CollectionUtils.isEmpty(new ArrayList<String>())); // true
        System.out.println(CollectionUtils.isNotEmpty(arrayList)); // true

        // stream List of lists
        List<List<String>> listOfLists = new ArrayList<>();
        listOfLists.add(List.of("Max", "Aliya"));
        listOfLists.add(List.of("Lili", "Alice"));

        List<String> listOfStrings = listOfLists.stream()
                .flatMap(List::stream)
                .map(i -> i)
                .toList();

        System.out.println(listOfStrings); // [Max, Aliya, Lili, Alice]
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
        int matcherGroupCount = matcher.groupCount();

        System.out.println("matcher find: " + matcher.find());
        System.out.println("matcherGroupCount: " + matcherGroupCount);

        for (int i = 1; i <= matcherGroupCount; i++) {
            String group = matcher.group(i);

            int startIndex = matcher.start(group);
            int endIndex = matcher.end(group);

            System.out.println("group: " + group);
        }

        if (matcher.matches()) {
            System.out.println("First name: " + matcher.group(1));
            System.out.println("Last name: " + matcher.group(2));
            System.out.println("Age: " + matcher.group(3));
            System.out.println("City: " + matcher.group(4));
        }
    }
}

class Numbers {
    public static final double Pound = 0.45359237D;

    public static double convertPoundsToKilos(double pounds) {
        return pounds * Numbers.Pound;
    }

    public static void main(String[] args) {
        /*Числа*/
//        int chinaPopulation = 1360000000; // int - 4 bytes
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
        float testFloat = 5.25F;
        /* @deprecated */
        float testFloat1 = (float) 5.25; // без кастинга ошибко

        // В основном использую double!!!
        double testDouble = 5.25D;

        int intDevision = 5 / 2; // 2
        float floatDevision = 5F / 2F; // 2.5
        float floatDevision1 = 5F / 3F; // 1.6666666
        double doubleDevision = 5D / 2D; // 2.5
        double doubleDevision1 = 5D / 3D; // 1.6666666666666667

//        System.out.println(intDevision);
//        System.out.println(floatDevision);
//        System.out.println(doubleDevision);
//        System.out.println(floatDevision1);
//        System.out.println(doubleDevision1);

        System.out.println(Numbers.convertPoundsToKilos(200D));

        // round
        double a = 4.9;
        int aInt = (int) a;
        int aRound = (int) Math.round(a);
//        System.out.println(aInt); // 4 - surprise)
//        System.out.println(aRound); // 5

        // int + double = double
        int aa = 3;
        double bb = 4.6;
        double cc = aa + bb;
//        System.out.println(cc); // 7.6

        // char Если в операциях участвуют данные типа char, то они преобразуются в int
        int d = 'a' + 5;
//        System.out.println(d); // 102

        BigInteger integer = new BigInteger("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");

        BigDecimal decimal = new BigDecimal("123.444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444");

        BigDecimal decimalTest = decimal.multiply(
                BigDecimal.valueOf(100)
        );
        System.out.println(decimalTest);
        System.out.println(decimal.setScale(0, RoundingMode.CEILING));
        System.out.println(decimal.stripTrailingZeros());
    }
}

class Primitives {
    public static void main(String[] args) {
        // char
        String name = "Max \n" + "Aliya"; // 40*8 bits
        System.out.println(name);
        char testChar = 'M'; // 2*8 bits
        char testChar1 = '\u004D'; // M в unicode https://unicode-table.com/
        System.out.println(testChar); // M
        System.out.println(testChar1); // M

        // only in JDK15
//        String text = """
//                Вот мысль, которой весь я предан,
//                Итог всего, что ум скопил.
//                Лишь тот, кем бой за жизнь изведан,
//                Жизнь и свободу заслужил.
//                """;
//        System.out.println(text);

        // boolean // predicate
        boolean isBool = true;
        boolean isBool1 = false;
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

        System.out.println(MapUtils.isEmpty(map)); // true

        map.put("camelCase", "camelCase");
        map.put("PascalCase", "PascalCase");
        map.put("kebab-case", "kebab-case");
        map.put("snake_case", "snake_case");

        System.out.println(MapUtils.isNotEmpty(map)); // true

        Set<String> keys = map.keySet();
        ArrayList<String> values = new ArrayList<>(map.values());

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry);
        }

        // так итерируюсь по мапе
        String value = map
                .entrySet()
                .stream()
                .filter(i -> Objects.equals(i.getKey(), "camelCase"))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);

        System.out.println(value); // camelCase

        //  так могу создать простую мапу
        Map.of(
                "Key", "Value",
                "Key1", "Value1"
        );
    }
}

/*
    многострочный комментарий
    Объявление нового класса,
    который содержит код программы
*/
class Comments {   // начало объявления класса Program

    // определение метода main
    public static void main(String[] args) {    // объявление нового метода

        System.out.println("Hello Java!");     // вывод строки на консоль
    }   // конец объявления нового метода
} // конец объявления класса Program

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
        List<Member> members = Arrays.asList(new Member("Max", 1500L), new Member("Aliya", 900L), new Member("Lili", 400L), new Member("Alice", 50L));

        members.forEach(val -> displayHighScorePosition(val.name, val.score));

        // или так
        displayHighScorePosition("Max", 1500L);
        displayHighScorePosition("Aliya", 900L);
        displayHighScorePosition("Lili", 400L);
        displayHighScorePosition("Alice", 50L);

        checkNumber(1);
        checkNumber(-1);
        checkNumber(0);

        rest("Rest args: ", 1, 2, 3);

        System.out.println(sum(1, 2));
        System.out.println(sum(1, 2, 3, 4));
    }

    public static void rest(String msg, int... args) {
        System.out.println(msg + " " + Arrays.toString(args));
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

    public static void checkNumber(int num) {
        if (num > 0) System.out.println("positive");
        if (num < 0) System.out.println("negative");
        if (num == 0) System.out.println("zero");
    }

    // method overloading
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int sum(int... args) {
        return Arrays.stream(args).sum();
    }
}

class While {
    // отличие while от do while наконец-то раскрыто))
    public static void main(String[] args) {
        int num = 0;

        while (num < 1) {
            System.out.println(num);
            num++;
        }

        // если вдруг захочу чтобы разок цикл тикнул
        do {
            System.out.println(num);
            num++;
        } while (num < 1);
    }
}

class ScannerClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your year of birth:");

        boolean isYearValid = scanner.hasNextInt();

        if (isYearValid) {
            int yearOfBirth = scanner.nextInt();
            int age = 2022 - yearOfBirth;
            scanner.nextLine();

            if (age < 0 || age > 100) {
                System.out.println("Invalid age, should be more than 0 and less than 100");
            } else {
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();
                System.out.println("Your name is " + name + ", age is: " + age);
            }
        } else {
            System.out.println("Invalid age number");
        }

        scanner.close();
    }
}

class TryCatchExceptions {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter number:");

            int x = scanner.nextInt();

            if (x > 30) throw new Exception("number should be  < 30");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//        tryCatchFinally();
    }

    public static void tryCatchFinally() {
        int[] nums = new int[3];

        try {
            // раскомментируй чтобы увидеть соответсвующую ошибко
//            nums[4] = 45;
//            nums[4] = Integer.parseInt("sdf");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Out of bounds " + Arrays.toString(nums));
        } catch (NumberFormatException ex) {
            System.out.println("Convert type error " + "Integer.parseInt(\"sdf\");");
        } finally {
            System.out.println("finally array: " + Arrays.toString(nums));
        }
    }
}

class TryCatchFinallyTest {
    public static int stringSize(Object s) {
        int len = 0;

        try {
            len = s.toString().length();
            return -22;
        } catch (Exception ex) {
            len = -1;
            return -11;
            // если есть finally то он вызовется всегда) даже если return поставлю в true, catch
            // если убрать finally то будет человеческий return
        } finally {
            return len;
        }
    }

    public static void main(String[] args) {
        System.out.println(stringSize("string"));
        System.out.println(stringSize(null));
    }
}

class Switch {
    @AllArgsConstructor
    private static enum SwitchEnum {
        ALL("all"),
        MANDATORY("mandatory"),
        NONE("none");

        @Getter
        @JsonValue
        private final String value;

        @Override
        public String toString() {
            return value;
        }
    }

    public static void main(String[] args) {
        int switchValue = 3;
        char switchCharValue = 'A';
        String month = "January".toLowerCase();

        switch (switchValue) {
            case 1: {
                System.out.println("Value 1");
                break;
            }

            case 3:
            case 4:
            case 5: {
                System.out.println("Value 3,4,5");
            }

            default: {
                System.out.println("default");
            }
        }

        switch (switchCharValue) {
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E': {
                System.out.println("A, B, C, D, E");
                break;
            }

            default: {
                System.out.println("Was not 1,2,3,4");
                break;
            }
        }

        switch (month) {
            case "january": {
                System.out.println("January");
                break;
            }

            case "february": {
                System.out.println("February");
                break;
            }

            default: {
            }
        }

        System.out.println(getStr(SwitchEnum.ALL));
    }

    private static String getStr(SwitchEnum type) {
        return switch (type) {
            case ALL -> EnumEtalon.ALL.getValue();
            case MANDATORY -> EnumEtalon.MANDATORY.getValue();
            case NONE -> EnumEtalon.NONE.getValue();
        };
    }
}

@Slf4j
class Await {
    public static void main(String[] args) {
        boolean processing = true;
        int counter = 0;

        while (processing) {
            try {
                System.out.println("counter " + counter);
                System.out.println(new Date());

                if (counter == 5) {
                    processing = false;
                }

                Thread.sleep(1000);
                counter++;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }
    }
}

class DateTest {
    public static void main(String[] args) {
        // Gets the number of seconds from the Java epoch of 1970-01-01T00:00:00Z.
        System.out.println(Instant.now().getEpochSecond());
    }
}

class ImportTest {
    public static void main(String[] args) {
        // забираю из удобного класса для хранения констант
        System.out.println(CONSTANT_FROM_LOCALIZATION);
        // забираю из удобного интерфейса для хранения констант
        System.out.println(CONSTANT_FROM_LOCALIZATION1);
    }
}

class UUIDTest {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}

@Slf4j
class BiConsumerExample {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class BiConsumerExampleObj {
        private String id;

        static void doSmth(BiConsumerExampleObj arg1, String b) {}
    }

    public static ObjectMapper objectMapper = new ObjectMapper();

    // тут map по сути это мапа из стринг - метод (BiConsumer как раз и есть этот метод) и вместо того чтобы писать if ()... в BiConsumer записываем ф-цию на каждый проп
    // и потом вызываем эту функцию в контексте объекта BiConsumerExampleObj
    public static void main(String[] args) {
        Map<String, BiConsumer<BiConsumerExampleObj, String>> map = new HashMap<>();

//        map.put("someProp", (i, j) -> i.setId(j));
        map.put("someProp", BiConsumerExampleObj::setId);
//        map.put("someProp", BiConsumerExampleObj::doSmth);

        try {
            System.out.println(objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException error", e);
        }

        BiConsumerExampleObj biConsumerExampleObj = new BiConsumerExampleObj();
        biConsumerExampleObj.setId("123");

        map.get("someProp").accept(biConsumerExampleObj, "prop");

        try {
            System.out.println(objectMapper.writeValueAsString(map));
            System.out.println(objectMapper.writeValueAsString(biConsumerExampleObj));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException error", e);
        }
    }
}

@Slf4j
class Logs {
    public static void main(String[] args) {
        String name = "Max";
        int age = 36;

        System.out.printf("Name: %s  Age: %d \n", name, age);
        System.out.println("Name Max" + "\n" + "Age 36");
        // отработает только в кваркусе
//        log.error("Error loading file content for {}", descriptor, e);
        log.warn("Error loading file content for {}", "my warning");
        log.info("Processing {}", 10);
    }
}