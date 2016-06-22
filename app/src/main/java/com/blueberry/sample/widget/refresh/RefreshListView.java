package com.blueberry.sample.widget.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by blueberry on 2016/6/21.
 */
public class RefreshListView extends RefreshLayoutBase<ListView>{

    private static final String TAG = "RefreshListView";

    private ListView listView ;
    private OnLoadListener loadListener ;

    private int lastY ;

    public RefreshListView(Context context) {
        super(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
        setContentView(listView);
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i(TAG, "onScrollStateChanged: ");
                if(currentStatus ==STATUS_IDLE && getScrollY() <= mInitScrollY && isBottom()
                        ){
                    showFooter();
                    if(null != loadListener){
                        loadListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i(TAG, "onScroll: ");
            }
        });
    }

    public OnLoadListener getLoadListener() {
        return loadListener;
    }

    public void setLoadListener(OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    @Override
    boolean isTop() {
        return listView.getFirstVisiblePosition()==0 && getScrollY() <=mHeadView.getMeasuredHeight();
    }

    @Override
    boolean isBottom() {
        return listView.getLastVisiblePosition() == listView.getAdapter().getCount()-1 ;
    }
    public interface OnLoadListener{
        void  onLoadMore();
    }
}
