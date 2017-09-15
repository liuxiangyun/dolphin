package com.yiyun.dolphin.ui.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yiyun.dolphin.DolphinApplication;
import com.yiyun.dolphin.presenter.BasePresenter;
import com.yiyun.dolphin.ui.view.BaseView;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * 利用Rxlifecycle 自动取消订阅，需要继承至RxFragment
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter, B extends ViewDataBinding> extends RxFragment {
    private V mView;
    private P mPresenter;
    private B mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMVP();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getResLayoutId(), container, false);
        return mBinding.getRoot();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        //LeakCanary监控Fragment的内存泄漏
        DolphinApplication.getRefWatcher().watch(this);
        mPresenter.detachView();
    }
}
