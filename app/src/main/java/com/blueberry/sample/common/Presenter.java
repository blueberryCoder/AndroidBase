package com.blueberry.sample.common;

/**
 * Created by blueberry on 2016/5/5.
 * mvp's presenter
 */
public interface Presenter<V extends BaseView> {

    /**
     * 绑定View
     * @param v
     */
    void attachView(V v);

    /**
     * 绑定结果
     * @return
     */
    boolean isAttached();

    /**
     * 获得view
     * @return
     */
    V getView() ;

    /**
     * 分离view
     */
    void detachView();

}
