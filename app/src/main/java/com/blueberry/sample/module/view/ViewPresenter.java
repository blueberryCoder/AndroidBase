package com.blueberry.sample.module.view;

import com.blueberry.sample.common.BasePresenter;
import com.blueberry.sample.module.view.data.ViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blueberry on 2016/6/17.
 */
public class ViewPresenter extends BasePresenter<IView> {

    /**
     * 加载数据
     */
    public void loadData(){
        List<ViewBean> datas = new ArrayList<>();
        datas.add(new ViewBean(1,"滑动冲突"));
        datas.add(new ViewBean(2,"事件分发机制"));
        datas.add(new ViewBean(3,"未实现")) ;
        datas.add(new ViewBean(3,"未实现")) ;
        datas.add(new ViewBean(3,"未实现")) ;
        datas.add(new ViewBean(3,"未实现")) ;
        datas.add(new ViewBean(3,"未实现")) ;
        datas.add(new ViewBean(3,"未实现")) ;
        datas.add(new ViewBean(3,"未实现")) ;
        datas.add(new ViewBean(3,"未实现")) ;
        //显示数据
        getView().showData(datas);
    }
}
