package com.single.studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.single.studydemo.android_databinding.BeanAttributeActivity;
import com.single.studydemo.android_databinding.DataBingCustomPropertyActivity;
import com.single.studydemo.android_databinding.DataBingEventActivity;
import com.single.studydemo.android_databinding.DataBingObservableActivity;
import com.single.studydemo.android_databinding.DataBingRecyclerViewActivity;
import com.single.studydemo.android_databinding.DataBingViewStubActivity;
import com.single.studydemo.android_databinding.ImportMultipleBeanActivity;
import com.single.studydemo.android_databinding.SingleBeanActivity;
import com.single.studydemo.imageloader.ImageLoaderListActivity;
import com.single.studydemo.rxandroid.RxAndroidEventActivity;

public class LanuchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.imageloader_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, ImageLoaderListActivity.class));
            }
        });
        findViewById(R.id.databing_single_bean_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, SingleBeanActivity.class));
            }
        });
        findViewById(R.id.databing_multiple_bean_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, ImportMultipleBeanActivity.class));
            }
        });
        findViewById(R.id.databing_bean_attribute_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, BeanAttributeActivity.class));
            }
        });
        findViewById(R.id.databing_observable_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, DataBingObservableActivity.class));
            }
        });
        findViewById(R.id.databing_event_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, DataBingEventActivity.class));
            }
        });
        findViewById(R.id.databing_rv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, DataBingRecyclerViewActivity.class));
            }
        });
        findViewById(R.id.databing_custom_property_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, DataBingCustomPropertyActivity.class));
            }
        });
        findViewById(R.id.databing_view_stub_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, DataBingViewStubActivity.class));
            }
        });
        findViewById(R.id.rx_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanuchActivity.this, RxAndroidEventActivity.class));
            }
        });
    }
}
