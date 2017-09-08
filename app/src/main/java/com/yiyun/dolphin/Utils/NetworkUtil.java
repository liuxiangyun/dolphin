package com.yiyun.dolphin.Utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yiyun.dolphin.DolphinApplication;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * 网络工具类
 */

public class NetworkUtil {

    /**
     * 判断是否连接网络
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) DolphinApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 获取使用的网络连接类型
     *
     * @return -1 无网络连接
     */
    public static int getConnectedType() {
        ConnectivityManager manager = (ConnectivityManager) DolphinApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    /**
     * 当前使用的网络连接类型是否是Wifi网络
     *
     * @return
     */
    public static boolean isWifiConnectedType() {
        return getConnectedType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 当前使用的网络连接类型是否是移动网络
     *
     * @return
     */
    public static boolean isMobileConnectedType() {
        return getConnectedType() == ConnectivityManager.TYPE_MOBILE;
    }
}
