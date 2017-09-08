package com.yiyun.dolphin.model.http;

import com.yiyun.dolphin.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * 给请求添加公共参数
 */

public class AddPublicParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl modifiedUrl = request.url().newBuilder()
                .addQueryParameter("Token", "")
                .addQueryParameter("platform", "android")
                .addQueryParameter("version", BuildConfig.VERSION_NAME)
                .build();
        request = request.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
