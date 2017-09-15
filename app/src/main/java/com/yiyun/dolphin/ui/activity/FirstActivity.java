package com.yiyun.dolphin.ui.activity;

import android.os.Bundle;

import com.jakewharton.rxbinding2.view.RxView;
import com.yiyun.dolphin.R;
import com.yiyun.dolphin.databinding.ActivityFirstBinding;
import com.yiyun.dolphin.model.http.RxTransformer;
import com.yiyun.dolphin.presenter.FirstPresenter;
import com.yiyun.dolphin.ui.view.FirstView;
import com.yiyun.dolphin.utils.ToastUtil;

public class FirstActivity extends BaseActivity<FirstView, FirstPresenter, ActivityFirstBinding> implements FirstView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxView.clicks(getBinding().fabClick)
                .compose(RxTransformer.CLICK_THROTTLE)
                .compose(bindToLifecycle())
                .subscribe(onNext -> refresh());
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_first;
    }

    @Override
    protected FirstView createView() {
        return this;
    }

    @Override
    protected FirstPresenter createPresenter() {
        return new FirstPresenter();
    }

    @Override
    public void refresh() {
        getPresenter().refresh();
    }

    @Override
    public void refreshResult(boolean isSuccess) {
        ToastUtil.toastShort(isSuccess ? "success" : "failure");
    }
}
