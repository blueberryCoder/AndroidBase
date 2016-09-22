package com.blueberry.sample.module.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.databinding.FgBindAdapterBinding;

/**
 * Created by blueberry on 2016/9/21.
 */

public class BindingAdapterFragment extends BaseFragment {
    public static BindingAdapterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BindingAdapterFragment fragment = new BindingAdapterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FgBindAdapterBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fg_bind_adapter,container,false
        );

        binding.setImage(
                new Image("https://ss0.bdstatic.com" +
                        "/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4037780041,4066468437&fm=116&gp=0.jpg"));
        return binding.getRoot();
    }
}
