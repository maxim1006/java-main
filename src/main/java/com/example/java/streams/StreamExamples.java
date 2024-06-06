package com.example.java.streams;

import com.example.java.enums.AbstractServiceViewModel;
import com.example.models.internal.*;
import com.example.service.AbstractService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.inject.Inject;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import jakarta.annotation.PostConstruct;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Any;
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
//        streamCreation();
//        intermediateOperations();
//        streamSumAndMax();
//        collectToMapExample();
//          collectToMapExample1();
//        collectToSetExample();
//        createMapFromListExample();
        createList();
//        createMap();
    }

    static void createMap() {
        HashMap<Object, Object> map = new HashMap<>() {
            {
                put("prop", 1);
                put("prop2", 2);
            }
        };

        Map<String, Integer> map1 = Map.of("prop", 1);

        // создание через AbstractMap.SimpleEntry
        AbstractMap.SimpleEntry<String, Integer> abstractMap = new AbstractMap.SimpleEntry<>("prop", 1);
        Map<String, Integer> collect = Stream.of(abstractMap).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(Collections.emptyMap());
    }

    static void createList() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("2");

        List<Integer> ints = List.of(1, 2, 3);
        // тоже нерасширяемый как и List.of
        List<Integer> ints1 = Arrays.asList(1, 2, 3);

        // а так уже будет расширяемый
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
        integers.add(4);
    }

    static List<String> createList(List<String> ... lists) {
        return Stream.of(lists).flatMap(Collection::stream).toList();
    }


    // пример трансформации коллекции в мапу
    static void collectToMapExample() {
        var integers = new ArrayList<>();

        integers.add(1);
        integers.add(2);
        integers.add(null);
        integers.add(3);

        Map<String, Object> testMap = Optional.ofNullable(integers)
                .map(list -> list.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList)
                .stream()
                .collect(Collectors.toMap(
                        Object::toString,
                        x -> x,
                        (x, y) -> x
                ));

        System.out.println(testMap); // {1=1, 2=2, 3=3}

        // эта мапа пробегается по testMap ищет ключи containsKey
        Map<String, Object> orderedSloById = Optional.of(List.of("1","2",3,4))
                .orElse(List.of())
                .stream()
                .filter(testMap::containsKey)
                .collect(Collectors.toMap(
                        Object::toString,
                        i -> i,
                        (x, y) -> x,
                        LinkedHashMap::new
                ));

        System.out.println(orderedSloById); // {1=1, 2=2}
    }

    static void collectToMapExample1() {
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
                                toMapListMap::get,
                                (x, y) -> x,
                                LinkedHashMap::new
                        )
                );

        System.out.println("toMapMap " + toMapMap); // toMapMap {Max=husband, Aliya=wife}
    }

    static void collectToSetExample() {
        ArrayList<Map<String, String>> arr = new ArrayList<>();
        arr.add(Map.of("1", "Max"));
        arr.add(Map.of("2", "Aliya"));
        arr.add(null);

        Map<String, Map<String, String>> collect = arr.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
//                        x -> x.keySet().toArray()[0].toString(),
                        x -> x.keySet().iterator().next(),
                        y -> y
                ));

        System.out.println(collect); // {1={1=Max}, 2={2=Aliya}}

