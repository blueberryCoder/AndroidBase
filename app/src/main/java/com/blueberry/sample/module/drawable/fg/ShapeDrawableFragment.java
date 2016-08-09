package com.blueberry.sample.module.drawable.fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;

/**
 * Created by blueberry on 2016/8/9.
 */
public class ShapeDrawableFragment extends BaseFragment {
    public static ShapeDrawableFragment newInstance() {

        Bundle args = new Bundle();

        ShapeDrawableFragment fragment = new ShapeDrawableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ScrollView scrollView = new ScrollView(getContext());

        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);

        ImageView rectImageView = new ImageView(getContext());
        rectImageView.setImageResource(R.drawable.rect_shape);
        ll.addView(rectImageView);

        ImageView ovalImageView = new ImageView(getContext());
        ovalImageView.setImageResource(R.drawable.oval_shape);
        ll.addView(ovalImageView);

        ImageView lineImageView = new ImageView(getContext());
        lineImageView.setImageResource(R.drawable.line_shape);
        lineImageView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);
        lineImageView.setLayoutParams(params);
        ll.addView(lineImageView);

        ImageView ringImageView = new ImageView(getContext());
        ringImageView.setImageResource(R.drawable.ring_shape);
        ll.addView(ringImageView);


        scrollView.addView(ll);
        return scrollView;
    }
}
