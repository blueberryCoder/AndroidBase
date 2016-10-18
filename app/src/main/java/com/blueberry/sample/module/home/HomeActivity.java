package com.blueberry.sample.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;
import com.blueberry.sample.module.PageActivity;
import com.blueberry.sample.module.animation.AnimationActivity;
import com.blueberry.sample.module.camera.CameraActivity;
import com.blueberry.sample.module.data_binding.ShowDataBindingActivity;
import com.blueberry.sample.module.db.DBActivity;
import com.blueberry.sample.module.home.data.HomeData;
import com.blueberry.sample.module.http.HttpActivity;
import com.blueberry.sample.widgets.ListItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/5/6.
 */
public class HomeActivity extends BaseActivity {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.home_recycle_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new ListItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        HomeAdapter homeAdapter = new HomeAdapter(this, HomeData.getData());
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
                        intent.putExtra(PageActivity.FRAGMENT_ID_INTENT_KEY, id);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra(PageActivity.FRAGMENT_ID_INTENT_KEY, id);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra(PageActivity.FRAGMENT_ID_INTENT_KEY, id);
                        startActivity(intent);
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
                        Intent intent1 = new Intent(HomeActivity.this, AnimationActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                        break;
                    case 10:
                        Intent intent2 = new Intent(HomeActivity.this, CameraActivity.class);
                        startActivity(intent2);
                        break;
                    case 11:
                        Intent intent3 = new Intent(HomeActivity.this,HttpActivity.class);
                        startActivity(intent3);
                        break;
                    case 12:
                        Intent intent4 = new Intent(HomeActivity.this, DBActivity.class);
                        startActivity(intent4);
                        break;
                    case 13:
                        // Data Binding
                        startActivity(new Intent(HomeActivity.this, ShowDataBindingActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
