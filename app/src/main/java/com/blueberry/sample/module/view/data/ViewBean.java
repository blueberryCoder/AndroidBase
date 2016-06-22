package com.blueberry.sample.module.view.data;

/**
 * Created by blueberry on 2016/6/17.
 *
 * RecycleView 显示的数据
 */
public class ViewBean {
    private int id ;
    private String content ;

    public ViewBean() {
    }

    public ViewBean(int id,String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
