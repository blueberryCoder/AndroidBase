package com.blueberry.sample.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by blueberry on 2016/6/17.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int number;

    public SpaceItemDecoration(int space, int number) {
        this.space = space;
        this.number = number;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;
        if (parent.getChildAdapterPosition(view) < number) {
            outRect.top = space;
        }
    }
}
