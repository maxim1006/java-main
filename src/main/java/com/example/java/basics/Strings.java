package com.example.java.basics;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/* String is not primitive it's a Class.
 * Strings are immutable - u cant change them, only create a new String */
class Strings {
    public static void main(String[] args) {
        String str = "Max";
        System.out.println(str);
        System.out.println(str + " " + str);
        System.out.println((str + " ").repeat(3).trim());

        String strB = "strB";
        String strB1 = "strB1";

        StringBuilder builder = new StringBuilder(strB);
//        System.out.println(builder.capacity()); // 20
        builder.append(strB1);

//        тоже что и StringBuilder только многопоточный
//        StringBuffer buffer = new StringBuffer(strB);
//        System.out.println(buffer.capacity()); // 20

        System.out.println(builder); // strBstrB1

        String numAsString = "2022";
        System.out.println(Integer.parseInt(numAsString));

        System.out.println(str.substring(0, 2)); // Ma

        String numAsStringExtra = "2022.123";
        double numAsStringExtraDouble = Double.parseDouble(numAsStringExtra);
        System.out.println(numAsStringExtraDouble);

        StringBuilder strBuilderReplace = new StringBuilder("");
        strBuilderReplace.append("a\n");
        strBuilderReplace.append("b\n");
        strBuilderReplace.append("c");
        System.out.println(strBuilderReplace.toString()); // a на след строке b на след строке с
        System.out.println(strBuilderReplace.toString().replace("\n", "")); // abс
        System.out.println("c".concat("/"));
        String strCompare1 = "Hello";
        String strCompare2 = "Hello";
        System.out.println(Objects.equals(strCompare1, strCompare2)); // true
        System.out.println(StringUtils.equals(strCompare1, strCompare2)); // true
        System.out.println(strCompare1.equals(strCompare2)); // true
        System.out.println(strCompare1 == strCompare2);  // true
    }
}
