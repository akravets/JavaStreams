package com.akravets.streams.tests;

import com.akravets.streams.StreamExample;
import com.akravets.streams.model.LamdaScope;
import com.akravets.streams.model.Person;
import com.akravets.streams.model.Vehicle;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import com.sun.org.apache.xpath.internal.functions.FuncCeiling;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import sun.security.pkcs11.wrapper.Functions;

import java.io.StringReader;
import java.net.Inet4Address;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class StreamExampleTests {
   StreamExample streamExample;
   List<Person> personList;
   List<String> list;

   @BeforeAll
   void setup(){
       streamExample = new StreamExample();
       personList = Arrays.asList(
         new Person("Bob", "New York"),
         new Person("Mike", "New York"),
         new Person("Alex", "Texas")
       );

       list = Arrays.asList(new String[]{"one", "two", "two", "three", "three", "three"});
   }

   @Ignore
    public void testGroupByOccurance(){
        Map<String, Long> map = streamExample.groupByOccunrance(list);
        Map<String, Long> expected = new HashMap<String, Long>();
        expected.put("one", new Long(1));
        expected.put("three", new Long(2));
        expected.put("two", new Long(2));

        assertThat(expected, IsMapContaining.hasEntry("one", 1L));
    }

    @Disabled
    @Ignore
    public void testGroupByCity() {
        Map<String, List<Person>> stringListMap = streamExample.groupByCity(personList);
        //System.out.println(stringListMap);

        stringListMap.forEach((k, v) -> {
            System.out.println(k + ": " +
                    v.stream().
                            map(Person::getName).
                            collect(Collectors.joining(",")));
        });
    }


    @Test
    public void testSortBySize(){
        List<String> list = Arrays.asList("electricity", "duck", "birds", "cat");

        Map<Integer, List<String>> result = streamExample.sortBySize(list);

        Stream<String> s = Stream.of("apple", "banana", "orange", "onee", "on");
        Map<String, Integer> collect = s.distinct().collect(Collectors.toMap(Function.identity(), String::length));

        LinkedHashMap<String, Integer> res = collect.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        res.forEach((k,v) -> System.out.println(k + ": " + v));
    }

    @Disabled
    @Test
    public void testPredicate() {
        List<Person> people = personList.stream().filter(new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getCity().startsWith("N");
            }
        }).collect(Collectors.toList());

        Predicate p = new Predicate() {
            @Override
            public boolean test(Object o) {
                return ((String) o).length() == 3;
            }
        };
        Object collect = Stream.of("electricity", "duck", "birds", "cat").filter(p).collect(Collectors.toSet());

        BinaryOperator<Integer> add = (o, o2) -> {
            System.out.println(o + o2);
            return o + o2;
        };

        BinaryOperator<Integer> mult = (o, o2) -> {
            System.out.println(o * o2);
            return o * o2;
        };

        Function<Integer, Integer> multiply = (o) -> o * 2;
        BiFunction<Integer, Integer, Integer> addAndMultiply = add.andThen(multiply);
        Integer apply = addAndMultiply.apply(2, 3);


        Function<String, String> test = (value) -> value + "!";
        String boom = test.apply("Boom");
        System.out.println(boom);
    }

    @Test
    public void testLambdaScope(){
        LamdaScope ls = new LamdaScope();
        Vehicle vehicle = ls.getVehicle();
        String bobz = vehicle.process("hi");
        Assert.assertEquals("hi instance variable", bobz);

        Stream.of("java", "c", "c#", "python")
                .map(x -> x + "|");
                //.reduce("Languages:", (x,y)->x+" | "+y);
        System.out.println();


    }
}
