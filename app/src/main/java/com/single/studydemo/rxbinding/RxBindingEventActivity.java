package com.single.studydemo.rxbinding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerViewAdapter;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxProgressBar;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.single.studydemo.R;
import com.single.studydemo.rxandroid.bean.IoBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by xiangcheng on 18/2/8.
 */

public class RxBindingEventActivity extends AppCompatActivity {
    private static final String TAG = RxBindingEventActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbinding_event);
        View rxbindingBtn = findViewById(R.id.rx_binding_btn);
        EditText rxBindingEdit = findViewById(R.id.rx_binding_edit);
        ProgressBar progressBar = findViewById(R.id.progress);
        RecyclerView recyclerView = findViewById(R.id.show_datas);
        //RXBinding事件响应包装处理
        //RxView.clicks(rxbindingBtn)给我们创建的是被观察者，call里面执行的是我们观察者收到的回调

        final Action1<? super Integer> progress = RxProgressBar.progress(progressBar);
        RxView.clicks(rxbindingBtn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                progress.call(100);
                Toast.makeText(RxBindingEventActivity.this, "to do", Toast.LENGTH_SHORT).show();
            }
        });
        RxView.globalLayouts(rxbindingBtn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Log.d(TAG, "globalLayouts");
            }
        });
        RxTextView.textChanges(rxBindingEdit).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                Log.d(TAG, "textChange:" + charSequence);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShowDatasAdapter showDatasAdapter = new ShowDatasAdapter(getListDatas());
        recyclerView.setAdapter(showDatasAdapter);
        RxRecyclerView.scrollStateChanges(recyclerView).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d(TAG, "SCROLL_STATE_IDLE");
                } else if (integer == RecyclerView.SCROLL_STATE_DRAGGING) {
                    Log.d(TAG, "SCROLL_STATE_DRAGGING");
                } else if (integer == RecyclerView.SCROLL_STATE_SETTLING) {
                    Log.d(TAG, "SCROLL_STATE_SETTLING");
                }
            }
        });

        RxRecyclerViewAdapter.dataChanges(showDatasAdapter).subscribe(new Action1<ShowDatasAdapter>() {
            @Override
            public void call(ShowDatasAdapter showDatasAdapter) {
                Log.d(TAG, "count:" + showDatasAdapter.getItemCount());
            }
        });

    }

    public class ShowDatasAdapter extends RecyclerView.Adapter {
        private List<IoBean> ioBeans;

        public ShowDatasAdapter(List<IoBean> ioBeans) {
            this.ioBeans = ioBeans;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(RxBindingEventActivity.this, R.layout.item_rx_io, null));
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

    private List<IoBean> getListDatas() {
        List<IoBean> ioBeans = new ArrayList<>();
        //这里只是模拟网络下获取数据
        ioBeans.add(new IoBean("向成1", "向成2"));
        ioBeans.add(new IoBean("向成3", "向成4"));
        ioBeans.add(new IoBean("向成5", "向成6"));
        ioBeans.add(new IoBean("向成7", "向成8"));
        ioBeans.add(new IoBean("向成9", "向成10"));
        return ioBeans;
    }
}
