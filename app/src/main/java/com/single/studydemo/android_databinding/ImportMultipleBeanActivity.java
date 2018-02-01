package com.single.studydemo.android_databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.single.studydemo.R;
import com.single.studydemo.android_databinding.bean.User;
import com.single.studydemo.databinding.ActivityImportMultipleBeanBinding;

/**
 * Created by xiangcheng on 18/2/1.
 * 多个bean同名使用import中alias属性指定别名
 */

public class ImportMultipleBeanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里的ViewDataBinding泛型会根据布局自动生成一个"布局的名字+Binding"
        ActivityImportMultipleBeanBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_import_multiple_bean);
        User user = new User("张三", "李四");

        com.single.studydemo.android_databinding.bean.multiple.User otherUser = new com.single.studydemo.android_databinding.bean.multiple.User("zs", "ls");
        //setUser方法会根据布局里面variable的name属性生成
        viewDataBinding.setOriginalUser(user);
        viewDataBinding.setUser(otherUser);
    }
}
