package com.blueberry.sample.module.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.R;
import com.blueberry.sample.module.view.data.ViewBean;
import com.blueberry.sample.module.view.srcoll_confict.ScrollConflictActivity;
import com.blueberry.sample.widgets.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 自定义View
 */
public class ViewFragment extends BaseFragment<IView, ViewPresenter> implements IView {

    @BindView(R.id.fg_view_recycleview)
    RecyclerView mRecycleView;

    private ViewRecycleViewAdapter adapter;

    public static ViewFragment newInstance() {

        Bundle args = new Bundle();

        ViewFragment fragment = new ViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ViewPresenter createPresenter() {
        return new ViewPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view, container, false);
        ButterKnife.bind(this, root);
        presenter.loadData();
        initView();
        return root;
    }

    private void initView() {
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        mRecycleView.addItemDecoration(new SpaceItemDecoration(16, 2));
    }


    @Override
    public void showData(List<ViewBean> datas) {
        adapter = new ViewRecycleViewAdapter(getContext(), datas);
        adapter.setmOnItemClickListener(onItemClickListener);
        mRecycleView.setAdapter(adapter);
    }

    private ViewRecycleViewAdapter.OnItemClickListener onItemClickListener = new ViewRecycleViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int pos, View view) {
            ViewBean viewBean = (ViewBean) adapter.getItem(pos);
            switch (viewBean.getId()) {
                case 1:
                    Logger.i("进入滑动冲突页面");

                    Intent intent =new Intent(getContext(), ScrollConflictActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    };
}
