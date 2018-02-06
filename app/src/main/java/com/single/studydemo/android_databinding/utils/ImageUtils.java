package com.single.studydemo.android_databinding.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by xiangcheng on 18/2/6.
 */

public class ImageUtils {

    /**
     * 加载图片
     * 无需手动调用此方法
     * 自定义属性来加载该方法
     *
     * @param view ImageView
     * @param url  图片地址
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
