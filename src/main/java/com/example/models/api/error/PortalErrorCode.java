package com.example.models.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PortalErrorCode {
    //general
    UNEXPECTED_ERROR("ERROR-0001", "Unexpected error"),
    NOT_FOUND_ERROR("ERROR-0002", "Not found"),
    INPUT_VALIDATION("ERROR-0003", "Invalid input provided"),
    METHOD_NOT_ALLOWED("ERROR-0004", "Method not allowed");

    @Getter
    private final String code;

    @Getter
    private final String message;
}
