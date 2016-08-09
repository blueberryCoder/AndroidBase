package com.blueberry.sample.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by blueberry on 2016/5/20.
 */
public abstract class  BaseFragment<V extends BaseView,
        P extends BasePresenter <V>> extends Fragment{

    protected P presenter ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if(presenter!=null){
            presenter.attachView((V) this);
        }
    }

    /**
     * 创建 Presenter
     * @return
     */
    protected abstract P createPresenter()  ;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }
}
