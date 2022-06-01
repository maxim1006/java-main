package com.example.java.basics;

import com.example.java.enums.EnumEtalon;

enum MyEnum {
    CONST, CONST1, CONST2;
}

enum MyEnum1 {
    Prop("123"),
    Prop1("454"),
    Prop2("45654");

    MyEnum1(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {return name;}
}

public class EnumExample {
    public static void main(String[] args) {
        System.out.println(MyEnum.CONST); // CONST
        System.out.println(MyEnum1.Prop.getName()); // 123
        System.out.println(EnumEtalon.ALL); // all
    }
}


