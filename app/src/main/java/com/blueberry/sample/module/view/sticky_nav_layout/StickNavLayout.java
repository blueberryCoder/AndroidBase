package com.blueberry.sample.module.view.sticky_nav_layout;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.blueberry.sample.R;

/**
 * Created by blueberry on 2016/10/13.
 */

public class StickNavLayout extends LinearLayout implements NestedScrollingParent {

    private static final String TAG = "StickNavLayout";

    private NestedScrollingParentHelper nestedScrollingParentHelper;

    private View mTop;
    private View mNav;
    private View mViewPager;
    private int mTopViewHeight;
    private OverScroller overScroller;

    public StickNavLayout(Context context) {
        this(context, null, 0);

    }

    public StickNavLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        setOrientation(VERTICAL);
        overScroller = new OverScroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = getMeasuredHeight() - mNav.getMeasuredHeight();
        setMeasuredDimension(widthMeasureSpec, mTop.getMeasuredHeight()
                + mNav.getMeasuredHeight() + mViewPager.getMeasuredHeight());
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mTopViewHeight = mTop.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.stick_nav_layout_header);
        mNav = findViewById(R.id.stick_nav_layout_indicator);
        View view = findViewById(R.id.stick_nav_layout_viewpager);
        if (!(view instanceof ViewPager)) {
            throw new RuntimeException("stick_nav_layout should be ViewPager.");
        }
        mViewPager = view;
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        nestedScrollingParentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.d(TAG, "onNestedPreScroll: ");
        boolean hiddenTop = getScrollY() < mTopViewHeight && dy > 0;
        boolean showTop = getScrollY() >= 0 && dy < 0
                && !ViewCompat.canScrollVertically(target, -1);
        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if(getScrollY() >= mTopViewHeight) return false;
        fling((int) velocityY) ;
        return true;
    }

    private void fling(int velocityY) {
        overScroller.fling(0,getScrollY(),0,velocityY,0,0,0,mTopViewHeight);
        invalidate();
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }

        if(y!=getScrollY()){
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if(overScroller.computeScrollOffset()){
            scrollTo(overScroller.getCurrX(),overScroller.getCurrY());
            invalidate();
        }
    }
}
