package com.blueberry.sample.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blueberry.sample.R;
import com.blueberry.sample.module.home.data.HomeData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blueberry on 2016/5/6.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private LayoutInflater mLayoutInflater ;

    private List<HomeData>  mDatas ;

    private OnItemClickListener mOnItemClickListener ;

    /**
     * 监听器
     */
    interface  OnItemClickListener{
        /**
         * 回调
         * @param pos  位置
         * @param id   数据 id
         */
       void onItemClick(int pos,int id) ;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 构造方法
     * @param context
     * @param datas   数据
     */
    public HomeAdapter (Context context, List<HomeData> datas){
        mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = datas ;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_list_home,null);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(position, mDatas.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class HomeViewHolder extends  RecyclerView.ViewHolder{

        @BindView(R.id.image_view)ImageView  iv ;
        @BindView(R.id.tv_content)TextView tv ;
        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
