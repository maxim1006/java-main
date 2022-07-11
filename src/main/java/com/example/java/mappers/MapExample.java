package com.example.java.mappers;

import com.example.properties.PortalProperties;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

// итак есть какой-то интерфейс
@Data
@AllArgsConstructor
@NoArgsConstructor
class AddressUnit {
    private String city;
    private String street;
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
        MapExampleInner.map.put("testName", AddressUnit::setCity);
        MapExampleInner.map.put("testName1", AddressUnit::setPostalCode);
        MapExampleInner.map.put("testName2", AddressUnit::setStreet);

        System.out.println(new Gson().toJson(map)); // {"testName1":{},"testName2":{},"testName":{}}

        main();
    }

    public static void main() {
        AddressUnit result = new AddressUnit();
        var setter = map.get("testName");
        setter.accept(result, "someValue");

        System.out.println(new Gson().toJson(result));
    }
}
