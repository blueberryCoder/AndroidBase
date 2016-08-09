package com.blueberry.sample.module;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;
import com.blueberry.sample.module.drawable.DrawableFragment;
import com.blueberry.sample.module.notify.NotifyFragment;
import com.blueberry.sample.module.threads.ThreadsFragment;
import com.blueberry.sample.module.view.ViewFragment;

/**
 * Created by blueberry on 2016/5/20.
 */
public class PageActivity extends BaseActivity {

    public static final String FRAGMENT_ID_INTENT_KEY = "fragment_key";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        startFragment();
    }

    private void startFragment() {
        Fragment fragment = null;
        switch (getFragmentId()) {
            case 0:
                fragment = ThreadsFragment.newInstance();
                break;
            case 1:
                fragment = ViewFragment.newInstance();
                break;
            case 2:
                fragment = DrawableFragment.newInstance();
                break;
            case 8:
                fragment = NotifyFragment.newInstance();
                break;

        }
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                .commitAllowingStateLoss();
    }



    private int getFragmentId() {
        return getIntent().getIntExtra(FRAGMENT_ID_INTENT_KEY, 0);
    }


}
