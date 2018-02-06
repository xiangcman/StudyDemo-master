package com.single.studydemo.android_databinding.event;

import android.view.View;
import android.widget.Toast;

/**
 * Created by xiangcheng on 18/2/2.
 */

public class EventHandler {
    public void onClickFriend(View view) {
        Toast.makeText(view.getContext(), "onClickFriend", Toast.LENGTH_LONG).show();
    }
}
