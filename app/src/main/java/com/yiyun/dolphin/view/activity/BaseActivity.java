package com.yiyun.dolphin.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yiyun.dolphin.ActivityController;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 利用Rxlifecycle 自动取消订阅，需要继承至RxAppCompatActivity
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends RxAppCompatActivity {
    protected B mViewDataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用DataBindingUtil设置ContentView
        mViewDataBinding = DataBindingUtil.setContentView(this, getResLayoutId());
        ActivityController.add(this);
    }

    abstract int getResLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.remove(this);
    }
}
