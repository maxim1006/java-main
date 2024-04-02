package com.example.java.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@AllArgsConstructor
public enum EnumEtalon {
    ALL("all"),
    MANDATORY("mandatory"),
    NONE("none");

    @Getter
    @JsonValue
//    Когда аннотация @JsonValue применяется к полю в классе, это означает, что значение этого поля должно использоваться как значение JSON при преобразовании объекта в JSON. Другими словами, это значение будет использоваться вместо имени поля в качестве ключа в JSON-объекте.
    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static EnumEtalon getValue(String str) {
        if (StringUtils.isEmpty(str)) {
            return NONE;
        }

        for (EnumEtalon value : EnumEtalon.values()) {
            if (Objects.equals(value.getValue(), str)) {
                return value;
            }
        }

        return NONE;
    }

    public static String getStringValue(String str) {
        if (StringUtils.isEmpty(str)) {
            return NONE.getValue();
        }

        for (EnumEtalon value : EnumEtalon.values()) {
            if (Objects.equals(value.getValue(), str)) {
                return value.getValue();
            }
        }

        return NONE.getValue();
    }
}

