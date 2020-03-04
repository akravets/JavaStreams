package com.akravets.streams.tests;

import com.akravets.streams.LambdaExample;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LambdaExampleTest {
    private static LambdaExample le;

    @BeforeAll
    static void setup(){
        le = new LambdaExample();
    }

    @Test
    public void testLambda(){
        String result = le.lambda("Bob");
        assertThat("clasInstanceVariable", is(result));
    }

    @Test
    public void testMyFunction(){
        String bob = le.myFunction().apply("Bob");
        assertThat(bob, is("clasInstanceVariable"));
    }
}
