package com.single.studydemo.android_databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.single.studydemo.R;
import com.single.studydemo.android_databinding.bean.User;
import com.single.studydemo.databinding.ActivitySingleBeanBinding;

/**
 * Created by xiangcheng on 18/2/1.
 * databing单个bean
 */

public class SingleBeanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里的ViewDataBinding泛型会根据布局自动生成一个"布局的名字+Binding"
        ActivitySingleBeanBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_bean);
        User user = new User("张三", "李四");
        //setUser方法会根据布局里面variable的name属性生成
        viewDataBinding.setUser(user);
    }
}
