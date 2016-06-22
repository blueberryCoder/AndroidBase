package com.blueberry.sample.module.view.srcoll_confict;

import com.blueberry.sample.common.BaseView;

import java.util.List;

/**
 * Created by blueberry on 2016/6/20.
 */
public interface IScrollConfict extends BaseView{

    void showOutHVData(List<String> data1, List<String> data2, List<String> data3);

    void showInnerHVData(List<String> data1, List<String> data2, List<String> data3);

    void showOutVVData(List<String> data1);
}
