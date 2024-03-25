package com.example.java.basics;

import com.example.exceptions.JsonParsingException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ExampleDto {
    private String name;
    private String details;
    private Integer order;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class ExampleJsonDto {
    private String name;
    private String details;
    private Integer order;
}

@Slf4j
@ApplicationScoped
public class ObjectMapperExample {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        ExampleJsonDto exampleJsonTreeObjectClassBase = ExampleJsonDto.builder().details("myDetails").name("Max").build();

        String dtoAsString = objectMapper.writeValueAsString(exampleJsonTreeObjectClassBase);

        // конвертация в JsonNode
        JsonNode jsonNode = objectMapper.valueToTree(exampleJsonTreeObjectClassBase);

        // еще можно конвертнуть в JsonNode вот так
         JsonNode jsonNode1 = objectMapper.convertValue(exampleJsonTreeObjectClassBase, JsonNode.class);

        // первый пример конвертации в JsonNode
        ExampleDto exampleDto = getPrepaidPlanFromJsonNode(jsonNode);
        System.out.println(exampleDto);
        // второй пример конвертации в JsonNode тут в логи выходит строка объекта от ломбок, поэтому остается order null
        System.out.println(getPrepaidPlanFromJsonNode(jsonNode1));
        // а тут уже конверчу в JSON и нет order null
        System.out.println(objectMapper.valueToTree(getPrepaidPlanFromJsonNode(jsonNode1)));
        // а тут уже конверчу в строку и тоже нет order null, эта строка отправляется на клиент
        System.out.println(objectMapper.writeValueAsString(getPrepaidPlanFromJsonNode(jsonNode1)));
        // тут в dtoAsString не будет null значений так как из objectMapper исключил null
        System.out.println(dtoAsString);
        // а так перевожу в JSON
        System.out.println(objectMapper.valueToTree(dtoAsString));
    }

    // основное в этом методе, парсим JsonNode в модельку по классу
    public static ExampleDto getPrepaidPlanFromJsonNode(JsonNode jsonNode) {
        return readJsonTree(jsonNode, ExampleDto.class);
    }

    public static <T> T readJsonTree(JsonNode jsonNode, Class<T> valueType) {
        if (jsonNode == null) {
            return null;
        }
        try {
            return objectMapper.treeToValue(jsonNode, valueType);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException(e);
        }
    }

    public static <T> T readJsonTreeSafe(JsonNode jsonNode, Class<T> valueType) {
        try {
            return objectMapper.treeToValue(jsonNode, valueType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
