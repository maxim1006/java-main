package com.example.java.basics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
        // могу из обычного метода достучаться до static, а наоборот не могу
        Class1 c1 = new Class1();
    }

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {
        // пример с instanceof
        Class1 c1 = new Class1();
        Class1 c11 = new Class1(2);

        Class2 c2 = new Class2("Max", 1);
        c2.num = 1;
//        System.out.println(c2.num + " " + c2.str);
//        System.out.println(new Gson().toJson(c2));

//        System.out.println(c1.getNum()); // 1
//        System.out.println(c1.getStr()); // str
//        System.out.println(c1 instanceof Class1); // true

        // пример Singleton
        Singleton singletonInstance = Singleton.INSTANCE;
        singletonInstance.f();

        // пример final
//        FinalClass finalClass = new FinalClass();
//        finalClass.prop = 2 // ошибко
//        FinalClass.staticProp = 2; // ошибко

//        throw new IOException();
//        throw new InternalServerErrorException("Sim Activation Failed");

        BuilderTest builderTest = new BuilderTest();
        System.out.println(new Gson().toJson(builderTest.buildInner())); // {"prop":"Max","prop1":"Max1"}
    }

    // Builder дает возможность строить классы
    public static class BuilderTest {
        public BuilderInner buildInner() {
            return BuilderInner.builder().prop("Max").prop1("Max1").build();
        }

        @Data
        @Builder
        private static class BuilderInner {
            private String prop;
            private String prop1;
            private String prop2;
            private String prop3;
        }
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class BuilderTestModel {
    String str = "Max";
    String str1 = "Max1";
    String str2;
}

class BuilderTest {
    public static void main(String[] args) {
        // {"str":"Hi mom"}
        System.out.println(new Gson().toJson(BuilderTestModel.builder().str("Hi mom").build()));
        // {"str":"Max","str1":"Max1"}
        System.out.println(new Gson().toJson(new BuilderTestModel()));
        // {"str":"1","str1":"2","str2":"3"}
        System.out.println(new Gson().toJson(new BuilderTestModel("1", "2", "3")));
    }
}

class ConstructorExample {
    // пример использования конструктора (называется как и класс)
    interface Group {
        public void addStudent(ConstructorExample student);
    }

    private String name;
    private String prop;

    public ConstructorExample(String name, String prop) {
        this.name = name;
    }

    public ConstructorExample() {
        // классный пример, могу в таком конструкторе вызывать любые overloaded конструкторы с соответствующими пропертями
        this("No Name", "Default prop"); // overload
    }

    public ConstructorExample(String prop) {
        this("No Name", prop); // overload
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

class Singleton2 {
    static Singleton2 instance;

    static double num;

    public Singleton2() {
        if (Singleton2.instance != null) return;

        Singleton2.instance = this;
        num = Math.random();
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(new Singleton2());
        System.out.println(Singleton2.instance);
        System.out.println(Singleton2.instance);
        System.out.println(Singleton2.instance.equals(Singleton2.instance)); // true
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

@Slf4j
class OOP {
    public static void main(String[] args) {
        try {
            Encapsulation e = new Encapsulation(-1);
            ExtendedPerson extendedPerson = new ExtendedPerson("Max");
            String extendedPersonJson = new ObjectMapper().writeValueAsString(extendedPerson);
            String extendedPersonJson1 = new Gson().toJson(extendedPerson);

            System.out.printf("%d", e.age);
            System.out.println(extendedPersonJson); // а тут ничего))
            System.out.println(new Gson().toJson(extendedPerson)); // {"name":"Max","age":30}
            System.out.println(extendedPerson.age + " " + extendedPerson.getAge()); // 30 34

            Gson gson = new Gson();
            ExtendedPerson extendedPersonFromJson = gson.fromJson(extendedPersonJson, new TypeToken<ExtendedPerson>() {
            }.getType());

            ExtendedPerson extendedPersonFromJson1 = gson.fromJson(extendedPersonJson1, new TypeToken<ExtendedPerson>() {
            }.getType());

            System.out.println(extendedPersonFromJson);
            System.out.println(extendedPersonFromJson1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
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

// нельзя создавать инстансы абстрактных классов. Разница между абстрактным и просто классом еще в том что в абстракный класс может реализовывать только часть интерфейса, а уже его наследник обязан дореализовывать остальное. Также существуют абстрактные методы. Такие методы определяются с помощью ключевого слова abstract и не имеют никакой реализации. Производный класс обязан переопределить и реализовать все абстрактные методы, которые имеются в базовом абстрактном классе. Также следует учитывать, что если класс имеет хотя бы один абстрактный метод, то данный класс должен быть определен как абстрактный.
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

    // в абстрактном классе могу реализовать только часть методов, остальное с наследника
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

class ObjectMethods {
    public static void main(String[] args) {
        ToString toString = new ToString();
        ToString1 toString1 = new ToString1("Max");
        ToString1 toString2 = new ToString1("Aliya");
        ToString1 toString3 = new ToString1("Max");

        System.out.println(toString.toString() + " " + toString1.toString()); // com.example.java.basics.ObjectMethods$ToString@210366b4 Test Max

        // могу использовать hashCode чтобы сравнить объекты
        System.out.println(toString.hashCode() + " " + toString1.hashCode()); // 250370634 1325808650
        System.out.println(toString.getClass() + " " + toString1.getClass()); // class com.example.java.basics.ObjectMethods$ToString class com.example.java.basics.ObjectMethods$ToString1

        System.out.println(toString2.equals(toString1)); // false
        System.out.println(toString2.equals(toString)); // false
        // без переопределения метода тоже был бы false, оставил в качестве примера сравнения
        System.out.println(toString3.equals(toString1)); // true
    }

    public static class ToString {
    }

    public static class ToString1 {
        private String name;
        private Object id;

        public ToString1(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Test " + name;
        }

        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof ToString1)) return false;

            ToString1 p = (ToString1) obj;
            return this.name.equals(p.name);
        }
    }
}

class Generics {
    public static void main(String[] args) {
        Generics.simpleGeneric();
    }

    static void simpleGeneric() {
        Generic<String> generic = new Generic("Max");
        Generic<Integer> generic1 = new Generic(123);

        System.out.println(generic.getId());
        System.out.println(generic1.getId());

        String[] strArr = {"Max", "Aliya"};
        generic.<String>print(strArr);

        Integer[] intArr = {123, 456};
        generic.<Integer>print(intArr);
    }

    // если хочу передавать только определенных наследников
    static void narrowingGeneric() {
        NarrowGenericSource narrowGenericSource = new NarrowGenericSource(123, "Max");
        NarrowGeneric<NarrowGenericSource> narrowGeneric = new NarrowGeneric<NarrowGenericSource>(narrowGenericSource);
    }

    interface GenericModel<T> {
        T getId();
    }

    public static class Generic<T> implements GenericModel<T> {
        private T name;

        Generic(T name) {
            this.name = name;
        }

        public T getId() {
            return name;
        }

        public <T> void print(T[] items) {
            for (T item : items) {
                System.out.println(item);
            }
        }
    }

    // непонятно зачем но можно делать так
    public static class GenericConstructor {
        private String tProp;

        <T> GenericConstructor(T tProp) {
            this.tProp = tProp.toString();
        }
    }

    public static class NarrowGeneric<T extends NarrowGenericSource> {
        private int id;
        private String str;

        NarrowGeneric(T source) {
            this.id = source.getId();
            this.str = source.getStr();
        }
    }

    private static class NarrowGenericSource {
        private int id;
        private String str;

        NarrowGenericSource(int id, String str) {
            this.id = id;
            this.str = str;
        }

        public int getId() {
            return id;
        }

        public String getStr() {
            return str;
        }
    }
}

class Clones {
    public static void main(String[] args) {
        // это подойдет если внутри класса Cloned не будет вложенных объектов, если будут, то надо во всех классах по цепочке реализовывать clone
        try {
            Cloned cloned = new Cloned("Max");
            Cloned cloned1 = cloned.clone();
            cloned1.setName("Aliya");
            cloned.display(); // MAX
        } catch (CloneNotSupportedException ex) {
            System.out.println("Clonable not implemented");
        }

        String str = "";

        System.out.println(str.isEmpty());
    }

    public static class Cloned implements Cloneable {
        private String name;

        Cloned(String name) {
            this.name = name;
        }

        void setName(String name) {
            this.name = name;
        }

        void display() {
            System.out.printf("Person %s \n", name);
        }

        public Cloned clone() throws CloneNotSupportedException {
            return (Cloned) super.clone();
        }
    }
}

class Point {
    public Point() {
    }

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}