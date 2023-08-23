package com.example.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class StubUtilsExample {
    private String name;
}

@ApplicationScoped
public class StubUtils {
    @Inject
    JsonMapper jsonMapper;

    public static ObjectMapper objectMapper = new ObjectMapper();

    public <T> T getStubByLocaleAndLink(Class<T> clazz, String link) {
        InputStream input = getClass().getResourceAsStream(link);

        try {
            String stub = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            return jsonMapper.convertStringToObject(stub, clazz);
        } catch (IOException e) {
            return null;
        }

        // так тоже могу получить файл
//        File jsonFile = new File(link);
//        try {
//            JsonNode rootNode = objectMapper.readTree(jsonFile); // "/files/test.json"
    }

    public Object getStubTest() {
        InputStream input = getClass().getResourceAsStream("/files/test.json");

        try {
            String stub = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            StubUtilsExample rootNode = jsonMapper.convertStringToObject(stub, StubUtilsExample.class); // "/files/test.json"
            System.out.println(rootNode);
        } catch (IOException e) {
            return null;
        }

        // так тоже могу получить файл
//        File jsonFile = new File(link);
//        try {
//            JsonNode rootNode = objectMapper.readTree(jsonFile); // "/files/test.json"
        return null;
    }
}
