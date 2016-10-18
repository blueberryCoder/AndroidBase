package com.blueberry.sample.module.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.databinding.FgViewSubBinding;

import java.util.ArrayList;

/**
 * Created by blueberry on 2016/9/21.
 */

public class ViewStubFragment extends BaseFragment {
    private static final String TAG = "ViewStubFragment";

    public static ViewStubFragment newInstance() {

        Bundle args = new Bundle();

        ViewStubFragment fragment = new ViewStubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<String> arrayList = new ArrayList<String>() {
        {
            add("first.");
            add("second.");
            add("three.");
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final FgViewSubBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fg_view_sub, container, false);
//        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
//            @Override
//            public void onInflate(ViewStub stub, View inflated) {
//
//                Log.i(TAG,"viewStub "+inflated);
//                LayoutViewStubBinding binding1 = DataBindingUtil.bind(inflated);
//                binding1.setList(arrayList);
//            }
//        });
        binding.setList(arrayList);
        binding.viewStub.getViewStub().inflate();
        return binding.getRoot();
    }
}
