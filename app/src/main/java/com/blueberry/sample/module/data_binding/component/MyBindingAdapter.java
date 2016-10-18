package com.blueberry.sample.module.data_binding.component;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by blueberry on 2016/9/21.
 */

public abstract class MyBindingAdapter {
    @BindingAdapter("app:imageUrl")
    public abstract void loadImageFromUrl(ImageView iv, String url);
}
