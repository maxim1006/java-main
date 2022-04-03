package com.example.java.basics;

//Полиморфизм - использование объектов с одинаковыми интерфейсами без информации о типе и внутренней структуре объектов, при этом и то и другое Clock но могу вызвать preciseClock.toString();

public class Poly {
    public static void main(String[] args) {
        Clock preciseClock = new PreciseClock();

        System.out.println(preciseClock.toString());
    }
}

class Clock {
    public static final int hoursStatic = 2;
    protected final int hours = 1;
    protected final int minutes = 1;

    public String toString() {
        return "My clock" + hours + ":" + minutes;
    }
}

class PreciseClock extends Clock {
    private static final int seconds = 1;
    private final int seconds1 = 1;

    // из них нельзя обращаться к нестатическим полям и методам как и в js
    private static void method() {
        System.out.println(seconds);
//        System.out.println(seconds1); // ошибко
    }

    // из статических методов недоступен this - ошибко
    // в js будет доступен this, но через него смогу достать только статические методы и свойства при этом
//    private static void method1() {
//        this.seconds
//    }

    @Override
    public String toString() {
        return "My clock " + hours + ":" + minutes + ":" + seconds;
    }
}