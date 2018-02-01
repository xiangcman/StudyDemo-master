package com.single.studydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.single.studydemo.android_databinding.BeanAttributeActivity;
import com.single.studydemo.android_databinding.ImportMultipleBeanActivity;
import com.single.studydemo.android_databinding.SingleBeanActivity;
import com.single.studydemo.imageloader.ImageLoaderListActivity;

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
    }
}
