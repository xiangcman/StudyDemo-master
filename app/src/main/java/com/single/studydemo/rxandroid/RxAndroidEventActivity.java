package com.single.studydemo.rxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.single.studydemo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by xiangcheng on 18/2/7.
 */

public class RxAndroidEventActivity extends AppCompatActivity {
    private static final String TAG = RxAndroidEventActivity.class.getSimpleName();
    private EditText input;
    private Button enter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.enter);
        Observable<String> buttonObservable = createButtonObservable();
        //Consumer接口只是简单的获取到被观察者的反馈回来的内容
        buttonObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //这样做的好处就是以后要处理的逻辑就是该处了
                Log.d(TAG, "input text:" + s);
            }
        });

        Observable<String> editTextObservable = createEditTextObservable();
//        editTextObservable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                //这样做的好处就是以后要处理editText内容变化的时候，逻辑都在此处了
//                Log.d(TAG, "input text:" + s);
//            }
//        });
        editTextObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "input text:" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        //merge操作符可以实现多个被观察者让一个观察者接收到发射的消息
        Observable<String> merge = Observable.merge(buttonObservable, editTextObservable);
    }

    //rxjava实现按钮的点击监听
    //创建一个被观察者，泛型用来指定咋们被观察者返回的数据类型
    private Observable<String> createButtonObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e.onNext(input.getText().toString());
                    }
                });
                //为了防止内存泄漏，这里在被观察者取消的时候，把相应的操作也还原
                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        // 7
                        enter.setOnClickListener(null);
                    }
                });
            }
        });
    }

    //rxjava实现editText内容变化的监听
    //创建一个被观察者，泛型用来指定咋们被观察者返回的数据类型
    private Observable<String> createEditTextObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        e.onNext(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                //为了防止内存泄漏，这里在被观察者取消的时候，把相应的操作也还原
                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        // 7
                        input.addTextChangedListener(null);
                    }
                });
            }
            //这里的filter操作符表示过滤的条件，test方法中返回true，被观察者才会去发射给观察者
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s.length() > 2;
//                return false;
            }
            //debounce操作符指的是间隔多少时间，被观察者会发射给观察者，一般适用频繁操作的场景
        }).debounce(1000, TimeUnit.MILLISECONDS);
    }
}
