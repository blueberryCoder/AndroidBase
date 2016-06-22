package com.blueberry.sample.module.home.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blueberry on 2016/5/6.
 */
public class HomeData {

    public static List<HomeData> datas = new ArrayList<>();

    static{

        /**
         * blueberry 已领
         */
        datas.add(new HomeData(0,"android 多线程分析",null));

        /**
         * blueberry 已领
         */
        datas.add(new HomeData(1,"Android 自定义View的分析",null));

        /**
         * blueberry 已领
         */
        datas.add(new HomeData(2,"Android drawable分析",null));

        /**
         * blueberry 已领
         */
        datas.add(new HomeData(3,"Android JNI的使用",null));


        datas.add(new HomeData(4,"Android 5.0 新特性分析",null));
        datas.add(new HomeData(5,"Android 6.0 新特性分析",null));
        datas.add(new HomeData(6,"java 1.8 新特性分析",null));
        datas.add(new HomeData(7,"android 屏幕适配",null)) ;
        /**
         * 你们可以自己添加。
         */
    }
    /**
     * id
     */
    private int id;

    /**
     * content
     */
    private String content;

    /**
     * url  主页列表随意的一个图片
     */
    private String url;

    public HomeData() {
    }

    public HomeData(int id, String content, String url) {
        this.id = id;
        this.content = content;
        this.url = url;
    }

    public static List<HomeData> getDatas() {
        return datas;
    }

    public static void setDatas(List<HomeData> datas) {
        HomeData.datas = datas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
