package com.example.exceptions;

// пример кастомного Exception
public class FactorialException extends Exception {
    private int number;

    public int getNumber() {
        return number;
    }

    public FactorialException(String message, int num) {
        super(message);

    }
}
