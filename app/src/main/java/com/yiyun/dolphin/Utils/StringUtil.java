package com.yiyun.dolphin.utils;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * String 操作工具类
 */

public class StringUtil {
    /**
     * 字符串是否为 null 或 “”
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 字符串不为 null 和 “”
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 字符串处理，如果字符串为null，返回"",否则，返回它本身
     *
     * @param str
     * @return
     */
    public static String handle(String str) {
        return str == null ? "" : str;
    }
}
