package com.yiyun.dolphin.view.fragment;

import android.support.v4.app.Fragment;

import com.yiyun.dolphin.DolphinApplication;

/**
 * Created by xiangyun_liu on 2017/9/7.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        //监控Fragment的内存泄漏
        DolphinApplication.getRefWatcher().watch(this);
    }
}
