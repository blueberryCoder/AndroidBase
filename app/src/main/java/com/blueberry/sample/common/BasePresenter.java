package com.blueberry.sample.common;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by blueberry on 2016/5/5.
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {

    /**
     * 软引用
     */
    protected Reference<V> viewHolder;

    /**
     * 绑定view
     * @param baseView
     */
    @Override
    public void attachView(V baseView) {
        viewHolder = new WeakReference<V>(baseView);
    }

    /**
     * 检查是否绑定
     * @return
     */
    @Override
    public boolean isAttached() {
        return viewHolder != null && viewHolder.get() != null;
    }

    /**
     * 获得view
     * @return  view 有可能为null
     */
    @Override
    public V getView() {
        if(!isAttached()) return null ;
        return viewHolder.get();
    }

    /**
     * 解绑，释放引用
     */
    @Override
    public void detachView() {
        if (viewHolder != null) {
            viewHolder.clear();
            viewHolder = null;
        }
    }
}
