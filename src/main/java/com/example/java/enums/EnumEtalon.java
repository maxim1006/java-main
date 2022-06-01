package com.example.java.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EnumEtalon {
    ALL("all"),
    MANDATORY("mandatory"),
    NONE("none");

    @Getter
    @JsonValue
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}

