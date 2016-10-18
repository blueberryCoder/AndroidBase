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
 * Created by blueberry on 2016/8/9.
 */
public class ClipDrawableFragment extends BaseFragment {
    public static ClipDrawableFragment newInstance() {

        Bundle args = new Bundle();

        ClipDrawableFragment fragment = new ClipDrawableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Button btn = new Button(getContext());
        btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.clip_drawable));
        btn.getBackground().setLevel(9000);
        return btn;
    }
}
