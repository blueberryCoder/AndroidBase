package com.blueberry.sample.module.data_binding.component;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by blueberry on 2016/9/21.
 */

public class TestBindingAdapter extends MyBindingAdapter {

    private static final String TAG = "TestBindingAdapter";

    @Override
    public void loadImageFromUrl(ImageView iv, String url) {
        Log.i(TAG, "loadImageFromUrl: "+url);
        Glide.with(iv.getContext()).load(url).into(iv);
    }
}
