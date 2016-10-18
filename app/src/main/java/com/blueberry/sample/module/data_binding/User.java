package com.blueberry.sample.module.data_binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.blueberry.sample.BR;

/**
 * Created by blueberry on 2016/9/20.
 */

public class User extends BaseObservable{
    private String firstName;
    private String lastName;
    private int age;
    private boolean isAdult;

    public User() {
    }

    public User(String firstName, String lastName, int age, boolean isAdult) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isAdult = isAdult;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
       notifyPropertyChanged(BR.lastName);
    }
    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
    @Bindable
    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
        notifyPropertyChanged(BR.adult);
    }
}
