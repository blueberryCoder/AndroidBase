package com.blueberry.sample.module.threads;

import com.blueberry.sample.common.BasePresenter;

/**
 * Created by blueberry on 2016/5/23.
 */
public class ThreadPresenter extends BasePresenter<IThreads>{
    /**
     * 显示网页
     */
    public void loadHtml(){
        getView().showHtml("http://blog.csdn.net/a992036795/article/details/51362487");
    }
}
