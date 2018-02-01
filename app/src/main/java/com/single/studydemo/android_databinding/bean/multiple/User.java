package com.single.studydemo.android_databinding.bean.multiple;

/**
 * Created by xiangcheng on 18/2/1.
 */

public class User {
    public String firstName;
    public String lastName;
    //用来测试databing动态属性用法
    public boolean isStutent;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, boolean isStutent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isStutent = isStutent;
    }
}
