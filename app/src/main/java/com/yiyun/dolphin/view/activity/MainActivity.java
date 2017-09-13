package com.yiyun.dolphin.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.yiyun.dolphin.R;
import com.yiyun.dolphin.Utils.ToastUtil;
import com.yiyun.dolphin.databinding.ActivityMainBinding;
import com.yiyun.dolphin.model.entry.UserEntry;
import com.yiyun.dolphin.model.http.RxTransformer;
import com.yiyun.dolphin.persenter.MainPersenter;


/**
 * Created by xiangyun_liu on 2017/9/4.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private int age = 18;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserEntry userEntry = new UserEntry("小白", age);
        mViewDataBinding.setUserEntry(userEntry);
        mViewDataBinding.setMainPersenter(new MainPersenter());

        RxView.clicks(mViewDataBinding.fabSendRequest)
                .compose(RxTransformer.CLICK_THROTTLE)
                .compose(bindToLifecycle())
                .subscribe(onNext -> userEntry.setAge(++age));

        RxView.longClicks(mViewDataBinding.fabSendRequest)
                .compose(bindToLifecycle())
                .subscribe(onNext -> startActivity(new Intent(MainActivity.this, FirstActivity.class)));

        RxView.clicks(mViewDataBinding.textUserName)
                .compose(RxTransformer.CLICK_THROTTLE)
                .compose(bindToLifecycle())
                .subscribe(onNext -> ToastUtil.toastShort("my name is " + mViewDataBinding.textUserName.getText()));
    }


    @Override
    int getResLayoutId() {
        return R.layout.activity_main;
    }
}
