package com.blueberry.sample.module.home;

import com.blueberry.sample.common.BaseView;
import com.blueberry.sample.module.home.data.HomeData;

import java.util.List;

/**
 * Created by blueberry on 2016/5/6.
 * 主页View
 */
public interface HomeView extends BaseView{
    /**
     * 显示主页的数据
     * @param datas  数据源
     */
   void showData(List<HomeData> datas) ;
}
