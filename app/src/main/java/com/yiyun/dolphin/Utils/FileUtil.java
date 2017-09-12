package com.yiyun.dolphin.Utils;

import android.os.Environment;

import com.yiyun.dolphin.DolphinApplication;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * 文件操作工具类
 */

public class FileUtil {
    /**
     * 当SD已挂载或者SD为内置存储不能卸载时将文件缓存在SD/Android/data/<application package>/cache目录下,
     * 否则缓存在data/data/<application package>/cache目录下
     *
     * @return
     */
    public static String getFitCacheDir() {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = DolphinApplication.getInstance().getExternalCacheDir().getPath();
        } else {
            cachePath = DolphinApplication.getInstance().getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 获取缓存目录
     * data/data/<application package>/cache
     *
     * @return
     */
    public static String getCacheDir() {
        return DolphinApplication.getInstance().getCacheDir().getPath();
    }
}
