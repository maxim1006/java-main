package com.example.java.basics;

import lombok.Data;

import java.io.IOException;

public class Classes {
    private final int num = 1;
    private final String str = "str";

    @Data
    public static class Class1 {
        String num;
        String str;
    }

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {
        // пример с instanceof
        Class1 c1 = new Class1();

        System.out.println(c1.getNum()); // 1
        System.out.println(c1.getStr()); // str
        System.out.println(c1 instanceof Class1); // true

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

// пример использования конструктора (называется как и класс)
interface Group {
    public void addStudent(ConstructorExample student);
}

class ConstructorExample {
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

// WTF??? да как так-то ???!!!
interface TestInterface {
    public int prop = 1;
}

abstract class AbstractClass implements TestInterface {
    public String prop = "";
}

class AbstractTestClass extends AbstractClass {
    public Boolean prop = true;
}
//////////////////////////////

// нельзя создавать инстансы абстрактных классов. Разница между абстрактным и просто классом еще в том что в абстракный класс может реализовывать только часть интерфейса, а уже его наследник обязан дореализовывать остальное
interface TestInterface1 {
    String getProp();

    String getProp1();
}

abstract class AbstractClass1 implements TestInterface1 {
    public String getProp() {
        return "Max";
    }
}

class AbstractTestClass1 extends AbstractClass1 {
    public String getProp1() {
        return "Max1";
    }
}

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

class FinallyTest {
    public static int stringSize(Object s) {
        int len = 0;

        try {
            len = s.toString().length();
            return -22;
        } catch (Exception ex) {
            len = -1;
            return -11;
            // если есть finally то он вызовется всегда) даже если return поставлю в tre, catch
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

            default: {}
        }
    }
}

