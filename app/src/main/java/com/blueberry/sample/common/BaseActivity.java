package com.blueberry.sample.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/5/5.
 */
public  abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this) ;
    }


    /**
     * layout id
     * @return
     */
    protected abstract int getLayoutResId() ;


}
