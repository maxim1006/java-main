package com.example.java.basics;

class BaseTest {
    protected int num = 1;
}

class Test1 extends BaseTest {
    String prop = "a";
}

class Test2 extends BaseTest {
    String prop1 = "b";
}

public class ExtendsTest {
    public static void main(String[] args) {
        BaseTest testBase = new Test1();
        Test1 test1 = new Test1();
        Test2 test2 = new Test2();

        func(testBase);
        func(test1);
        func(test2);
    }

    public static void func(BaseTest t) {
        System.out.println(t.num);
    }

    public static void func(Test1 t) {
        System.out.println(t.prop);
    }

    public static void func(Test2 t) {
        System.out.println(t.prop1);
    }
}