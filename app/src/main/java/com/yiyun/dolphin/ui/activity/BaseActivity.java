package com.yiyun.dolphin.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yiyun.dolphin.ActivityController;
import com.yiyun.dolphin.presenter.BasePresenter;
import com.yiyun.dolphin.ui.view.BaseView;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 利用Rxlifecycle 自动取消订阅，需要继承至RxAppCompatActivity
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter, B extends ViewDataBinding> extends RxAppCompatActivity {
    private V mView;
    private P mPresenter;
    private B mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMVP();
        //使用DataBindingUtil设置ContentView
        mBinding = DataBindingUtil.setContentView(this, getResLayoutId());
        ActivityController.add(this);
    }

    /**
     * 初始化mvp相关操作
     */
    private void initMVP() {
        if (mView == null) {
            mView = createView();
        }
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mView != null && mPresenter != null) {
            mPresenter.attachView(mView);
        } else {
            throw new IllegalArgumentException("view 或 presenter 不能为null");
        }
    }

    /**
     * 获取layout资源id
     *
     * @return
     */
    protected abstract int getResLayoutId();

    /**
     * 创建View
     *
     * @return
     */
    protected abstract V createView();

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 获取Presenter
     *
     * @return
     */
    protected P getPresenter() {
        return mPresenter;
    }

    /**
     * 获取ViewDataBinding对象
     *
     * @return
     */
    protected B getBinding() {
        return mBinding;
    }

    /**
     * 实现BaseView中的bindLifecycle
     *
     * @return
     */
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.remove(this);
        mPresenter.detachView();
    }
}
