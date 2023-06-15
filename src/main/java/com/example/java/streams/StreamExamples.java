package com.example.java.streams;

import com.example.java.enums.AbstractServiceViewModel;
import com.example.models.*;
import com.example.service.AbstractService;
import io.quarkus.arc.All;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.java.basics.ObjectMapperExample.objectMapper;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

@Slf4j
public class StreamExamples {
    public static void main(String[] args) {
        streamCreation();
//        intermediateOperations();
    }

    static void streamCreation() {
        Map<String, Integer> mapTest = new HashMap<>();
        mapTest.put("Alice", 3);
        mapTest.put("Lili", 7);

        List<Map<String, Integer>> list = new ArrayList<>();

        list.add(mapTest);
        list.add(mapTest);

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

        System.out.println(new ArrayList<>(list.stream().flatMap(i -> i.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum)).entrySet()));

        if (true) return;


        // Collectors.toMap
        List<String> toMapList = List.of("Max", "Aliya");
        Map<String, String> toMapListMap = new HashMap<>() {
            {
                put("Max", "husband");
                put("Aliya", "wife");
            }
        };

        Map<String, String> toMapMap = toMapList
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> e,
                                toMapListMap::get
                        )
                );

        System.out.println("toMapMap " + toMapMap);


        // nonMatch / anyMatch
        Stream<String> streamNonMatch
                = Stream.of("CSE", "C++", "Java", "DS");

        Stream<String> streamNonMatch1 = Stream.of("CSE", "C++", "Java", "DS");
        Stream<String> streamNonMatch2 = Stream.of("CSE", "C++", "Java", "DS");

        boolean nonMatch = streamNonMatch.noneMatch(i -> i.length() > 3);
        System.out.println("nonMatch " + nonMatch); // false

        boolean anyMatch = streamNonMatch1.anyMatch(i -> i.length() > 3);
        boolean anyMatchReg = streamNonMatch2.anyMatch("CS"::matches);
        System.out.println("anyMatch " + anyMatch); // true
        System.out.println("anyMatchReg " + anyMatchReg); // false
        System.out.println("matches " + "/api/tech/some".matches("/api/tech*")); // true

        List<String> strings = List.of("1", "2", "3");
        Stream<String> stream = strings.stream();

        Stream<? extends Serializable> stream1 = Stream.of("1", 2, 3);

        String[] strings1 = {"a1", "a2", "a3"};
        Stream<String> stream2 = Arrays.stream(strings1).parallel();

        // фильтрую по null
        String strFromMap = strings.stream()
                .filter(Objects::nonNull)
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

        // пример как найти что-то в листе и вернуть если есть
        List<String> ALLOWED_DOMAINS = List.of("domain1", "google.com");
        String prefix = "google";
        System.out.println("is ALLOWED_DOMAINS " + ALLOWED_DOMAINS.stream().anyMatch(i -> StringUtils.startsWith(i, prefix)));


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

        // проверяю на distinct объекты, останется толлько 1 "Max", 35
        AbstractMap.SimpleEntry<String, Integer> distinctMap = new AbstractMap.SimpleEntry<>("Max", 35);
        AbstractMap.SimpleEntry<String, Integer> distinctMap1 = new AbstractMap.SimpleEntry<>("Max", 35);
        AbstractMap.SimpleEntry<String, Integer> distinctMap2 = new AbstractMap.SimpleEntry<>("Max1", 35);
        List<AbstractMap.SimpleEntry<String, Integer>> distinctList = List.of(distinctMap, distinctMap1, distinctMap2);

        System.out.println(distinctList.stream().distinct().toList()); // [Max=35, Max1=35]

        // классный пример с toMap и Function.identity()
        List<Team> teamList = List.of(new Team(
                "1",
                "my",
                Collections.singletonList(
                        new TeamMember(
                                "mama1213",
                                "Maxim Maximov",
                                Arrays.asList("project1", "project2", "project3", "project4")
                        )
                )
        ));


        Map<String, Team> teamListModified = teamList.stream().map(i -> {
            i.setName("My");
            return i;
        }).collect(Collectors.toMap(Team::getId, Function.identity()));

        try {
            System.out.println("teamListModified: " + objectMapper.writeValueAsString(teamListModified));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
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

        StreamTest streamTest = StreamTest.builder().id("1").items(
                List.of(StreamTest1.builder().id("inner").items(
                        List.of(StreamTest2.builder().id("inner2").items(Collections.emptyList()).build())
                ).build())).build();

        List<StreamTest2> streamTestList = streamTest
                .getItems()
                .stream()
                .map(i -> i.getItems())
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(i -> StringUtils.startsWith(i.getId(), "inner"))
                .toList();

        System.out.println(streamTestList);

        Collection<Person1> prices = Arrays.asList(
                new Person1("Max", new BigDecimal("12312123123"), Sex.MAN,
                        List.of()
                ),
                new Person1("Max1", new BigDecimal("12312123123"), Sex.MAN,
                        List.of()
                )
        );

        // findAny возвращает Optional
        Optional<BigDecimal> upfrontPrice = prices.stream()
                .filter(price -> price.getName().equals("asd"))
                .findAny()
                .map(Person1::getAge);

        System.out.println("upfrontPrice " + upfrontPrice);
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

    @Getter
    @AllArgsConstructor
    static class Person1 {
        private final String name;
        private final BigDecimal age;
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
        // этот лист можно расширять
        Collection<StreamExamples.Person> people = Arrays.asList(
                new StreamExamples.Person("Max", 16, StreamExamples.Sex.MAN,
                        List.of(new StreamExamples.Person("aMax", 16, StreamExamples.Sex.MAN, Collections.emptyList()))
                ),
                new StreamExamples.Person("Aliya", 23, StreamExamples.Sex.MAN,
                        List.of(new StreamExamples.Person("aAliya", 16, StreamExamples.Sex.MAN, Collections.emptyList()))
                ),
                new StreamExamples.Person("Lili", 42, StreamExamples.Sex.WOMEN,
                        List.of(new StreamExamples.Person("aLili", 16, StreamExamples.Sex.MAN, Collections.emptyList()))
                ),
                new StreamExamples.Person("Alice", 69, StreamExamples.Sex.MAN,
                        List.of(new StreamExamples.Person("aAlice", 16, StreamExamples.Sex.MAN, Collections.emptyList()))
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

        // убираю null из массива
        List<String> nullList = new ArrayList<>();
        nullList.add("a");
        nullList.add("b");
        nullList.add(null);
        nullList.add("c");
        System.out.println(nullList.stream().filter(Objects::nonNull).collect(Collectors.toList()));

        System.out.println(personFromPeople);

        Map<String, String> mapEx = new HashMap<>();
        mapEx.put("Greeting", "Hi mom!");

        if (MapUtils.isNotEmpty(mapEx)) {
            System.out.println("really hi)");
        }

        // Stream.concat examples
        System.out.println(Stream.concat(List.of("1", "2").stream(), Stream.of("3")).toList());

    }
}

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class EnrichableModel {
    String id;
    List<EnrichableModel> items;
    String name;

    public EnrichableModel(String id, List<EnrichableModel> items) {
        this.id = id;
        this.items = items;
    }
}

class EnrichStream {
    public static void main(String[] args) {
        EnrichableModel m = EnrichableModel.builder().id("1").items(
                List.of(EnrichableModel.builder().id("2").items(
                        List.of(EnrichableModel.builder().id("3").items(Collections.emptyList()).build())
                ).build())
        ).build();

        enrichMode(m);

        System.out.println(m);

        EnrichableModel m1 = new EnrichableModel("1",
            List.of(new EnrichableModel("2",
                    List.of(new EnrichableModel("3", List.of())))
            )
        );

        m1.setName("Max");
        enrichMode1(m1.items);

        System.out.println(m1);
    }

    static void enrichMode(EnrichableModel m) {
        if (CollectionUtils.isEmpty(m.items)) return;

        m.setName("Max");

        for (EnrichableModel i : m.items) {
            i.setName("Max");
            enrichMode(i);
        }
    }

    static void enrichMode1(List<EnrichableModel> list) {
        if (list == null) return;

        for (EnrichableModel i : list) {
            i.setName("Max");
            enrichMode1(i.items);
        }
    }
}

class TesStreamExample {
    public static void main(String[] args) {
    }
}


// пример
// TODO сделать пример без @Any а с одним инстансом
class AbstractServiceTest {
    @Inject
    @Any
    Instance<AbstractService> abstractServices;

    public Map<AbstractServiceViewModel, AbstractService> viewModelFactoriesMap;

    @PostConstruct
    public void createFactories() {
        viewModelFactoriesMap = abstractServices
                .stream()
                .collect(Collectors.toMap(AbstractService::getType, Function.identity()));

        System.out.println("viewModelFactoriesMap " + viewModelFactoriesMap);
        System.out.println("viewModelFactoriesMap " + viewModelFactoriesMap.get(AbstractServiceViewModel.DATA));
    }
}