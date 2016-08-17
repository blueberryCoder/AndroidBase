package com.blueberry.sample;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Builder Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public static <E> void add(List<E> list,E str){
        list.add(str) ;
    }
}