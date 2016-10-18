package com.blueberry.sample.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by blueberry on 2016/5/6.
 */
public class ListItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    private final static int DEFAULT_ORIENTATION = LinearLayoutManager.VERTICAL;

    private int mOrientation;

    public ListItemDecoration(Context context, int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL
                && orientation != LinearLayoutManager.VERTICAL) {
            this.mOrientation = DEFAULT_ORIENTATION;
        } else {
            this.mOrientation = orientation;
        }
        final TypedArray a = context.obtainStyledAttributes(new int[]{
                android.R.attr.listDivider
        });
        mDrawable = a.getDrawable( 0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(mOrientation ==LinearLayoutManager.HORIZONTAL){
            drawHorizontal(c,parent);
        }else{
            drawVertical(c,parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft() ;
        int right = parent.getWidth()-parent.getPaddingRight() ;
        int childCount = parent.getChildCount() ;
        for(int i=0;i<childCount;i++){
            View child =parent.getChildAt(i);
            RecyclerView.LayoutParams params   = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top  = child.getTop()+params.topMargin;
            int bottom = top+mDrawable.getIntrinsicHeight() ;
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(c);
        }

    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop() ;
        int bottom = parent.getHeight() -parent.getPaddingBottom();
        int childCount = parent.getChildCount() ;
        for(int i=0;i<childCount;i++){
            View child =parent.getChildAt(i) ;
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight()+params.rightMargin ;
            int right = left +mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
