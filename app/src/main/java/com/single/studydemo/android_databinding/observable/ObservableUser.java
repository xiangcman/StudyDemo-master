package com.single.studydemo.android_databinding.observable;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.single.studydemo.BR;

/**
 * Created by xiangcheng on 18/2/2.
 * 算是比较繁琐的一种方式了
 */

public class ObservableUser extends BaseObservable {
    private String firstName;
    private String lastName;

    public ObservableUser() {
    }

    public ObservableUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
