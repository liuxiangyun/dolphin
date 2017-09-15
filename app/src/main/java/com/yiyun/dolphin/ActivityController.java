package com.yiyun.dolphin;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 程序中所有Activity的控制器
 */

public class ActivityController {
    private static List<Activity> activities = new ArrayList<>();

    /**
     * 添加activity
     * <p>
     * onCreate 中调用该方法
     *
     * @param activity
     */
    public static void add(Activity activity) {
        if (activity != null) {
            activities.add(activity);
        }
    }

    /**
     * 删除activity
     * <p>
     * onDestroy 中调用该方法
     *
     * @param activity
     */
    public static void remove(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
        }
    }

    /**
     * 清除并关闭所有的activity
     */
    public static void clear() {
        for (Activity activity : activities) {
            if (!activity.isDestroyed()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
