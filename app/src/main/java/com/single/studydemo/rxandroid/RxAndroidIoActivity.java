package com.single.studydemo.rxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.single.studydemo.R;
import com.single.studydemo.rxandroid.bean.IoBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangcheng on 18/2/7.
 * rxandroid线程调度事例代码
 */

public class RxAndroidIoActivity extends AppCompatActivity {
    private static final String TAG = RxAndroidIoActivity.class.getSimpleName();
    private EditText input;
    private Button enter;
    private ProgressBar progressBar;
    private RecyclerView showDatas;
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid_io);
        showDatas = findViewById(R.id.show_data);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.enter);
        progressBar = findViewById(R.id.progress);
        showDatas.setLayoutManager(new LinearLayoutManager(this));
        Observable<String> buttonObservable = createButtonObservable();
        //该局表示此处的被观察者发生在主线程
        disposable = buttonObservable.observeOn(AndroidSchedulers.mainThread()).
                //doonNext方法表示线程调度后，需要做的事情,此处需要传入Consumer对象
                        doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //此处可以做一些准备工作啥的
                        showDatas.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }).
                        observeOn(Schedulers.io()).
                //map方法是提供一种数据转换成另外一种数据结构，上面已经说了该方法发生在io线程
                //规定了传入的参数和返回的数据类型
                        map(new Function<String, List<IoBean>>() {
                    @Override
                    public List<IoBean> apply(String s) throws Exception {
                        return getListDatas(s);
                    }
                }).
                //数据获取后，立即切换到主线程中
                        observeOn(AndroidSchedulers.mainThread()).
                //Consumer接口只是简单的获取到被观察者的反馈回来的内容
                        subscribe(new Consumer<List<IoBean>>() {
                    @Override
                    public void accept(List<IoBean> ioBeans) throws Exception {
                        //这样做的好处就是以后要处理的逻辑就是该处了
                        showDatas.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        showDatas.setAdapter(new ShowDatasAdapter(ioBeans));
                    }
                });

    }

    //解除订阅
    @Override
    protected void onStop() {
        super.onStop();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private List<IoBean> getListDatas(String source) {
        List<IoBean> ioBeans = new ArrayList<>();
        try {
            //这里只是模拟网络下获取数据
            Thread.sleep(2000);
            ioBeans.add(new IoBean("向成1", "向成2"));
            ioBeans.add(new IoBean("向成3", "向成4"));
            ioBeans.add(new IoBean("向成5", "向成6"));
            ioBeans.add(new IoBean("向成7", "向成8"));
            ioBeans.add(new IoBean("向成9", "向成10"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ioBeans;
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

    private class ShowDatasAdapter extends RecyclerView.Adapter {
        private List<IoBean> ioBeans;

        public ShowDatasAdapter(List<IoBean> ioBeans) {
            this.ioBeans = ioBeans;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(RxAndroidIoActivity.this, R.layout.item_rx_io, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyHolder myHolder = ((MyHolder) holder);
            myHolder.firstName.setText(ioBeans.get(position).firstName);
            myHolder.lastName.setText(ioBeans.get(position).lastName);
        }

        @Override
        public int getItemCount() {
            return ioBeans.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            private TextView firstName;
            private TextView lastName;

            public MyHolder(View itemView) {
                super(itemView);
                firstName = itemView.findViewById(R.id.fist_name_tv);
                lastName = itemView.findViewById(R.id.last_name_tv);
            }
        }
    }
}
