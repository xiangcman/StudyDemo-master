package com.single.studydemo.android_databinding.event;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by xiangcheng on 18/2/2.
 * 演示databing
 */

public class DataBingEvent {

    public void runTask(Task task) {
        task.run();
    }

    public void runTask(View view, Task task) {
        Toast.makeText(view.getContext(), "执行了传view的runTask", Toast.LENGTH_SHORT).show();
        task.run();
    }

    public void runTask(View view, Task task, boolean isRun) {
        Toast.makeText(view.getContext(), "执行了传view的runTask", Toast.LENGTH_SHORT).show();
        if (!isRun) {
            task.run();
        }
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            Log.d("TASK", "怎么就执行了Task了呢");
        }
    }
}
