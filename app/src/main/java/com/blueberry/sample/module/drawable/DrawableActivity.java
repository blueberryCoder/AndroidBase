package com.blueberry.sample.module.drawable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.FrameLayout;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;
import com.blueberry.sample.module.drawable.fg.BitmapFragment;
import com.blueberry.sample.module.drawable.fg.ClipDrawableFragment;
import com.blueberry.sample.module.drawable.fg.InsetDrawableFragment;
import com.blueberry.sample.module.drawable.fg.LayerListFragment;
import com.blueberry.sample.module.drawable.fg.LevelListFragment;
import com.blueberry.sample.module.drawable.fg.NinePatchFragment;
import com.blueberry.sample.module.drawable.fg.ScaleDrawableFragment;
import com.blueberry.sample.module.drawable.fg.ShapeDrawableFragment;
import com.blueberry.sample.module.drawable.fg.StateListFragment;
import com.blueberry.sample.module.drawable.fg.TransitionDrawableFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/8/8.
 */
public class DrawableActivity extends BaseActivity {

    public static final String ID_KEY = "id_key";

    private int curId;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable);
        ButterKnife.bind(this) ;
        curId = getIntent().getIntExtra(ID_KEY, 0);
        createFragment();

    }

    private void createFragment() {
        Fragment fragment = null;
        Log.i("TAG","id=="+curId);
        switch (curId) {
            case 0:
                fragment = BitmapFragment.newInstance();
                break;
            case 1:
                fragment = NinePatchFragment.newInstance();
                break;
            case 2:
                fragment = LayerListFragment.newInstance();
                break;
            case 3:
                fragment = StateListFragment.newInstance();
                break;
            case 4:
                fragment = LevelListFragment.newInstance();
                break;
            case 5:
                fragment = TransitionDrawableFragment.newInstance();
                break;
            case 6:
                fragment = InsetDrawableFragment.newInstance();
                break;
            case 7:
                fragment = ClipDrawableFragment.newInstance();
                break;
            case 8:
                fragment = ScaleDrawableFragment.newInstance();
                break;
            case 9:
                fragment = ShapeDrawableFragment.newInstance();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commitAllowingStateLoss();
    }
}
