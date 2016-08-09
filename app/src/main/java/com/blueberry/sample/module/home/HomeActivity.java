package com.blueberry.sample.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.blueberry.sample.common.BaseActivity;
import com.blueberry.sample.R;
import com.blueberry.sample.module.home.data.HomeData;
import com.blueberry.sample.module.PageActivity;
import com.blueberry.sample.widgets.ListItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by blueberry on 2016/5/6.
 */
public class HomeActivity extends BaseActivity<HomeView, HomePresenter> implements HomeView {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.home_recycle_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new ListItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        presenter.gainData();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    /**
     * 显示数据
     *
     * @param datas 数据源
     */
    @Override
    public void showData(List<HomeData> datas) {
        HomeAdapter homeAdapter = new HomeAdapter(this, datas);
        mRecyclerView.setAdapter(homeAdapter);
        setListener(homeAdapter);
    }

    /**
     * 处理点击事件
     *
     * @param homeAdapter
     */
    private void setListener(HomeAdapter homeAdapter) {
        homeAdapter.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, int id) {
                Intent intent = new Intent(HomeActivity.this, PageActivity.class);
                switch (id) {
                    case 0:
                        //多线程分析
                        Logger.d("click id : " + 0);
                        intent.putExtra(PageActivity.FRAGMENT_ID_INTENT_KEY, id);
                        startActivity(intent);
                        break;
                    case 1:
                        Logger.d("click id : " + 1);
                        intent.putExtra(PageActivity.FRAGMENT_ID_INTENT_KEY, id);
                        startActivity(intent);
                        break;
                    case 2:
                        Logger.d("click id : " + 2);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        intent.putExtra(PageActivity.FRAGMENT_ID_INTENT_KEY, id);
                        startActivity(intent);
                        break;
                    case 9:
                        intent.putExtra(PageActivity.FRAGMENT_ID_INTENT_KEY, id);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


}
