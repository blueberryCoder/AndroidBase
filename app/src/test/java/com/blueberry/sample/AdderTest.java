package com.blueberry.sample;

import junit.framework.TestCase;

/**
 * Created by blueberry on 2016/8/22.
 */
public class AdderTest extends TestCase{
    public void testAdd() {
        assertEquals(3,add(1,2));
        System.out.println("call testAdd");
    }

    public static int add(int a,int b){
        return a+b;
    }
}
