package com.example.java.optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
class Content {
    String text;
}

class Test {
    public static Content getInstance() {
        boolean rand = Math.random() > 0.5;
        Content content = rand ? new Content("content") : null;
        System.out.println(content + " " + rand);
        return content;
    }

    public static List<Content> getInstances() {
        boolean rand = Math.random() > 0.5;
        List<Content> contents = rand ? List.of(new Content("content")) : null;
        System.out.println(contents + " " + rand);
        return contents;
    }

    public static void main(String[] args) {
        System.out.println(Optional.ofNullable(getInstance()).map(Content::getText).orElse(null));
        System.out.println(Optional.ofNullable(getInstance()).map(Content::getText).orElse(null));
        System.out.println(Optional.ofNullable(getInstance()).map(Content::getText).orElse(null));

        List<Content> list = Optional.ofNullable(getInstances())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println(list);
    }
}
