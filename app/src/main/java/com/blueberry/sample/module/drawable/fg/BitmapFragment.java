package com.blueberry.sample.module.drawable.fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;

/**
 * Created by blueberry on 2016/8/8.
 */
public class BitmapFragment extends BaseFragment {

    public static BitmapFragment newInstance() {

        Bundle args = new Bundle();

        BitmapFragment fragment = new BitmapFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ImageView iv = new ImageView(getContext());
        // bitmap 支持三种格式图片  png(首选),jpg(可接受),gif(不推荐)
        iv.setBackgroundDrawable(getActivity()
                .getResources().getDrawable(R.drawable.bitmap_drawable));
        return iv;
    }


}
