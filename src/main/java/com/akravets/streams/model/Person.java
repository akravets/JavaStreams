package com.akravets.streams.model;

public class Person {
    private final String name;
    private final String city;

    public Person(String name, String city){
        this.name = name;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}