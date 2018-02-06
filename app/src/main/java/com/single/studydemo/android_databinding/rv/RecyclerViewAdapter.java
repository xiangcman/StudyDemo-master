package com.single.studydemo.android_databinding.rv;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.single.studydemo.BR;
import com.single.studydemo.R;
import com.single.studydemo.android_databinding.bean.RecyclerViewItem;
import com.single.studydemo.databinding.AdapterRecyclerViewBinding;

import java.util.List;

/**
 * Created by xiangcheng on 18/2/2.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {

    private List<RecyclerViewItem> list;

    public RecyclerViewAdapter(List<RecyclerViewItem> list) {
        this.list = list;
    }

    @Override
    public RecyclerViewAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //同样会根据布局生成一个相应的binding
        AdapterRecyclerViewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.adapter_recycler_view, parent, false);
        //这里getRoot会返回布局的根view
        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.BindingHolder holder, int position) {
        //在绑定数据的时候是固定写法，就没什么好说的了
        holder.getBinding().setVariable(BR.item, list.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {

        //这里在holder里面，传入一个binding对象就ok了
        private AdapterRecyclerViewBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public AdapterRecyclerViewBinding getBinding() {
            return binding;
        }

        public void setBinding(AdapterRecyclerViewBinding binding) {
            this.binding = binding;
        }
    }
}
