package com.blueberry.sample.module.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;
import com.blueberry.sample.common.Config;
import com.blueberry.sample.databinding.ActShowDataBindingBinding;
import com.blueberry.sample.module.data_binding.component.ProductComponent;
import com.blueberry.sample.module.data_binding.component.TestComponent;

/**
 * Created by blueberry on 2016/9/20.
 * <p>
 * 参考：http://blog.zhaiyifan.cn/2016/06/16/android-new-project-from-0-p7/
 * https://developer.android.com/topic/libraries/data-binding/index.html
 */
public class ShowDataBindingActivity extends BaseActivity {

    private static final String TAG = "ShowDataBindingActivity";
    
    private ActShowDataBindingBinding dataBinding;

    public class Presenter {

        public void onClickSimpleDemo(View view) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, SimpleFragment.newInstance())
                    .addToBackStack(null).commit();
        }

        public void onClickTwoWayDemo(View view) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, TwoWayBindingFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }

        public void onClickRecyclerViewDemo(View view) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, RecyclerViewFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }

        public void onClickChainDemo(View view) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, ChainFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }

        public void onClickViewStubDemo(View view){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content,ViewStubFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }

        public void onClickBindingAdapter(View view){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content,BindingAdapterFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }

        public void onClickComponent(View view){
            if(Config.isTest){
                DataBindingUtil.setDefaultComponent(new TestComponent());
            }else{
                DataBindingUtil.setDefaultComponent(new ProductComponent());
            }
            Config.isTest=!Config.isTest;

            recreate();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.act_show_data_binding);
        dataBinding.setPresenter(new Presenter());
        Log.i(TAG, "onCreate: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
