package com.example.java.streams;

import com.example.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;

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
        Map<String, Integer> mapTest = new HashMap<>();
        mapTest.put("Alice", 3);
        mapTest.put("Lili", 7);

        Set<Map.Entry<String, Integer>> newMapTest = mapTest.entrySet();

        System.out.println(newMapTest); // [Alice=3, Lili=7]

        Map<Integer, String> newMapTestResult = newMapTest.stream()
                .map(i -> Map.of(i.getValue(), i.getKey()))
                .collect(Collectors.toMap(i -> Integer.parseInt(i.keySet().toArray()[0].toString()), i -> i.values().toArray()[0].toString())); // {3=Alice, 7=Lili}

         Map<Integer, String> newMapTestResult1 = newMapTest.stream()
                 // AbstractMap.SimpleEntry только для 1 пары ключ значение
                .map(i -> new AbstractMap.SimpleEntry<>(i.getValue(), i.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<Integer, String> newMapTestResult2 = newMapTest.stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        System.out.println(newMapTestResult); // {3=Alice, 7=Lili}
        System.out.println(newMapTestResult1); // {3=Alice, 7=Lili}
        System.out.println(newMapTestResult2); // {3=Alice, 7=Lili}



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
        Integer stream5Integer = stream5.findFirst().orElse(null);
        System.out.println("stream5 " + stream5Integer);

//        random.*

//        Function<String, String> concat = (s) -> s;
//        System.out.println(concat);
//        DoubleFunction<String> valueOf = String::valueOf;
//        System.out.println(valuOf);

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
                .filter(person -> person.getName().startsWith("Е"))
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
                // проверка на то что коллекция не пустая чтобы дальше не падали null pointers
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
        // если все же хочу новый массив
//        .findFirst().orElse(new ArrayList<>());

        System.out.println(personFromPeople);

        Map<String, String> mapEx = new HashMap<>();
        mapEx.put("Greeting", "Hi mom!");

        if (MapUtils.isNotEmpty(mapEx)) {
            System.out.println("really hi)");
        }

    }
}


// пример
class Test {
    @Inject
    @Any
    Instance<AbstractService> abstractService;

    public Map<String, AbstractService> viewModelFactoriesMap;

    @PostConstruct
    public void createFactories() {
        viewModelFactoriesMap = abstractService
                .stream()
                .collect(Collectors.toMap(AbstractService::getType, Function.identity()));

        System.out.println(viewModelFactoriesMap);
    }
}