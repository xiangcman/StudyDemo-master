### 用来学习一些流行的第三方的库

**android_databing**

- 单个bean绑定到控件的属性上
```
<data>
    <variable
        name="user"
        type="com.single.studydemo.android_databinding.bean.User" />
</data>
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{user.firstName}" />
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{user.lastName}" />
</LinearLayout>
```
见[SingleBeanActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/SingleBeanActivity.java)



- 多个bean重名问题
```
<data>
    <import
        alias="OriginalUser"
        type="com.single.studydemo.android_databinding.bean.User" />
    <import type="com.single.studydemo.android_databinding.bean.multiple.User" />
    <variable
        name="originalUser"
        type="OriginalUser" />
    <variable
        name="user"
        type="User" />
</data>
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{originalUser.firstName}" />
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{user.lastName}" />
</LinearLayout>
```
见[ImportMultipleBeanActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/ImportMultipleBeanActivity.java)



- 属性复杂赋值问题

见[BeanAttributeActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/BeanAttributeActivity.java)



- 事件传递问题

见[DataBingEventActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/DataBingEventActivity.java)



- view、data双向(单向)传递问题

见[DataBingObservableActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/DataBingObservableActivity.java)



- recyclerView中使用问题

见[DataBingRecyclerViewActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/DataBingRecyclerViewActivity.java)



- 自定义属性问题

见[DataBingCustomPropertyActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/DataBingCustomPropertyActivity.java)



- ViewStub在databing中使用问题

见[DataBingViewStubActivity](https://github.com/xiangcman/StudyDemo-master/blob/master/app/src/main/java/com/single/studydemo/android_databinding/DataBingViewStubActivity.java)
