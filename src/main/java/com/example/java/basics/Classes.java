package com.example.java.basics;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;

public class Classes {
    private final int num = 1;
    private final String str = "str";

    @Data
    public static class Class1 {
        int num = 1;
        String str = "Aliya";

        // можно и так но зачем?)
        /*начало блока инициализатора*/
//        {
//            num = 1;
//            str = "Aliya";
//        }
        /*конец блока инициализатора*/

        // конструктор
        Class1() {
            // ссылка на свойство
            System.out.println("Class1 constructor " + num);

            // не могу достучаться до метода из статик метода, а вот наоборот могу
//            privateMethod();
        }

        Class1(int num) {
            // пример аргумента + поле
            System.out.println("Class1 arg constructor " + num + this.num + " " + str);
        }
    }

    @AllArgsConstructor
    public static class Class2 {
        private String str;
        private int num;
    }

    private void privateMethod() {
        // могу из обычного метода достучаться до static
        Class1 c1 = new Class1();
    }

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {
        // пример с instanceof
        Class1 c1 = new Class1();
        Class1 c11 = new Class1(2);

        Class2 c2 = new Class2("Max", 1);
        c2.num = 1;
        System.out.println(c2.num + " " + c2.str);
        System.out.println(new Gson().toJson(c2));

//        System.out.println(c1.getNum()); // 1
//        System.out.println(c1.getStr()); // str
//        System.out.println(c1 instanceof Class1); // true

        // пример Singleton
        Singleton singletonInstance = Singleton.INSTANCE;
        singletonInstance.f();

        // пример final
        FinalClass finalClass = new FinalClass();
//        finalClass.prop = 2 // ошибко
//        FinalClass.staticProp = 2; // ошибко

//        throw new IOException();
    }
}

class ConstructorExample {
    // пример использования конструктора (называется как и класс)
    interface Group {
        public void addStudent(ConstructorExample student);
    }

    String name;

    public ConstructorExample(String name) {
        this.name = name;
    }

    public ConstructorExample() {
        this("No Name"); // overload
    }

    public void addToGroup(Group group) {
        group.addStudent(this);
    }
}

class Singleton {
    public static final Singleton INSTANCE = new Singleton();

    public int f() {
        return 1;
    }

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}

// в final-классе, все методы неявно являются final
// private методы неявно являются final
final class FinalClass {
    public final void method() {
    }

    public static int staticMethod() {
        return 1;
    }

    // c final обязательна инициализация, либо так
    public final int prop = 1;
    public final int prop2;
    public static final int staticProp = 1;
    public static final int staticProp1;

    // либо так
    static {
        staticProp1 = 2;
    }

    // либо так
    FinalClass() {
        prop2 = 2;
    }
}

/*
- Внутренний класс ведет себя как обычный класс за тем исключением, что его объекты могут быть созданы только внутри внешнего класса.
- Внутренний класс имеет доступ ко всем полям внешнего класса, в том числе закрытым с помощью модификатора private. Аналогично внешний класс имеет доступ ко всем членам внутреннего класса, в том числе к полям и методам с модификатором private.
- Ссылку на объект внешнего класса из внутреннего класса можно получить с помощью выражения Внешний_класс.this, например, Person.this.
- Объекты внутренних классов могут быть созданы только в том классе, в котором внутренние классы опеределены. В других внешних классах объекты внутреннего класса создать нельзя.
*/
class OuterNestedClass {
    private int privateP = 1;

    // бывает вложенный класс, он имеет неявную ссылку на родителя
    // во вложенном классе this относится к объекту вложенного класса, но если нужен внешний ИмяВнешнегоКласса.this
    // Если вложенный объект создается снаружи внешнего класса, то используется синтаксис: внешнийОбъект.new ВнутреннийКласс()
    private class InnerNestedClass {
        static final int prop = 1;

        InnerNestedClass() {
            System.out.println(privateP);
            System.out.println(this.prop);
            System.out.println(OuterNestedClass.this.privateP);
        }
    }

    // так как доступа из вложенного статического класса к родителю нет, должен прокидывать родительский в конструкторе
    private static class InnerStaticNestedClass {
        static final int prop = 1;

