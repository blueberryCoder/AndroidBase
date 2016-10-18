package com.blueberry.sample.module.view.sticky_nav_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueberry.sample.R;
import com.blueberry.sample.widgets.ListItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/10/16.
 */

public class RefreshFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    public static RefreshFragment newInstance() {

        Bundle args = new Bundle();

        RefreshFragment fragment = new RefreshFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fg_refresh_stick, container, false);
        ButterKnife.bind(this, root);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new ListItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new StickAdapter(getContext(),null));

        return root;
    }
}
