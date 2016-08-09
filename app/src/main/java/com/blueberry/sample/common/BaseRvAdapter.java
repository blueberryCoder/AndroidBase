package com.blueberry.sample.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by blueberry on 2016/8/8.
 */
public abstract class BaseRvAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List<T> data;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private OnRvItemClickListener onRvItemClickListener;

    public BaseRvAdapter(Context context, List<? extends T> data) {
        this.data = (List<T>) data;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        setupClickListener(holder, position);
        onBindHolder(holder,position);
    }

    public abstract void onBindHolder(VH holder, int position);

    private void setupClickListener(VH holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRvItemClickListener != null) {
                    onRvItemClickListener.onItemClick(position, v);
                }
            }
        });
    }

    public void setOnRvItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        this.onRvItemClickListener = onRvItemClickListener;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public interface OnRvItemClickListener {
        void onItemClick(int pos, View view);
    }
}
