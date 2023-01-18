package com.example.java.maps;

import com.example.properties.PortalProperties;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.MapUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Data
@AllArgsConstructor
@NoArgsConstructor
class AddressUnit {
    private String street;
    private String city;
    private String postalCode;
}

public class MapExample {
    public static void main(String[] args) {
        new MapExampleInner();
    }
}

@ApplicationScoped
class MapExampleInner {
    @Inject
    PortalProperties portalProperties;

    public static Map<String, BiConsumer<AddressUnit, String>> map = new HashMap<>();

    // The PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization
    @PostConstruct
    public void init() {
        System.out.println(portalProperties);
    }

    MapExampleInner() {
        Map<String, String> testMap = new HashMap<>();
        Map<String, String> testMap1 = new HashMap<>();

        MapExampleInner.map.put("testName", AddressUnit::setCity);
        MapExampleInner.map.put("testName1", AddressUnit::setPostalCode);
        MapExampleInner.map.put("testName2", AddressUnit::setStreet);
        MapExampleInner.map.put("testName3", null);

//        System.out.println(MapUtils.isEmpty(map)); // false
//        System.out.println(MapUtils.isEmpty(testMap)); // true

        testMap1.put("key", "value");
        testMap.putAll(testMap1);

//        System.out.println(new Gson().toJson(map)); // {"testName1":{},"testName2":{},"testName":{}}

        // пример создания простой мапы
//        AbstractMap.SimpleEntry<String, Integer> distinctMap = new AbstractMap.SimpleEntry<>("Max", 35);

        main();
    }

    public static void main() {
        // вот так в сеттер могу что-то положить
        AddressUnit result = new AddressUnit();
        var setter = map.get("testName");
        setter.accept(result, "someValue");

        System.out.println(new Gson().toJson(result));
    }
}
