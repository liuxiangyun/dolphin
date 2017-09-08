package com.yiyun.dolphin;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 程序所有Activity的控制器
 */

public class ActivityController {
    private static List<Activity> activities = new ArrayList<>();

    public static void add(Activity activity) {
        if (activity != null) {
            activities.add(activity);
        }
    }

    public static void remove(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
        }
    }

    public static void clear() {
        activities.clear();
    }
}
