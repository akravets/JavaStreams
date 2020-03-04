package com.akravets.streams;

import com.akravets.streams.model.Person;
import com.akravets.streams.model.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample {
    public Map<String, Long> groupByOccunrance(List<String> list){
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<String, List<Person>> groupByCity(List<Person> list) {
        Map<String, List<Person>> group = list.stream()
                .collect(Collectors.groupingBy(Person::getCity));

        return group;
    }

    public Map<Integer, List<String>> sortBySize(List<String> list){

        Stream<String> stream = Stream.of("electricity", "duck", "birds", "cat");
        Map<Integer, List<String>> collect = stream
                .collect(groupingBy(String::length));
        return collect;
    }
}
