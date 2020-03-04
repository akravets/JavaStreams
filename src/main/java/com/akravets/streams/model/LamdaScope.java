package com.akravets.streams.model;

public class LamdaScope {
    private String str = "instance variable";

    Vehicle v = (name) -> {
        String str = "test";
        return name + " " + this.str;
    };

    public Vehicle getVehicle(){
        return v;
    }
}
