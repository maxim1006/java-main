package com.example.java.optional;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

public class OptionalTest {
    @Data
    @Builder
    private static class Test {
        public String prop;
    }

    public static void main(String[] args) {
        Optional<Test> test = getTest(null);
        Optional<Test> test1 = getTest("str");

        System.out.println(test.isPresent()); // false
        System.out.println(test1.isPresent()); // true

        Test.builder().prop("123").build();
    }

    private static Optional<Test> getTest(String str) {
        if (str == null) return Optional.empty();

        return Optional.of(Test.builder().prop("new prop").build());
    }
}

class Test {
    public static void main(String[] args) {

    }
}
