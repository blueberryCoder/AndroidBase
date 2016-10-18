package com.blueberry.sample.module.data_binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by blueberry on 2016/9/20.
 */

public class FormModel extends BaseObservable{
    private String userName;
    private String passWord;

    public FormModel(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public FormModel() {
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(com.blueberry.sample.BR.userName);
    }

    @Bindable
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
        notifyPropertyChanged(com.blueberry.sample.BR.passWord);
    }
}
