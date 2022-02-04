package com.example.java.chips.lambda;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
