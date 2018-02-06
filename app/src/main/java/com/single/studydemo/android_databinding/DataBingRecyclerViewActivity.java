package com.single.studydemo.android_databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.single.studydemo.R;
import com.single.studydemo.android_databinding.bean.RecyclerViewItem;
import com.single.studydemo.android_databinding.rv.RecyclerViewAdapter;
import com.single.studydemo.databinding.ActivityDatabingRecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangcheng on 18/2/2.
 */

public class DataBingRecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabingRecyclerviewBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databing_recyclerview);
        viewDataBinding.reView.setLayoutManager(new LinearLayoutManager(this));
        List<RecyclerViewItem> items = new ArrayList<>();
        items.add(new RecyclerViewItem("hello"));
        items.add(new RecyclerViewItem("hello"));
        items.add(new RecyclerViewItem("hello"));
        items.add(new RecyclerViewItem("hello"));
        items.add(new RecyclerViewItem("hello"));
        items.add(new RecyclerViewItem("hello"));
        items.add(new RecyclerViewItem("hello"));
        items.add(new RecyclerViewItem("hello"));
        viewDataBinding.reView.setAdapter(new RecyclerViewAdapter(items));
    }
}
