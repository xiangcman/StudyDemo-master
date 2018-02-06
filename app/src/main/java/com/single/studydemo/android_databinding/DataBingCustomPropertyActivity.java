package com.single.studydemo.android_databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.single.studydemo.R;
import com.single.studydemo.databinding.ActivityDatabingCustomPropertyBinding;

/**
 * Created by xiangcheng on 18/2/6.
 */

public class DataBingCustomPropertyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabingCustomPropertyBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databing_custom_property);
        viewDataBinding.setUrl("https://www.baidu.com/img/bd_logo1.png");
    }
}
