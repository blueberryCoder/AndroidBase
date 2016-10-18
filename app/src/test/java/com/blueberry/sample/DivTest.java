package com.blueberry.sample;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by blueberry on 2016/8/22.
 */
public class DivTest {

    @Test
    public void testDiv(){
        Assert.assertEquals(3,div(9,3));
        System.out.println("call testDiv");

    }

    public static int div (int a,int b){
        return a/b;
    }
}
