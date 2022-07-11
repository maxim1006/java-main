package com.example.java.chips.lambda;

import com.example.models.TeamMember;

import java.util.*;

public class LambdaExample {
    public static List<TeamMember> teams = Arrays.asList(
            new TeamMember("1", "Maxim Maximov", List.of("project1")),
            new TeamMember("2", "Maxim Maximov1", List.of("project2")));

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t: list) {
            result.add(f.apply(t));
        }

        return result;
    }

    public static void main(String[] args) {
        //comparator
        Comparator<TeamMember> byName = new Comparator<TeamMember>() {
            public int compare(TeamMember o1, TeamMember o2) {
                return o1.getName().compareTo(o2.getName()) ;
            }
        };

        Collections.sort(teams, byName);
        System.out.println("Sort by name Teams : " + teams.toString());

        //use lambda expression
        Collections.sort(teams, ((o1, o2) -> o1.getName().compareTo(o2.getName())));
        System.out.println("Sort by name (used lambda expression) Teams: " + teams.toString());

        //Syntax lambda expression
//        () -> {}.
//        () -> "Raoul".
//        () -> { return "Mario"; }.
//        (Integer i) -> return "Team" + i;. // invalid
//        (String s) -> { "Test"; }. // invalid

        // Function interface
        List<Integer> l = map(Arrays.asList("lambda", "in", "action"), (String s) -> s.length());

        System.out.println("Function interface map: " + l.toString());

        // Method reference and constructor
        // (TeamMember teamItem) -> teamItem.getName()
        Collections.sort(teams, (Comparator.comparing(TeamMember::getName)));
        System.out.println("Sort by name (used lambda expression) Teams: " + teams.toString());

        List<Integer> l2 = map(Arrays.asList("lambda", "in", "action"), String::length);

        // () -> TeamMember::new
    }
}
