package com.example.java.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AbstractServiceViewModel {
    DEFAULT("default"),
    DATA("data");

    @Getter
    @JsonValue
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}