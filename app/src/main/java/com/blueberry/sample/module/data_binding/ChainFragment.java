package com.blueberry.sample.module.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.databinding.FgChainBinding;
import com.blueberry.sample.utils.Async;

/**
 * Created by blueberry on 2016/9/21.
 */

public class ChainFragment extends BaseFragment {

    private User user = new User("firstName","lastName",20,true);

    public static ChainFragment newInstance() {

        Bundle args = new Bundle();

        ChainFragment fragment = new ChainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FgChainBinding binding =DataBindingUtil.inflate(inflater,
                R.layout.fg_chain,container,false);
        binding.setUser(user);
        Async.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                user.setAge(1);
            }
        },3000);

        return binding.getRoot();
    }
}
