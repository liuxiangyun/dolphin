package com.yiyun.dolphin.model.http;

import com.yiyun.dolphin.R;
import com.yiyun.dolphin.utils.NetworkUtil;
import com.yiyun.dolphin.utils.StringUtil;
import com.yiyun.dolphin.utils.ToastUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * 配置缓存策略（如何使用缓存）
 * 1.有网络的情况下优先使用接口单独配置的缓存策略（Retrofit可用@Headers注解配置），如果该请求没有配置
 * 那么使用统一的缓存策略
 * 2.没有网络的情况下强制使用缓存数据
 */

public class CacheInterceptor implements Interceptor {
    // 有网络的情况下，从从网络获取数据的那一次请求开始计时，在一定的时间内不去重新获取网络数据，而是使用缓存数据，单位：S
    private static final int MAX_AGE = 0;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        //获取请求的缓存配置（Retrofit请求可用@Headers注解配置）
        String cacheControl = request.cacheControl().toString();
        //有网络的情况下从网络获取数据
        if (NetworkUtil.isNetworkConnected()) {
            if (StringUtil.isEmpty(cacheControl)) {
                //统一的缓存策略，凡是没有配置过的接口都使用此配置
                cacheControl = "public, max-age=" + MAX_AGE;
            }
            response = chain.proceed(request);
        } else {
            ToastUtil.toastLongCenter(R.string.network_error);
            //无网络时强制使用缓存数据
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            response = chain.proceed(request);
            int maxStale = Integer.MAX_VALUE;
            cacheControl = "public, only-if-cached, max-stale=" + maxStale;
        }
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl)
                .build();
    }
}
