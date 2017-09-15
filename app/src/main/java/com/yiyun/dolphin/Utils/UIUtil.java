package com.yiyun.dolphin.utils;

import android.content.res.Resources;

import com.yiyun.dolphin.DolphinApplication;

/**
 * Created by xiangyun_liu on 2017/9/8.
 * <p>
 * UI操作相关工具类
 */

public class UIUtil {
    /**
     * 获取resources对象
     *
     * @return
     */
    public static Resources getResources() {
        return DolphinApplication.getInstance().getResources();
    }

    /**
     * 获取字符串资源
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * post runnable到主线程
     *
     * @param runnable
     */
    public static void postMainThread(Runnable runnable) {
        DolphinApplication.getMainThreadHandler().post(runnable);
    }

    /**
     * 延迟post runnable到主线程
     *
     * @param runnable
     * @param delayMillis
     */
    public static void postMainThreadDelayed(Runnable runnable, long delayMillis) {
        DolphinApplication.getMainThreadHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 当前线程是否是主线程
     */
    public static boolean isRunMainThread() {
        return DolphinApplication.getMainThreadId() == Thread.currentThread().getId();
    }
}
