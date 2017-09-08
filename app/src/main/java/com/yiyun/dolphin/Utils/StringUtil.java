package com.yiyun.dolphin.Utils;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * String 操作工具类
 */

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }
}
