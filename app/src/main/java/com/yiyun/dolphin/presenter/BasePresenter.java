package com.yiyun.dolphin.presenter;

import com.yiyun.dolphin.ui.view.BaseView;

/**
 * Created by xiangyun_liu on 2017/9/14.
 * <p>
 * 所有Presenter的父类
 */

public class BasePresenter<V extends BaseView> {
    private V mView;

    /**
     * 绑定View
     *
     * @param view
     */
    public void attachView(V view) {
        mView = view;
    }

    /**
     * 解绑View
     */
    public void detachView() {
        mView = null;
    }

    /**
     * 获取View
     *
     * @return
     */
    public V getView() {
        return mView;
    }
}
