package com.blueberry.sample.module.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.databinding.FgRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blueberry on 2016/9/21.
 */

public class RecyclerViewFragment extends BaseFragment {

    private List<User> users = new ArrayList<User>() {
        {
            add(new User("fistName1", "lastName1", 1, false));
            add(new User("fistName2", "lastName2", 2, false));
            add(new User("fistName3", "lastName3", 20, true));
            add(new User("fistName4", "lastName4", 3, false));
            add(new User("fistName5", "lastName5", 40, true));
            add(new User("fistName6", "lastName6", 2, false));
        }
    };

    public static RecyclerViewFragment newInstance() {

        Bundle args = new Bundle();

        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FgRecyclerViewBinding binding
                = DataBindingUtil.inflate(inflater, R.layout.fg_recycler_view, container, false);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(new UserAdapter(getContext(), users));
        return binding.getRoot();
    }
}
