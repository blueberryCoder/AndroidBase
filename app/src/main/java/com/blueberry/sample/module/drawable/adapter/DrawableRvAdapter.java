package com.blueberry.sample.module.drawable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blueberry.sample.common.BaseRvAdapter;

import java.util.List;

/**
 * Created by blueberry on 2016/8/8.
 */
public class DrawableRvAdapter extends BaseRvAdapter<String, DrawableRvAdapter.ViewHolder> {

    public DrawableRvAdapter(Context context, List<? extends String> data) {
        super(context, data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater
                .inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindHolder(ViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(data.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
