package com.yiyun.dolphin;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by xiangyun_liu on 2017/9/4.
 */

public class DolphinApplication extends Application {
    /**
     * Logger日志全局Tag
     */
    private static final String TAG = "Logger";
    /**
     * ApplicationContext
     */
    private static DolphinApplication mDolphinApplication;
    /**
     * LeakCanary用来监听内存泄漏的对象
     */
    private static RefWatcher mRefWatcher;
    /**
     * 主线程id
     */
    private static long mMainThreadId;
    /**
     * 主线程handler
     */
    private static Handler mMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mDolphinApplication = this;
        mMainThreadId = Thread.currentThread().getId();
        mMainThreadHandler = new Handler();

        initLogger();
        initLeakCanary();
    }

    /**
     * 设置Logger
     */
    private void initLogger() {
        Settings setting = Logger.init(TAG);
        //非debug模式下不打印日志
        if (!BuildConfig.DEBUG) {
            setting.logLevel(LogLevel.NONE);
        }
    }

    /**
     * LeakCanary
     */
    private void initLeakCanary() {
        //LeakCanary.install()会返回一个预定义的 RefWatcher，同时也会启用一个ActivityRefWatcher，用于自动监控调用
        //Activity.onDestroy()之后泄露的activity，如需在Fragment中监控，可在Fragment.onDestroy中使用refWatcher.watch()
        mRefWatcher = LeakCanary.install(this);
    }

    public static Context getInstance() {
        return mDolphinApplication;
    }

    public static RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

}
