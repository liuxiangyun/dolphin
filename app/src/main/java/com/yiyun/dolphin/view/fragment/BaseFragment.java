package com.yiyun.dolphin.view.fragment;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yiyun.dolphin.DolphinApplication;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * 利用Rxlifecycle 自动取消订阅，需要继承至RxFragment
 */

public abstract class BaseFragment extends RxFragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        //监控Fragment的内存泄漏
        DolphinApplication.getRefWatcher().watch(this);
    }
}
