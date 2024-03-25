package com.example.utils;

import com.example.java.basics.ObjectMapperExample;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import lombok.Data;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class StubUtilsExample {
    private String name;
}

@ApplicationScoped
public class StubUtils {
    @Inject
    JsonMapper jsonMapper;

    @Inject
    ObjectMapperExample objectMapperExample;

    public static ObjectMapper objectMapper = new ObjectMapper();

    public <T> T getStubLink(Class<T> clazz, String link) {
        InputStream input = getClass().getResourceAsStream(link);

        try {
            String stub = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            return jsonMapper.convertStringToObject(stub, clazz);
        } catch (IOException e) {
            return null;
        }
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

        return null;
    }
}
