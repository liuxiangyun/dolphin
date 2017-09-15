package com.yiyun.dolphin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.yiyun.dolphin.R;
import com.yiyun.dolphin.databinding.ActivityMainBinding;
import com.yiyun.dolphin.model.entry.UserEntry;
import com.yiyun.dolphin.model.http.RxTransformer;
import com.yiyun.dolphin.presenter.MainPresenter;
import com.yiyun.dolphin.ui.view.MainView;
import com.yiyun.dolphin.utils.ToastUtil;


/**
 * Created by xiangyun_liu on 2017/9/4.
 */

public class MainActivity extends BaseActivity<MainView, MainPresenter, ActivityMainBinding> implements MainView {
    private int age = 18;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserEntry userEntry = new UserEntry("小白", age);
        getBinding().setUserEntry(userEntry);

        RxView.clicks(getBinding().fabSendRequest)
                .compose(RxTransformer.CLICK_THROTTLE)
                .compose(bindToLifecycle())
                .subscribe(onNext -> startActivity(new Intent(MainActivity.this, FirstActivity.class)));
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainView createView() {
        return this;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void refresh() {
        getPresenter().refresh();
    }

    @Override
    public void refreshResult(boolean isSuccess) {
        ToastUtil.toastShort(isSuccess ? "刷新成功" : "刷新失败");
    }
}
