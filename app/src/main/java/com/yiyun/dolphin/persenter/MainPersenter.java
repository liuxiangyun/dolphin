package com.yiyun.dolphin.persenter;

import android.view.View;

import com.yiyun.dolphin.Utils.ToastUtil;
import com.yiyun.dolphin.model.entry.UserEntry;

/**
 * Created by xiangyun_liu on 2017/9/13.
 */

public class MainPersenter {
    public void onClick(View v) {
        ToastUtil.toastShort("你点击了我");
    }

    public void check(UserEntry user) {
        ToastUtil.toastShort("my name is " + user.getName());
    }
}
