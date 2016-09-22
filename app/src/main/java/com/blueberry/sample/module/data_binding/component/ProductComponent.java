package com.blueberry.sample.module.data_binding.component;

/**
 * Created by blueberry on 2016/9/21.
 */

public class ProductComponent implements android.databinding.DataBindingComponent {

    private MyBindingAdapter adapter = new ProductBindingAdapter();

    @Override
    public MyBindingAdapter getMyBindingAdapter() {
        return adapter;
    }
}
