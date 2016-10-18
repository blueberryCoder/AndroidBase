package com.blueberry.sample.module.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.databinding.FgTwoWayBinding;

/**
 * Created by blueberry on 2016/9/20.
 */

public class TwoWayBindingFragment extends BaseFragment {
    public static TwoWayBindingFragment newInstance() {

        Bundle args = new Bundle();

        TwoWayBindingFragment fragment = new TwoWayBindingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FgTwoWayBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fg_two_way,container,false);
        binding.setFormModel(new FormModel());
        return binding.getRoot();
    }
}
