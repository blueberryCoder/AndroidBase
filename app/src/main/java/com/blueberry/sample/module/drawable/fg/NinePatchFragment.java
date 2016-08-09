package com.blueberry.sample.module.drawable.fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;

/**
 * Created by blueberry on 2016/8/8.
 */
public class NinePatchFragment extends BaseFragment {

    public static NinePatchFragment newInstance() {

        Bundle args = new Bundle();

        NinePatchFragment fragment = new NinePatchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ImageView iv = new ImageView(getContext());
        iv.setBackgroundDrawable(getActivity()
                .getResources().getDrawable(R.drawable.nine_patch));
        return iv;
    }


}
