package com.blueberry.sample.module.drawable.fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.utils.Async;

/**
 * Created by blueberry on 2016/8/8.
 */
public class LevelListFragment extends BaseFragment {

    public static LevelListFragment newInstance() {

        Bundle args = new Bundle();

        LevelListFragment fragment = new LevelListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final ImageView iv = new ImageView(getContext());
        iv.setBackgroundDrawable(getActivity()
                .getResources().getDrawable(R.drawable.level_list));
        iv.getBackground().setLevel(200);
        Async.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                iv.getBackground().setLevel(800);
            }
        }, 2000);
        return iv;
    }


}
