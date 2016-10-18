package com.blueberry.sample.module.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.databinding.FgDataBindingSimpleBinding;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by blueberry on 2016/9/20.
 */

public class SimpleFragment extends BaseFragment {

    private FgDataBindingSimpleBinding dataBinding;

    private User user = new User("default firstName", "default lastName", 0, false);

    private Random random = new Random(SystemClock.currentThreadTimeMillis());

    private ArrayList<String> arrays= new ArrayList<String>(){
        {
            add("name1");
            add("name2");
            add("name3");
            add("name4");

        }
    };

    public class Presenter {
        private static final String TAG = "Presenter";

        public void onTextChange(CharSequence s, int start, int before, int count) {
            Log.d(TAG, "onTextChange: " + s);
//            if(!dataBinding.tvFirstName.getText().toString().equals(s.toString())){
//
//                dataBinding.tvFirstName.setText(s);
//            }
            user.setFirstName(s.toString());

        }

        public void onAgeChange(CharSequence s, int start, int before, int count) {
            try {

                user.setAge(Integer.parseInt(s.toString()));
            } catch (NumberFormatException e) {
            }

        }

    }

    public static SimpleFragment newInstance() {

        Bundle args = new Bundle();

        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater,
                R.layout.fg_data_binding_simple, container, false);
        user.setAge(random.nextInt(80));
        dataBinding.setUser(user);
        dataBinding.setPresenter(new Presenter());
        dataBinding.setList(arrays);

        return dataBinding.getRoot();
    }
}
