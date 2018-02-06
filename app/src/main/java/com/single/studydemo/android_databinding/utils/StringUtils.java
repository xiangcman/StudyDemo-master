package com.single.studydemo.android_databinding.utils;

/**
 * Created by xiangcheng on 18/2/2.
 */

public class StringUtils {
    //为了演示databinding使用自定义类
    public static String capitalize(String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
}
