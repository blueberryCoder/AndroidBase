package com.blueberry.sample.module.view;

import com.blueberry.sample.common.BaseView;
import com.blueberry.sample.module.view.data.ViewBean;

import java.util.List;

/**
 * Created by blueberry on 2016/6/17.
 */
public interface IView extends BaseView{

    void showData(List<ViewBean> datas);
}
