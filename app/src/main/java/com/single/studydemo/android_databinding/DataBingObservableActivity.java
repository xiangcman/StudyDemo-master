package com.single.studydemo.android_databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayMap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.single.studydemo.R;
import com.single.studydemo.android_databinding.observable.ObservableFieldsUser;
import com.single.studydemo.android_databinding.observable.ObservableUser;
import com.single.studydemo.databinding.ActivityDatabingObservableBinding;

/**
 * Created by xiangcheng on 18/2/2.
 * 数据绑定到view上，达到自动刷新view效果
 */

public class DataBingObservableActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabingObservableBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databing_observable);
        final ObservableUser observableUser = new ObservableUser("张三", "李四");
        viewDataBinding.setUser(observableUser);
        viewDataBinding.setButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observableUser.setFirstName("zs");
                observableUser.setLastName("ls");
            }
        });

        //下面采用的方式是ObservableField
        final ObservableFieldsUser observableFieldsUser = new ObservableFieldsUser("张三", "李四");
        viewDataBinding.setObservableFieldsUser(observableFieldsUser);
        viewDataBinding.setObservableFieldbuttonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observableFieldsUser.firstName.set("你是张三");
                observableFieldsUser.lastName.set("你是李四");
            }
        });

        //下面采用databing里面自带的ObservableArrayMap实现view刷新
        final ObservableArrayMap<String, String> user = new ObservableArrayMap<>();
        user.put("firstName", "容华");
        user.put("lastName", "谢后");
        viewDataBinding.setObservableArrayMap(user);
        viewDataBinding.setObservableMapbuttonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.put("firstName", "空谷");
                user.put("lastName", "幽兰");
            }
        });

    }
}
