package com.blueberry.sample.module.drawable.fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;

/**
 * Created by blueberry on 2016/8/8.
 */
public class StateListFragment extends BaseFragment {

    public static StateListFragment newInstance() {

        Bundle args = new Bundle();

        StateListFragment fragment = new StateListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Button btn = new Button(getContext());
        btn.setBackgroundDrawable(getActivity()
                .getResources().getDrawable(R.drawable.selecter));
        return btn;
    }


}
