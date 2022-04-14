package com.example.java.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

// ставлю private чтобы доступ был только через сеттеры
@Data
@AllArgsConstructor
@NoArgsConstructor
class ExampleModel {
    private String str;
    private int num;
    private long lifeTimeSeconds;
    private boolean enabled;
}

@Data
class ExampleModel1 {
    private String str;
    private int num;
    private long lifeTimeSeconds;
    private boolean enabled;
}

@Data
class LombokExampleImpl {
    private Map<String, ExampleModel> mapExample;
    private Map<String, ExampleModel1> mapExample1;
}


public class LombokExample {
    public static void main(String[] args) {
        LombokExampleImpl lombokExampleImpl = new LombokExampleImpl();
        Map<String, ExampleModel> mapExample = new HashMap<>();
        Map<String, ExampleModel1> mapExample1 = new HashMap<>();
        ExampleModel exampleModel = new ExampleModel("string", 1000, 10000000000000L, true);
        ExampleModel1 exampleModel1 = new ExampleModel1();

        exampleModel1.setEnabled(true);
        exampleModel1.setLifeTimeSeconds(10000000000000L);
        exampleModel1.setNum(1000);
        exampleModel1.setStr("string");

        mapExample.put("prop", exampleModel);
        mapExample1.put("prop1", exampleModel1);

        lombokExampleImpl.setMapExample(mapExample);
        lombokExampleImpl.setMapExample1(mapExample1);

        System.out.println(lombokExampleImpl.getMapExample());
        System.out.println(lombokExampleImpl.getMapExample1());
    }
}

