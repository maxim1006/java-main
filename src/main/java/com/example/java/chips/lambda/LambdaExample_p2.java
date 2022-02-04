package com.example.java.chips.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;

public class LambdaExample_p2 {

    private static int customCompare(String s1, String s2) {
        return 0;
    }

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        //pre java8 code
        strings.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return CharSequence.compare(o1, o2);
            }
        });
        //java8
        strings.sort((o1, o2) -> customCompare(o1, o2));

        //java7
        strings.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        //java8
        strings.sort((Object o1, Object o2) -> { return 0; } );

        //Method references
        strings.sort(LambdaExample_p2::customCompare);
        strings.sort(String::compareTo);

        strings.forEach(System.out::println);
        strings.forEach((s) -> System.out.println(s));

        strings.stream().filter("abc"::equals);

        // () -> { }
        // () -> 42
        // () -> null
        // () -> { return 42; }
        // () -> { if (true) return 42; else { System.gc(); return 13; } }
        // (int x) -> x + 1
        // (x) -> x + 1
        // x -> x + 1
        // (int x, final int y) -> x + y
        IntUnaryOperator intFunction = x -> x + 1;
        IntBinaryOperator intBinaryOperator = (int x, final int y) -> x + y;

        //also valid:
        // (int... x) -> ..
        // (int[] x) -> ..
        // Arrays::<String>sort
        // "abc"::length
        // List<String>::size
        // int[]::clone

        boolean test = true;
//        Supplier<Iterator<String>> supplier = ((List<String>)(test ? strings.replaceAll(String::trim) : strings)) :: iterator;

        // (test ? list.replaceAll(String::trim) : list) :: iterator
        // super::toString
        // ArrayList<String>::new
        // Foo::<String>new
        // Bar<String>::<Integer>new
        // Outer.Inner::new
        // int[]::new

//        ! toString
//        System.out.println(intFunction.getClass().getName());
    }


    /**
     * arr.forEach((val, index, array) => { ... });
     */
    static <T> void customForEach(Collection<T> items, ObjIntConsumer<T> consumer) {
        int i = 0;
        for (T item : items) {
            consumer.accept(item, i++);
        }
    }

    static void logEntry(String value, int index) {
        System.out.println(index + ": " + value);
    }

    /*
     * customForEach(array, (item, index) -> {...})
     */
    public static void main2(String[] args) {
        List<String> strings = List.of("1", "2", "3");
        customForEach(strings, LambdaExample_p2::logEntry);
    }
}
