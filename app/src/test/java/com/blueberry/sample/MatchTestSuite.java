package com.blueberry.sample;


import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by blueberry on 2016/8/22.
 */
//@RunWith( Suite.class)
//@Suite.SuiteClasses({AdderTest.class,DivTest.class})
public class MatchTestSuite {


    public static Test suite() {
        TestSuite testSuite  =new TestSuite("com.blueberry.sample");
        testSuite.addTest(new JUnit4TestAdapter(AdderTest.class));
        testSuite.addTest(new JUnit4TestAdapter(DivTest.class));
        return testSuite;
    }



}
