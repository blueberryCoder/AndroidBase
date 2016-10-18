package com.blueberry.sample.module.view.sticky_nav_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/10/16.
 */

public class StickNavActivity extends BaseActivity {

    @BindView(R.id.stick_nav_layout_header)
    TextView stickNavLayoutHeader;
    @BindView(R.id.stick_nav_layout_indicator)
    TabLayout stickNavLayoutIndicator;
    @BindView(R.id.stick_nav_layout_viewpager)
    ViewPager stickNavLayoutViewpager;
    @BindView(R.id.stick_layout)
    StickNavLayout stickLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_stick_layout);
        ButterKnife.bind(this);

        stickNavLayoutViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {



            String[] title = new String[]{"title1","title2","title3"};
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                if(position==1){
//                    return RefreshFragment.newInstance();
                }
                return StickFragment.newInstance();

            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });

        stickNavLayoutIndicator.setupWithViewPager(stickNavLayoutViewpager);
        stickNavLayoutViewpager.setCurrentItem(1);
    }
}
