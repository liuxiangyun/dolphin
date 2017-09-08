package com.yiyun.dolphin.Utils;

import android.view.Gravity;
import android.widget.Toast;

import com.yiyun.dolphin.DolphinApplication;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * Toast 工具类
 */

public class ToastUtil {

    public static void toastShort(String text) {
        toast(text, -1, Toast.LENGTH_SHORT, false);
    }

    public static void toastShortCenter(String text) {
        toast(text, -1, Toast.LENGTH_SHORT, true);
    }

    public static void toastShort(int resId) {
        toast(null, resId, Toast.LENGTH_SHORT, false);
    }

    public static void toastShortCenter(int resId) {
        toast(null, resId, Toast.LENGTH_SHORT, true);
    }

    public static void toastLong(String text) {
        toast(text, -1, Toast.LENGTH_LONG, false);
    }

    public static void toastLongCenter(String text) {
        toast(text, -1, Toast.LENGTH_LONG, true);
    }

    public static void toastLong(int resId) {
        toast(null, resId, Toast.LENGTH_LONG, false);
    }

    public static void toastLongCenter(int resId) {
        toast(null, resId, Toast.LENGTH_LONG, true);
    }

    /**
     * 线程安全的toast
     *
     * @param text
     * @param resId
     * @param duration
     * @param isCenter
     */
    private static void toast(final String text, final int resId, final int duration, final boolean isCenter) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(DolphinApplication.getInstance(), text != null ? text : UIUtil.getString(resId), duration);
                if (isCenter) {
                    toast.setGravity(Gravity.CENTER, 0, 0);
                }
                toast.show();
            }
        };
        if (UIUtil.isRunMainThread()) {
            runnable.run();
        } else {
            UIUtil.postMainThread(runnable);
        }
    }
}
