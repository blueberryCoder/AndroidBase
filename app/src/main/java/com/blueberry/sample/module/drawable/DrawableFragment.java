package com.blueberry.sample.module.drawable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.common.BaseRvAdapter;
import com.blueberry.sample.module.drawable.adapter.DrawableRvAdapter;
import com.blueberry.sample.widgets.ListItemDecoration;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/8/8.
 */
public class DrawableFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    public static DrawableFragment newInstance() {

        Bundle args = new Bundle();

        DrawableFragment fragment = new DrawableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fg_drawable, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        rv.addItemDecoration(new ListItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        DrawableRvAdapter rvAdapter = new DrawableRvAdapter(getContext(),
                Arrays.asList("bitmap", "nine-patch", "Layer List", "State List", "Level List",
                        "Transition Drawable", "Inset Drawable", "Clip Drawable", "Scale Drawable",
                        "Shape Drawable"
                ));
        rv.setAdapter(rvAdapter);

        rvAdapter.setOnRvItemClickListener(new BaseRvAdapter.OnRvItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                Intent intent = new Intent(getContext(), DrawableActivity.class);
                intent.putExtra(DrawableActivity.ID_KEY, pos);
                startActivity(intent);
            }
        });
    }


}
