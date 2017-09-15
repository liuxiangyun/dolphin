package com.yiyun.dolphin.ui.view;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by xiangyun_liu on 2017/9/14.
 * <p>
 * 所有View层操作接口的父接口
 */

public interface BaseView {
    /**
     * 用来在Presenter中将Observable和activity或者fragment的生命周期进行绑定，防止内存泄漏
     *
     * @return
     */
    LifecycleTransformer bindLifecycle();
}
