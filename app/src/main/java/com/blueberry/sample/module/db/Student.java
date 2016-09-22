package com.blueberry.sample.module.db;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by blueberry on 2016/8/18.
 */
@Table(name="students")
public class Student extends Model {
    @Column(name="sid")
    public long id;
    @Column(name="name")
    public String name;
    @Column(name = "tel_no")
    public String tel_no;
    @Column(name = "cls_id")
    public int cls_id;

    public Student(){
        super();
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel_no='" + tel_no + '\'' +
                ", cls_id='" + cls_id + '\'' +
                '}';
    }
}
