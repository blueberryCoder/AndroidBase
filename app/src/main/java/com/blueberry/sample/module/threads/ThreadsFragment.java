package com.blueberry.sample.module.threads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseFragment;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/5/20.
 */
public class ThreadsFragment extends BaseFragment {

    @BindView(R.id.thread_webview)
    WebView mWebView;

    public static BaseFragment newInstance() {

        Bundle args = new Bundle();

        ThreadsFragment fragment = new ThreadsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thread, null);
        ButterKnife.bind(this, view);
        setupWebView("http://blog.csdn.net/a992036795/article/details/51362487");
        return view;
    }

    private void setupWebView(String url) {
        /*手机访问这个网址,会重定向*/
        mWebView.loadUrl(url);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                /*重写此方法点击网页里面的链接还是当前的webvidw里面跳转*/
                Logger.d("url is %s ", url);
                view.loadUrl(url);
                return true;
            }
        });

    }


}