        // так как доступа из вложенного класса к родителю нет, должен прокидывать родительский в конструкторе
        private OuterNestedClass outerNestedClass;

        private final int privateNestedP = 1;

        InnerStaticNestedClass(OuterNestedClass outerNestedClass) {
            System.out.println(privateNestedP);
            this.outerNestedClass = outerNestedClass;
        }
    }

    OuterNestedClass() {
        int p = InnerNestedClass.prop;
        int pStatic = InnerStaticNestedClass.prop;

        InnerNestedClass innerNestedClass = new InnerNestedClass();
        InnerStaticNestedClass innerStaticNestedClass = new InnerStaticNestedClass(this);
    }
}

class OOP {
    public static void main(String[] args) {
        Encapsulation e = new Encapsulation(-1);
        ExtendedPerson extendedPerson = new ExtendedPerson("Max");

        System.out.printf("%d", e.age);
        System.out.println(new Gson().toJson(extendedPerson)); // {"name":"Max","age":30}
        System.out.println(extendedPerson.age + " " + extendedPerson.getAge()); // 30 34
    }

    private static class Encapsulation {
        private int age = 1;

        Encapsulation(int age) {
            if (age > 0) this.age = age;
        }
    }

    private static class Person {
        String name;
        int age = 30;

        public Person(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }
    }

    // При вызове переопределенного метода виртуальная машина динамически находит и вызывает именно ту версию метода, которая определена в подклассе. Данный процесс еще называется dynamic method lookup или динамический поиск метода или динамическая диспетчеризация методов. Наследовать можно только от 1 класса
    private static class ExtendedPerson extends Person {
        public ExtendedPerson(String name) {
            super(name); // если базовый класс определяет конструктор, то производный класс должен его вызвать
        }

        // указываю переопределение метода, в целом не обязательно но наглядно
        @Override
        public int getAge() {
            return 34;
        }
    }

    // запрет наследования
    private static final class FinalClass {
    }
//    private final static class FinalClassExtended extends FinalClass {} // ошибка - нельзя наследовать final class

    private static class ClassWithFinalMethod {
        public final void print() {
            System.out.println("ClassWithFinalMethod");
        }
    }

    private static class ClassWithFinalMethodExtended extends ClassWithFinalMethod {
        // ошибка - нельзя переписывать final метод
//        @Override
//        public void print() {
//            System.out.println("ClassWithFinalMethod");
//        }
    }

    public enum PageType {
        UNKNOWN("unknown");

        final String value;

        PageType(String value) {
            this.value = value;
        }
    }
}

// нельзя создавать инстансы абстрактных классов. Разница между абстрактным и просто классом еще в том что в абстракный класс может реализовывать только часть интерфейса, а уже его наследник обязан дореализовывать остальное. Также сущенствуют абстрактные методы. Такие методы определяются с помощью ключевого слова abstract и не имеют никакой реализации. Производный класс обязан переопределить и реализовать все абстрактные методы, которые имеются в базовом абстрактном классе. Также следует учитывать, что если класс имеет хотя бы один абстрактный метод, то данный класс должен быть определен как абстрактный.
class AbstractExamples {
    public static void main(String[] args) {
        // тут спецом поставил Object а не HomePageRule чтобы показать instanceof
        Object pageRule = new HomePageRule();
        Object pageRule1 = new ProductPageRule();

        // downcasting - PageRule не всегда является HomePageRule иногда и ProductPageRule
        HomePageRule pageRuleCasted = (HomePageRule) pageRule;

        // upcasting - HomePageRule и ProductPageRule всегда инстансы PageRule
        System.out.println(pageRule instanceof PageRule); // true
        System.out.println(pageRule instanceof HomePageRule); // true
        System.out.println(pageRule instanceof ProductPageRule); // false
        System.out.println(pageRule1 instanceof PageRule); // true
        System.out.println(pageRule1 instanceof ProductPageRule); // true
        System.out.println(pageRule1 instanceof HomePageRule); // false
    }

    interface AbstractInterface {
        public String name = "Aliya";
        public int age = 34;
    }

