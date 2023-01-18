package com.example.java.enums;

class EnumClass {
    public static void main(String[] args) {
//        System.out.println(m()); // метода нельзя
        System.out.println(EnumClass.Day.FRIDAY); // FRIDAY // а enum можно)

        EnumClass.Day monday = EnumClass.Day.MONDAY;

        // каждый enum имеет статический метод values()
        EnumClass.Day[] days = EnumClass.Day.values();

        StringBuilder daysStr = new StringBuilder();

        for (EnumClass.Day day : days) {
            daysStr.append(day).append(" ");
        }

        System.out.println(daysStr);

        // а вот пример если хочу осмысленные строки
        EnumClass.DayStr[] dayStrs = EnumClass.DayStr.values();

        StringBuilder dayStrsStr = new StringBuilder();

        for (EnumClass.DayStr dayEnum : dayStrs) {
            dayStrsStr.append(dayEnum.day).append(" ");
        }

        System.out.println(dayStrsStr);

        // пример использования метода из enum
        System.out.println(EnumClass.DayStr.capitalize(EnumClass.DayStr.FRIDAY));
    }

    // это для примера что enum можно использовать в static методе, а enum можно
    public void m() {
    }

    enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    enum DayStr {
        MONDAY("monday"),
        TUESDAY("tuesday"),
        WEDNESDAY("wednesday"),
        THURSDAY("thursday"),
        FRIDAY("friday"),
        SATURDAY("saturday"),
        SUNDAY("sunday");

        // можно делать всякие методы
        public static String capitalize(EnumClass.DayStr day) {
            return day.day.substring(0, 1).toUpperCase() + day.day.substring(1);
        }

        // enum как и обычные классы, могут определять конструкторы, поля и методы.
        final String day;

        DayStr(String day) {
            this.day = day;
        }
    }
}
