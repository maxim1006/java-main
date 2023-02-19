package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

class BasicTestModel {}

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestModel extends BasicTestModel {
    // тут пример на использование JsonProperty, если не проставить то значение придет в json как {"default":false}, если проставить то как {"isDefault":false}
    @JsonProperty("isDefault")
    private boolean isDefault;

    // применится то что прописал в JsonProperty те hasDo
    @JsonProperty("canDo")
    private boolean hasDo;
}

@Slf4j
class TestClass {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            System.out.println(objectMapper.writeValueAsString(new TestModel()));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException error", e);
        }
    }
}


