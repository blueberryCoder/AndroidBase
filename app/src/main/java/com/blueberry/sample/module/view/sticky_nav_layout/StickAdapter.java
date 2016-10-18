package com.blueberry.sample.module.view.sticky_nav_layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blueberry.sample.common.BaseRvAdapter;

import java.util.List;

/**
 * Created by blueberry on 2016/10/16.
 */

public class StickAdapter extends BaseRvAdapter<String,StickAdapter.ViewHolder> {

    public StickAdapter(Context context, List<? extends String> data) {
        super(context, data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv =new TextView(mContext);
        tv.setHeight(100);
        tv.setGravity(Gravity.CENTER);
        return new ViewHolder(tv);
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        (   (TextView)holder.itemView).setText("测试");


    }

    @Override
    public int getItemCount() {
        return 40;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
