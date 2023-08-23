package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class JsonMapper {

    @Inject
    ObjectMapper mapper;

    public String convertObjectToString(Object value) {
        return convertObjectToString(value, false);
    }

    public String convertObjectToPrettyString(Object value) {
        return convertObjectToString(value, true);
    }

    private String convertObjectToString(Object value, boolean pretty) {
        try {
            if (value instanceof Boolean || value instanceof String) {
                return String.valueOf(value);
            } else {
                if (pretty) {
                    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
                } else {
                    return mapper.writeValueAsString(value);
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Error convert object to string", e);
        }
        return null;
    }

    public <T> T convertStringToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

    public <T> Map<String, T> convertStringToMap(String json, Class<T> valueClazz) {
        if (json == null) {
            return null;
        }

        MapType typedMap = mapper.getTypeFactory().constructMapType(Map.class, String.class, valueClazz);
        return readValue(json, typedMap);
    }

    public <T> Map<String, T> convertStringToMap(String json, TypeReference<T> valueType) {
        if (json == null) {
            return null;
        }

        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType typedMap = typeFactory.constructMapType(
            Map.class,
            typeFactory.constructType(String.class),
            typeFactory.constructType(valueType)
        );
        return readValue(json, typedMap);
    }

    private <T> T readValue(String json, MapType typedMap) {
        try {
            return mapper.readValue(json, typedMap);
        } catch (JsonProcessingException e) {
            log.error("Error read mapper value", e);
        }
        return null;
    }

    public <K, V> Map<K, V> convertObjectToMap(Object object, Class<K> keyClazz, Class<V> valueClazz) {
        MapType typedMap = mapper.getTypeFactory().constructMapType(
            Map.class,
            keyClazz,
            valueClazz
        );

        return mapper.convertValue(object, typedMap);
    }
}
