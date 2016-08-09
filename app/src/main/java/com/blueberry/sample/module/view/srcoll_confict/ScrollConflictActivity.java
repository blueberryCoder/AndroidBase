package com.blueberry.sample.module.view.srcoll_confict;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by blueberry on 2016/6/20.
 */
public class ScrollConflictActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_srcoll_confict);
        ButterKnife.bind(this);
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
                                ScrollConflictFragment.newInstance(ScrollConflictFragment.OUT_HV))
                        .commit();
                break;
            case R.id.btn_out_scroll_vv:
                Logger.i("click btn_out_scroll_vv");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ScrollConflictFragment.newInstance(ScrollConflictFragment.OUT_VV))
                        .commit();
                break;
            case R.id.btn_innner_scroll_hv:
                Logger.i("click btn_innner_scroll_hv");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ScrollConflictFragment.newInstance(ScrollConflictFragment.INNER_HV))
                        .commit();
                break;
            case R.id.btn_innner_scroll_vv:
                Logger.i("click btn_innner_scroll_vv");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                ScrollConflictFragment.newInstance(ScrollConflictFragment.INNER_VV))
                        .commit();
                break;
        }
    }


}
