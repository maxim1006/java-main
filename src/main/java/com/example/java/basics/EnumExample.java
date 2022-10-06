package com.example.java.basics;

import com.example.java.enums.EnumEtalon;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

enum MyEnum {
    CONST, CONST1, CONST2;
}

enum MyEnum1 {
    Prop("123", null),
    Prop1("454", null),
    Prop2("45654", null),
    Prop3("Name", "Value");

    MyEnum1(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private final String name;
    private final String value;

    public String getName() {return name;}
    public String getValue() {return value;}
}

public class EnumExample {
    public static void main(String[] args) {
        System.out.println(MyEnum.CONST.toString()); // CONST
        System.out.println(MyEnum.CONST); // CONST
        System.out.println(MyEnum1.Prop3.getName()); // "Name"
        System.out.println(MyEnum1.Prop3.getValue()); // "Value"
        System.out.println(MyEnum1.Prop.getName()); // 123
        System.out.println(EnumEtalon.ALL); // all
        // так привожу к строке (если например понадобиться в switch)
        System.out.println(EnumEtalon.valueOf("ALL")); // all
        System.out.println(EnumEtalon.valueOf("ALL") == EnumEtalon.ALL); // true
    }
}

class EnumExample1 {
    public static void main(String[] args) {
        System.out.println(CheckoutStepTask.UPDATE_DETAILS.getValue()); // updateDetails
        System.out.println(CheckoutStepTask.UPDATE_DETAILS.toString()); // updateDetails
        System.out.println(CheckoutStepTask.UPDATE_DETAILS.getCheckoutStep()); // details

        CheckoutStepTask test = CheckoutStepTask.parse("addToCart");
        CheckoutStepName test1 = CheckoutStepTask.getByCheckoutStep("updateDetails");
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
        ObjectMapper om = new ObjectMapper();

        try {
            System.out.println(om.writeValueAsString(MyEnum3.ANOTHER_VALUE));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(MyEnum3.ANOTHER_VALUE);
    }
}

enum MyEnum3 {
    @JsonProperty("theFirstValue") THE_FIRST_VALUE,
    @JsonProperty("another_value") ANOTHER_VALUE;
}

