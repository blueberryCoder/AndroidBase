package com.blueberry.sample.module.data_binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by blueberry on 2016/9/21.
 */

public class MyBindingAdapter {

    @BindingAdapter({"bind:imageUrl", "bind:default"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext())
                .load(url)
                .error(error)
                .into(view);
    }

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color){
        return new ColorDrawable(color);
    }
}
