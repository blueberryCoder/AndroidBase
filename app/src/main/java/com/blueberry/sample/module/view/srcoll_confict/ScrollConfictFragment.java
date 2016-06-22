package com.blueberry.sample.module.view.srcoll_confict;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blueberry.sample.common.BaseFragment;
import com.blueberry.sample.widget.HorizontalEx;
import com.blueberry.sample.widget.HorizontalEx2;
import com.blueberry.sample.widget.ListViewEx;
import com.blueberry.sample.widget.refresh.RefreshLayoutBase;
import com.blueberry.sample.widget.refresh.RefreshLayoutBase2;
import com.blueberry.sample.widget.refresh.RefreshListView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by blueberry on 2016/6/20.
 */
public class ScrollConfictFragment extends BaseFragment<IScrollConfict, ScrollConfitPresenter> implements IScrollConfict {

    private static final String TAG = "ScrollConfictFragment";

    public static final String SHOW_KEY = "show_key";

    public static final int OUT_HV = 1;
    public static final int OUT_VV = 2;
    public static final int INNER_HV = 3;
    public static final int INNER_VV = 4;
    private int code;

    private HorizontalEx mHorizontalEx;
    private HorizontalEx2 mHorizontalEx2;
    private RefreshListView mRefreshListView;
    private RefreshLayoutBase2 mRefreshListView2 ;

    public static ScrollConfictFragment newInstance(int code) {

        Bundle args = new Bundle();
        args.putInt(SHOW_KEY, code);
        ScrollConfictFragment fragment = new ScrollConfictFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        code = getArguments().getInt(SHOW_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = null;
        switch (code) {
            case OUT_HV:
                mHorizontalEx = new HorizontalEx(getContext());
                root = mHorizontalEx;
                break;
            case OUT_VV:
                mRefreshListView = new RefreshListView(getContext());
                root = mRefreshListView;
                break;
            case INNER_HV:
                mHorizontalEx2 = new HorizontalEx2(getContext());
                root = mHorizontalEx2;
                break;
            case INNER_VV:
                mRefreshListView2 = new RefreshLayoutBase2(getContext()) ;
                root = mRefreshListView2 ;
                break;
        }

        presenter.loadOutHVData();
        presenter.loadInnerHVData();
        presenter.laodOutVVData();
        return root;
    }

    @Override
    protected ScrollConfitPresenter createPresenter() {
        return new ScrollConfitPresenter();
    }

    @Override
    public void showOutHVData(List<String> data1, List<String> data2, List<String> data3) {
        if (code != OUT_HV) return;
        ListView listView1 = new ListView(getContext());
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data1);
        listView1.setAdapter(adapter1);

        ListView listView2 = new ListView(getContext());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data2);
        listView2.setAdapter(adapter2);

        ListView listView3 = new ListView(getContext());
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data3);
        listView3.setAdapter(adapter3);

        ViewGroup.LayoutParams params
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mHorizontalEx.addView(listView1, params);
        mHorizontalEx.addView(listView2, params);
        mHorizontalEx.addView(listView3, params);
    }

    @Override
    public void showInnerHVData(List<String> data1, List<String> data2, List<String> data3) {
        if (code != INNER_HV) return;
        ListViewEx listView1 = new ListViewEx(getContext());
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data1);
        listView1.setAdapter(adapter1);
        listView1.setmHorizontalEx2(mHorizontalEx2);

        ListViewEx listView2 = new ListViewEx(getContext());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data2);
        listView2.setAdapter(adapter2);
        listView2.setmHorizontalEx2(mHorizontalEx2);

        ListViewEx listView3 = new ListViewEx(getContext());
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data3);
        listView3.setAdapter(adapter3);
        listView3.setmHorizontalEx2(mHorizontalEx2);

        ViewGroup.LayoutParams params
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mHorizontalEx2.addView(listView1, params);
        mHorizontalEx2.addView(listView2, params);
        mHorizontalEx2.addView(listView3, params);
    }


    @Override
    public void showOutVVData(List<String> data1) {
        if (code != OUT_VV) return;
        ListView listView = new ListView(getContext());
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data1);
        listView.setAdapter(adapter1);
        mRefreshListView.setListView(listView);
        mRefreshListView.setOnRefreshListener(new RefreshLayoutBase.OnRefreshListener() {
            @Override
            public void refresh() {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        Log.i(TAG, " sleep current thread: "+Thread.currentThread().getName());
                        try {
                            /*模拟 拉取数据*/
                            Thread.currentThread().sleep(2000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext("hhe");
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                Log.i(TAG, "current thread: "+Thread.currentThread().getName());
                                mRefreshListView.refreshComplete();
                            }
                        });

            }
        });

        mRefreshListView.setLoadListener(new RefreshListView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            /*模拟加载数据*/
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext("s");
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                            mRefreshListView.footerComplete();
                            }
                        });
            }
        });

    }


}
