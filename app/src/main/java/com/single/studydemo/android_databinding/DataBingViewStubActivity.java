package com.single.studydemo.android_databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.single.studydemo.R;
import com.single.studydemo.android_databinding.bean.multiple.User;
import com.single.studydemo.databinding.ActivityDatabingViewstubBinding;
import com.single.studydemo.databinding.IncludeLayoutBinding;

/**
 * Created by xiangcheng on 18/2/6.
 */

public class DataBingViewStubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabingViewstubBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databing_viewstub);
        viewDataBinding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                IncludeLayoutBinding viewStubBinding = DataBindingUtil.bind(inflated);
                User user = new User("容华", "谢后");
                viewStubBinding.setUser(user);
            }
        });
        if (!viewDataBinding.viewStub.isInflated()) {
            //viewDataBinding.viewStub获取的是ViewStubProxy
            viewDataBinding.viewStub.getViewStub().inflate();
        }
    }
}
