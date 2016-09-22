package com.blueberry.sample.module.data_binding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blueberry.sample.R;

import java.util.List;

/**
 * Created by blueberry on 2016/9/21.
 */

public class UserAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private static final int ITEM_VIEW_TYPE_ADULT = 1;
    private static final int ITEM_VIEW_TYPE_CHILD = 2;

    private LayoutInflater mLayoutInflater;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        mLayoutInflater = LayoutInflater.from(context);
        this.users = users;
    }

    @Override
    public int getItemViewType(int position) {
        if (users.get(position).isAdult()) {
            return ITEM_VIEW_TYPE_ADULT;
        } else {
            return ITEM_VIEW_TYPE_CHILD;
        }
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_ADULT) {
            return new BindingViewHolder(
                    DataBindingUtil.inflate(mLayoutInflater,
                            R.layout.item_fg_rv_adult, parent, false));
        } else {
            return new BindingViewHolder(
                    DataBindingUtil.inflate(mLayoutInflater,R.layout.item_fg_rv,parent,false)
            );
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(com.blueberry.sample.BR.user,users.get(position));
        holder.getBinding().executePendingBindings();
    }
    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }
}
