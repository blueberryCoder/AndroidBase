package com.blueberry.sample.module.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blueberry.sample.R;
import com.blueberry.sample.module.view.data.ViewBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/6/17.
 */
public class ViewRecycleViewAdapter extends RecyclerView.Adapter<ViewRecycleViewAdapter.ViewHolder> {

    private static final int TAG_POS_KEY = 213<<24;


    public int[] colors = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.GRAY};
    private List<ViewBean> datas;
    private List<Integer> heights;

    private LayoutInflater mInflate;

    private OnItemClickListener mOnItemClickListener;

    interface OnItemClickListener {
        void onItemClick(int pos, View view);
    }

    public ViewRecycleViewAdapter(Context context, List<ViewBean> datas) {
        this.mInflate = LayoutInflater.from(context);
        this.datas = datas;
        generateHeights();
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = mInflate.inflate(R.layout.item_view_fragments, parent, false);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.
                            onItemClick(v.getTag(TAG_POS_KEY) == null
                                    ? 0 : (int) v.getTag(TAG_POS_KEY), v);
                }
            }
        });
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(datas.get(position).getContent());
        holder.tv.setBackgroundColor(colors[position % colors.length]);
        ViewGroup.LayoutParams layoutParams = holder.tv.getLayoutParams();
        layoutParams.height = heights.get(position);
        holder.tv.setLayoutParams(layoutParams);
        holder.itemView.setTag(TAG_POS_KEY,position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public Object getItem( int pos){
        return datas.get(pos) ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_recycleView_item_tv)
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void generateHeights() {
        if (datas == null) return;
        heights = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            int height = new Random().nextInt(100) + 200;
            heights.add(height);
        }
    }

}
