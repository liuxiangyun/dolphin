package com.yiyun.dolphin.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.yiyun.dolphin.R;
import com.yiyun.dolphin.databinding.ActivityMainBinding;
import com.yiyun.dolphin.model.entry.UserEntry;

import java.util.concurrent.TimeUnit;


/**
 * Created by xiangyun_liu on 2017/9/4.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private int age = 18;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserEntry userEntry = new UserEntry("我是小白", age);
        mViewDataBinding.setUserEntry(userEntry);

        RxView.clicks(mViewDataBinding.fabSendRequest)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .subscribe(onNext -> userEntry.setAge(++age));

        RxView.longClicks(mViewDataBinding.fabSendRequest)
                .compose(bindToLifecycle())
                .subscribe(onNext -> startActivity(new Intent(MainActivity.this, FirstActivity.class)));
    }


    @Override
    int getResLayoutId() {
        return R.layout.activity_main;
    }
}
