package com.blueberry.sample.module.view.srcoll_confict;

import com.blueberry.sample.common.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blueberry on 2016/6/20.
 */
public class ScrollConflictPresenter extends BasePresenter<IScrollConflict> {

    public void loadOutHVData() {
        List<String> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        List<String> data3 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            String str1 = "列表1——" + i;
            String str2 = "列表2__" + i;
            String str3 = "列表3__" + i;
            data1.add(str1);
            data2.add(str2);
            data3.add(str3);
        }
        if (getView() != null) getView().showOutHVData(data1, data2, data3);
    }

    public void loadInnerHVData(){
        List<String> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        List<String> data3 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            String str1 = "列表1——" + i;
            String str2 = "列表2__" + i;
            String str3 = "列表3__" + i;
            data1.add(str1);
            data2.add(str2);
            data3.add(str3);
        }
        if (getView() != null) getView().showInnerHVData(data1,data2,data3);
    }

    public void laodOutVVData(){
        List<String> data1 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            String str1 = "列表1——" + i;
            data1.add(str1);
        }
        if(getView()!=null) getView().showOutVVData(data1);
    }
}