//        Collection<Object> collect1 = Optional.of(List.of("1", "2"))
        Set<Map<String, String>> collect1 = Optional.of(List.of("1", "2"))
                .orElse(List.of())
                .stream()
                .filter(collect::containsKey)
                .map(collect::get)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        System.out.println(collect1); // [{1=Max}, {2=Aliya}]

        List<Map<String, String>> collect2 = collect1.stream().toList();

        System.out.println(collect2);
    }

    static void collectToSetExample1() {
        ArrayList<Map<String, String>> arr = new ArrayList<>();

        arr.add(new HashMap<>() {
            {
                put("id", "1");
                put("name", "Max");
            }
        });

        arr.add(new HashMap<>() {
            {
                put("id", "2");
                put("name", "Aliya");
            }
        });

        Map<String, Map<String, String>> mapOfMaps = arr.stream()
                .collect(Collectors.toMap(
                        x -> x.get("id"),
                        y -> y,
                        (x, y) -> x
                ));

        Set<Map<String, String>> collect = Optional.of(List.of("1", "2")).orElse(List.of())
                .stream()
                .filter(mapOfMaps::containsKey)
                .map(mapOfMaps::get)
                .collect(Collectors.toSet());
    }

    static void createMapFromListExample() {
        Map<String, Integer> mapTest = new HashMap<>() {
            {
                put("Alice", 3);
                put("Lili", 7);
            }
        };

        List<Map<String, Integer>> list = new ArrayList<>();

        list.add(mapTest);
        list.add(mapTest);

        // cоздание Set из коллекции, создание map из list,
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

        System.out.println(list);

        // тут из интересного помимо того что из листа забираю объекты через entrySet, еще и в мерж функцию закидываю сумму этих значение и получается массив из entries с суммой возрастов
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(
                list.stream()
                        .flatMap(i -> i.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum))
                        .entrySet());

        System.out.println(entries); // [Alice=6, Lili=14]

        Map<String, Integer> collect = entries.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(collect);

        // если просто хочу собрать мап из массива
        List<String> list1 = List.of("Max", "Aliya");
        Map<String, String> collect1 = list1.stream()
                .collect(Collectors.toMap(
                        Object::toString,
                        x -> x,
                        (x, y) -> x));

        Map<String, String> collect2 = list1.stream()
                .collect(Collectors.toMap(
                        i -> i,
                        j -> j));
    }

    static void streamCreation() {
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

        boolean hasOne = strings.contains("1");

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

        // так можно найти что-то в листе и если есть то тру
//        boolean hasChar = object.getItems().stream()
//                .map(i -> i.getСhars().get(portalProperties.chars().ids().allowedForPreOrder()))
//                .anyMatch(Objects::nonNull);

//        return object.getItems().stream()
//                .map(i -> i.getMap().get(id))
//                .filter(Objects::nonNull)
//                .anyMatch(i -> i.getValue().equals(value));


        Stream<Object> stream4 = Stream.generate(() -> 2);

        // пример получения рандомного числа new Random().nextInt(__число__)
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

    static void streamSumAndMax() {
        List<AbstractMap.SimpleEntry<String, Integer>> integersList = List.of(
                new AbstractMap.SimpleEntry<>("name", 12),
                new AbstractMap.SimpleEntry<>("name", 24),
                new AbstractMap.SimpleEntry<>("name", 36)
        );

        Optional.ofNullable(integersList)
                .stream()
                .flatMap(Collection::stream)
//                .flatMap(entries -> entries.stream()) // это тоже что и .flatMap(Collection::stream)
                .map(AbstractMap.SimpleEntry::getValue)
                .mapToInt(Integer::valueOf)
                .max()
                .ifPresent(System.out::println);

        System.out.println(Optional.ofNullable(integersList)
                .stream()
                .flatMap(Collection::stream)
                .map(AbstractMap.SimpleEntry::getValue)
                .mapToInt(Integer::valueOf)
                .sum());
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
        System.out.println(Stream.concat(List.of("1", "2").stream(), Stream.of("3")).toList()); // [1, 2, 3]

        // concat multiple
        System.out.println(Stream.concat(
                Stream.concat(
                        List.of("1", "2").stream(),
                        Stream.of("3")
                ),
                Stream.of("4")
        ).collect(Collectors.toSet())); // [1, 2, 3, 4]
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

class FlatMapExample {
    @Data
    @Builder
    @AllArgsConstructor
    static class McFileEntityDescriptor {
        @EqualsAndHashCode.Exclude
        private final UUID id;
        private final UUID uuid;
        private final String variant;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class McPicture {
        @JsonProperty("image")
        private String imageUrl;
        private Map<String, String> variants;
    }

    @Data
    @Builder
    static class Item {
        private String name;
    }

    @Data
    @Builder
    static class ItemDescriptor {
        private String variant;
    }

    public static void main(String[] args) {
        List<McPicture> list = new ArrayList<>();

        list.add(McPicture.builder().variants(
                new HashMap<>() {{
                    put("small", "/small");
                    put("big", "/big");
                }}
        ).build());

        Set<McFileEntityDescriptor> fileDescriptors = Stream.concat(
                Stream.concat(
                        list.stream()
                                .filter(i -> MapUtils.isNotEmpty(i.getVariants()))
                                .flatMap(i -> i.getVariants().keySet().stream().map(j -> FlatMapExample.mapPictureToFileEntityDescriptor(i, j))),
                        list.stream().map(i -> FlatMapExample.mapPictureToFileEntityDescriptor(i, null))
                ),
                list.stream().map(i -> FlatMapExample.mapPictureToFileEntityDescriptor(i, null))
        ).collect(Collectors.toSet());

        System.out.println(fileDescriptors);
    }

    public static McFileEntityDescriptor mapPictureToFileEntityDescriptor(McPicture item, String j) {
        return McFileEntityDescriptor.builder().variant(j).build();
    }
}