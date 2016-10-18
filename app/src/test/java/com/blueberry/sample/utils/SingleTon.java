package com.blueberry.sample.utils;

/**
 * Created by blueberry on 2016/8/29.
 */
public class SingleTon {
    private static SingleTon instance = new SingleTon();
    protected SingleTon(){}
    public static SingleTon getInstance(){
        return instance;
    }
}

