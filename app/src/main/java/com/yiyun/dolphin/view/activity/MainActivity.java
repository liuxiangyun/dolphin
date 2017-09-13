package com.yiyun.dolphin.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.yiyun.dolphin.R;
import com.yiyun.dolphin.Utils.ToastUtil;
import com.yiyun.dolphin.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;


/**
 * Created by xiangyun_liu on 2017/9/4.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxView.clicks(mViewDataBinding.fabSendRequest)
                .throttleFirst(5, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribe(onNext -> ToastUtil.toastShort("点击了按钮，我做的防抖处理，五秒钟之内只能点击一次"));
        RxView.longClicks(mViewDataBinding.fabSendRequest)
                .compose(bindToLifecycle())
                .subscribe(onNext -> startActivity(new Intent(MainActivity.this, FirstActivity.class)));
    }


    @Override
    int getResLayoutId() {
        return R.layout.activity_main;
    }
}
