package com.example.java.streams;

import com.example.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class StreamExamples {
    public static void main(String[] args) {
        streamCreation();
        intermediateOperations();
    }

    static void streamCreation() {
        List<String> strings = List.of("1", "2", "3");
        Stream<String> stream = strings.stream();

        Stream<? extends Serializable> stream1 = Stream.of("1", 2, 3);

        String[] strings1 = {"a1", "a2", "a3"};
        Stream<String> stream2 = Arrays.stream(strings1).parallel();

        String strFromMap = strings.stream()
                .filter(i -> i.equals("1"))
                .findFirst()
                .orElse(null);


        Stream<Integer> stream3 = Stream.iterate(1, integer -> integer + 1);

        System.out.println("Stream3:");
        stream3
                .peek((item) -> System.out.println("[1]" + item))
                .limit(10)
//                .filter(i -> i % 3 == 0)
                .peek((item) -> System.out.println("[2]" + item))
//                .limit(2)
                .sorted(Comparator.reverseOrder())
                .peek((item) -> System.out.println("[3]" + item))
                /*.count()*/;
        System.out.println("Stream3 end");


        Stream<Object> stream4 = Stream.generate(() -> 2);

        Random random = new Random();
        Stream<Integer> stream5 = Stream.generate(random::nextInt);

//        random.*

//        Function<String, String> concat = (s) -> s;
//        System.out.println(concat);
//        DoubleFunction<String> valuOf = String::valueOf;
//        System.out.println(valuOf);

    }


    @Inject
    @Any
    Instance<AbstractService> abstractService;

    public Map<String, AbstractService> viewModelFactoriesMap;

    @PostConstruct
    public void createFactories() {
        viewModelFactoriesMap = abstractService
                .stream()
                .collect(Collectors.toMap(AbstractService::getType, Function.identity()));
    }

    private static void intermediateOperations() {
        Stream<Integer> infiniteStream = Stream.iterate(1, integer -> integer + 1);
        ConcurrentMap<Integer, Integer> collect = infiniteStream
                .skip(1)
                .peek(val -> System.out.println("[1] " + val))
                .limit(10)
                .peek(val -> System.out.println("[2] " + val))
                .filter(val -> val % 2 == 0)
                .peek(val -> System.out.println("[3] " + val))
                .skip(1)
                .collect(Collectors.toConcurrentMap(val -> val, val -> val));

        Collection<Person> people = Arrays.asList(
                new Person("Max", 16, Sex.MAN,
                        List.of(new Person("aMax", 16, Sex.MAN, List.of()))
                ),
                new Person("Aliya", 23, Sex.MAN,
                        List.of(new Person("aAliya", 16, Sex.MAN, List.of()))
                ),
                new Person("Lili", 42, Sex.WOMEN,
                        List.of(new Person("aLili", 16, Sex.MAN, List.of()))
                ),
                new Person("Alice", 69, Sex.MAN,
                        List.of(new Person("aAlice", 16, Sex.MAN, List.of()))
                )
        );

//        IntSummaryStatistics statistics = people.stream()
//                .filter((p) -> p.getSex() == Sex.MAN)
//                .mapToInt(Person::getAge)
//                .summaryStatistics();
//        System.out.println("avg age = " + statistics.getAverage() + " / count : " + statistics.getCount());

        //sort by sex & then by age
        Collection<Person> bySexAndAge = people
                .stream()
//                .sorted((o1, o2) -> o1.getSex() != o2.getSex() ? o1.getSex().compareTo(o2.getSex()) : o1.getAge().compareTo(o2.getAge()))
                .peek(elem -> {
                    System.out.println("elem " + elem);
                })
//                .sorted(comparing(Person::getSex).thenComparing(Person::getName))
                .sorted(comparing(Person::getSex)
                        .thenComparing(comparingInt(Person::getAge).reversed()))
                .filter(person -> person.getName().startsWith("??"))
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(bySexAndAge);

    }

    static class MyPersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    enum Sex {MAN, WOMEN}

    @Getter
    @AllArgsConstructor
    static class Person {
        private final String name;
        private final Integer age;
        private final Sex sex;
        private final List<Person> persons;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }
    }
}

class MultipleFilters {
    public static void main(String[] args) {
        Collection<StreamExamples.Person> people = Arrays.asList(
                new StreamExamples.Person("Max", 16, StreamExamples.Sex.MAN,
                        List.of(new StreamExamples.Person("aMax", 16, StreamExamples.Sex.MAN, List.of()))
                ),
                new StreamExamples.Person("Aliya", 23, StreamExamples.Sex.MAN,
                        List.of(new StreamExamples.Person("aAliya", 16, StreamExamples.Sex.MAN, List.of()))
                ),
                new StreamExamples.Person("Lili", 42, StreamExamples.Sex.WOMEN,
                        List.of(new StreamExamples.Person("aLili", 16, StreamExamples.Sex.MAN, List.of()))
                ),
                new StreamExamples.Person("Alice", 69, StreamExamples.Sex.MAN,
                        List.of(new StreamExamples.Person("aAlice", 16, StreamExamples.Sex.MAN, List.of()))
                )
        );

        StreamExamples.Person personFromPeople = people
                .stream()
                // ???????????????? ???? ???? ?????? ?????????????????? ???? ???????????? ?????????? ???????????? ???? ???????????? null pointers
                .filter(slo -> CollectionUtils.isNotEmpty(people))
                .filter(i -> i.getName()
                        .equals("Max")
                )
                .findFirst().flatMap(i -> i.getPersons()
                        .stream()
                        .filter(item -> item.getName()
                                .equals("aMax")
                        )
                        .findFirst())
                .orElse(null);
        // ???????? ?????? ???? ???????? ?????????? ????????????
//        .findFirst().orElse(new ArrayList<>());

        System.out.println(personFromPeople);
    }
}