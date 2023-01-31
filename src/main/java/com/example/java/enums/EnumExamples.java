package com.example.java.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum MyEnum {
    CONST, CONST1, CONST2;
}

@RequiredArgsConstructor
enum MyEnum1 {
    Prop("123", null),
    Prop1("454", null),
    Prop2("45654", null),
    Prop3("Name", "Value");

    // когда @RequiredArgsConstructor могу не писать конструктор
//    MyEnum1(String name, String value) {
//        this.name = name;
//        this.value = value;
//    }


    @Getter
    private final String value;
    @Getter
    private final String name;

//    private final String name;
//    private final String value;
//    public String getName() {return name;}
//    public String getValue() {return value;}

    // могу перезаписать метод для возврата значения при toString
//    @Override
//    public String toString() {
//        return value;
//    }
}

@RequiredArgsConstructor
enum MyEnum2 {
    Prop("1", "one"),
    Prop1("4", "four");

    @Getter
    private final String code;

    @Getter
    private final String message;
}

public class EnumExamples {
    public static void main(String[] args) {
        System.out.println(MyEnum.CONST.toString()); // CONST
        System.out.println(MyEnum.CONST); // CONST
        System.out.println(MyEnum.CONST.equals("CONST")); // false
        System.out.println(MyEnum.CONST.equals(MyEnum.CONST)); // true
        System.out.println(MyEnum1.Prop3.getName()); // "Name"
        System.out.println(MyEnum1.Prop3.getValue()); // "Value"
        System.out.println(MyEnum1.Prop.getName()); // 123
        System.out.println(MyEnum1.Prop); // Prop
        System.out.println(MyEnum1.Prop.toString()); // Prop

        List<MyEnum1> MyEnum1List = Arrays.stream(MyEnum1.values()).toList();

        StringBuilder myEnum1Names = new StringBuilder("");
        StringBuilder myEnum1Values = new StringBuilder("");

        for (MyEnum1 item : MyEnum1List) {
            myEnum1Names.append(item.getName()).append(" ");
            myEnum1Values.append(item.getValue()).append(" ");
        }

        System.out.println(MyEnum1List); // [Prop, Prop1, Prop2, Prop3]
        System.out.println(myEnum1Names); // 123 454 45654 Name
        System.out.println(myEnum1Values); // null null null Value

        System.out.println(EnumEtalon.ALL); // all
        // так привожу к строке (если например понадобиться в switch)
        System.out.println(EnumEtalon.valueOf("ALL")); // all
        System.out.println(EnumEtalon.valueOf("ALL") == EnumEtalon.ALL); // true

        System.out.println(MyEnum4.map()); // {prop2=value2, prop1=value1, prop=value}
    }
}

class EnumExample1 {
    public static void main(String[] args) {
        System.out.println(CheckoutStepTask.UPDATE_DETAILS.getValue()); // updateDetails
        System.out.println(CheckoutStepTask.UPDATE_DETAILS.toString()); // updateDetails
        System.out.println(CheckoutStepTask.UPDATE_DETAILS.getCheckoutStep()); // details

        CheckoutStepTask test = CheckoutStepTask.parse("addToCart");
        CheckoutStepName test1 = CheckoutStepTask.getByCheckoutStep("updateDetails");
        System.out.println(test.name()); // "addToCart"
        System.out.println(test.getValue()); // "addToCart"
        System.out.println(test); // "addToCart"
        System.out.println(test1); // "details"


        String testStr = "details";
        CheckoutStepName testStrEnum = CheckoutStepName.DETAILS;

        // нехорошо
        if (testStr.equals(CheckoutStepName.DETAILS.toString())) {
            System.out.println(true);
        }
        // лучше
        if (CheckoutStepName.DETAILS.equals(testStrEnum)) {
            System.out.println(false);
        }
    }
}

class EnumExample2 {
    public static void main(String[] args) {
        System.out.println(MyEnum2.Prop.getCode() + ' ' + MyEnum2.Prop.getMessage()); // 1 one
    }
}

@RequiredArgsConstructor
enum CheckoutStepName {
    DETAILS("details"),
    PERSONAL_INFO("personalInfo"),
    DELIVERY("delivery"),
    PAYMENT("payment");

    @Getter
    @JsonValue
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}

@RequiredArgsConstructor
enum CheckoutStepTask {

    // тут просто офигеть, еще важна последовательность геттеров ниже и типо сперва пойдет String, потом CheckoutStepName, а ADD_TO_CART - это типо конструктор
    ADD_TO_CART("addToCart", null),
    UPDATE_DETAILS("updateDetails", CheckoutStepName.DETAILS),
    UPDATE_PERSONAL_INFO("updatePersonalInfo", CheckoutStepName.PERSONAL_INFO),
    UPDATE_DELIVERY("updateDelivery", CheckoutStepName.DELIVERY),
    START_PAYMENT("startPayment", CheckoutStepName.PAYMENT);

    @Getter
    @JsonValue
    private final String value;

    @Getter
    private final CheckoutStepName checkoutStep;

    public static CheckoutStepTask parse(String value) {
        // тут удобно что могу в orElse вернуть даже строку изза findAny
        String str = Arrays.stream(CheckoutStepTask.values())
                .map(CheckoutStepTask::getValue)
                .filter(typeValue -> typeValue.equals(value))
                .findAny()
                .orElse("");

        // а в таком случае изза findFirst в orElse смогу вставить либо null либо CheckoutStepTask._значение_
        CheckoutStepTask task = Arrays.stream(CheckoutStepTask.values()).filter(typeValue -> typeValue.equals(value)).findFirst().orElse(null);

        // тоже но с Stream.of
        CheckoutStepTask task1 = Stream.of(CheckoutStepTask.values()).filter(typeValue -> typeValue.equals(value)).findFirst().orElse(null);

        return Arrays.stream(CheckoutStepTask.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public static CheckoutStepName getByCheckoutStep(String value) {
        return Arrays.stream(CheckoutStepTask.values())
                .filter(i -> i.getValue().equals(value))
                .findFirst()
                .map(CheckoutStepTask::getCheckoutStep)
                .orElse(null);
    }

    @Override
    public String toString() {
        return value;
    }
}

class EnumExample3 {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // прикольно тут именно значение приплывет
            System.out.println(objectMapper.writeValueAsString(MyEnum3.ANOTHER_VALUE)); // "another_value"
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(MyEnum3.ANOTHER_VALUE); //ANOTHER_VALUE
    }
}

enum MyEnum3 {
    @JsonProperty("theFirstValue") THE_FIRST_VALUE,
    @JsonProperty("another_value") ANOTHER_VALUE;
}

@AllArgsConstructor
enum MyEnum4 {
    Prop("prop", "value"),
    Prop1("prop1", "value1"),
    Prop2("prop2", "value2");

    @Getter
    private final String prop;

    @Getter
    private final String value;

    public static Stream<MyEnum4> stream() {
        return Stream.of(MyEnum4.values());
    }

    public static Map<String, String> map() {
        return stream()
                .collect(Collectors.toMap(MyEnum4::getProp, MyEnum4::getValue));
    }
}

