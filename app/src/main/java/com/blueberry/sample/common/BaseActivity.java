package com.blueberry.sample.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/5/5.
 */
public  abstract  class BaseActivity<V extends BaseView,P extends BasePresenter<V >>  extends AppCompatActivity {
    
    protected  P  presenter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this) ;
        presenter = createPresenter() ;
        if(presenter!=null)
        presenter.attachView((V)this);
    }

    /**
     * factory method
     * 获得presentor
     * @return
     */
    protected abstract P createPresenter();

    /**
     * layout id
     * @return
     */
    protected abstract int getLayoutResId() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter !=null)
        presenter.detachView();
    }
}