    public abstract static class PageRule implements AbstractInterface {
        public abstract void display();
    }

    public static class HomePageRule extends PageRule {
        // на отсутствие свойств типо не ругается
        String name = "home";

        // а вот если нет методов ругань
        public void display() {
            System.out.println("HomePageRule display");
        }

        ;
    }

    public static class ProductPageRule extends PageRule {
        String name = "product";

        public void display() {
            System.out.println("ProductPageRule display");
        }

        ;
    }
}

// надо учитывать, что если класс применяет интерфейс, то он должен реализовать все методы интерфейса. Если класс не реализует какие-то методы интерфейса, то такой класс должен быть определен как абстрактный, а его неабстрактные классы-наследники затем должны будут реализовать эти методы.
class InterfaceClass {
    public static void main(String[] args) {
        // удобство в том что могу создать разные объекты с 1 интерфейсом
        TestModel t = new TestClass();
        TestModel t1 = new TestClass1Ext();

        // чтобы вызвать доп метод которого нет в интерфейсе также как и в тс нужно явно указать что за класс
        ((TestClass) t).method(); // method
        // или
        if (t instanceof TestClass) ((TestClass) t).method(); // method

        t.defaultMethod();

        // ))))))
        TestModel.staticMethod();

        // могу пробрасывать interface в метод
        TestModel1 t11 = new TestClass();
        InterfaceClass.methodWithInterfacedArg(t11); // {}
    }

    // По умолчанию все методы в интерфейсе фактически имеют модификатор public.
    interface TestModel {
        void print();

        void print1();

        // это просто жесть, мало того что могу инитить свойства в интерфейса
        int prop = 1;

        // так еще и методы по умаолчаню, которые не обязательно реализовывать в классах, ахахахаха
        default void defaultMethod() {
            System.out.println("default method from interface)))");
            privateMethod();
        }

        // также есть статические методы интерфейса
        static void staticMethod() {
            System.out.println("static method from interface)))");
        }

        private void privateMethod() {
            System.out.println("privateMethod from interface)))");
        }
    }

    // могут наследоваться
    interface TestModel1 extends TestModel {
        int PROP = 1;
    }

    // могу делать аргументы с интерфейсом
    public static void methodWithInterfacedArg(TestModel1 t) {
        System.out.println(new Gson().toJson(t));
    }

    // можно выполнять несколько интерфейсов
    static class TestClass implements TestModel, TestModel1 {
        @Override
        public void print() {
            System.out.println("print");
        }

        @Override
        public void print1() {
            System.out.println("print1");
        }

        public void method() {
            System.out.println("method");
        }
    }

    // в абстрактном классе могу реализовать только часть методов, остальное с наследнека
    abstract static class TestClass1 implements TestModel {
        @Override
        public void print() {
            System.out.println("print");
        }
    }

    static class TestClass1Ext extends TestClass1 {
        @Override
        public void print1() {
            System.out.println("TestClass1Ext");
        }
    }
}

class EnumClass {
    public static void main(String[] args) {
//        System.out.println(m()); // метода нельзя
        System.out.println(Day.FRIDAY); // FRIDAY // а enum можно)

        Day monday = Day.MONDAY;

        // каждый enum имеет статический метод values()
        Day[] days = Day.values();

        StringBuilder daysStr = new StringBuilder();

        for (Day day : days) {
            daysStr.append(day).append(" ");
        }

        System.out.println(daysStr);

        // а вот пример если хочу осмысленные строки
        DayStr[] dayStrs = DayStr.values();

        StringBuilder dayStrsStr = new StringBuilder();

        for (DayStr dayEnum : dayStrs) {
            dayStrsStr.append(dayEnum.day).append(" ");
        }

        System.out.println(dayStrsStr);

        // пример использования метода из enum
        System.out.println(DayStr.capitalize(DayStr.FRIDAY));
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
        public static String capitalize(DayStr day) {
            return day.day.substring(0, 1).toUpperCase() + day.day.substring(1);
        }

        // enum как и обычные классы, могут определять конструкторы, поля и методы.
        final String day;

        DayStr(String day) {
            this.day = day;
        }
    }
}