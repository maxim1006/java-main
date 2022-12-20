package com.example.exceptions;

public class JsonParsingException extends RuntimeException {
    public JsonParsingException(Throwable cause) {
        super(cause);
    }
}
