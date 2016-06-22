package com.blueberry.sample.module.view.srcoll_confict;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;

import butterknife.OnClick;

/**
 * Created by blueberry on 2016/6/20.
 */
public class ScrollConfictActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_view_srcoll_confict;
    }

    @Override
    protected ScrollConfitPresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.btn_out_scroll_hv, R.id.btn_out_scroll_vv
            , R.id.btn_innner_scroll_hv, R.id.btn_innner_scroll_vv})
    void switchFragment(View v) {
        switch (v.getId()) {
            case R.id.btn_out_scroll_hv:
                Logger.i("click btn_out_scroll_hv");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ScrollConfictFragment.newInstance(ScrollConfictFragment.OUT_HV))
                        .commit();
                break;
            case R.id.btn_out_scroll_vv:
                Logger.i("click btn_out_scroll_vv");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ScrollConfictFragment.newInstance(ScrollConfictFragment.OUT_VV))
                        .commit();
                break;
            case R.id.btn_innner_scroll_hv:
                Logger.i("click btn_innner_scroll_hv");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ScrollConfictFragment.newInstance(ScrollConfictFragment.INNER_HV))
                        .commit();
                break;
            case R.id.btn_innner_scroll_vv:
                Logger.i("click btn_innner_scroll_vv");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ScrollConfictFragment.newInstance(ScrollConfictFragment.INNER_VV))
                        .commit();
                break;
        }
    }


}
