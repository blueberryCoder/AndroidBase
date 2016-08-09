package com.blueberry.sample.module.home;

import com.blueberry.sample.common.BasePresenter;
import com.blueberry.sample.module.home.data.HomeData;

import java.util.List;

/**
 * Created by blueberry on 2016/5/6.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    /**
     * 返回数据
     * @return
     */
    private List<HomeData> loadData() {
        return HomeData.data;
    }

    /**
     * 得到数据，并显示
     */
    public void gainData(){
        if(getView()!=null){
            getView().showData(loadData());
        }
    }
}
