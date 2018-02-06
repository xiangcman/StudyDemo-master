package com.single.studydemo.android_databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.single.studydemo.R;
import com.single.studydemo.android_databinding.event.DataBingEvent;
import com.single.studydemo.android_databinding.event.EventHandler;
import com.single.studydemo.databinding.ActivityDatabingEventBinding;

/**
 * Created by xiangcheng on 18/2/2.
 */

public class DataBingEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabingEventBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databing_event);
        viewDataBinding.setEventHandler(new EventHandler());
        viewDataBinding.setEvent(new DataBingEvent());
        viewDataBinding.setTask(new DataBingEvent.Task());
    }
}
