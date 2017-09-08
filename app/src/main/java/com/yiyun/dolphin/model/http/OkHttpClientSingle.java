package com.yiyun.dolphin.model.http;

import com.yiyun.dolphin.BuildConfig;
import com.yiyun.dolphin.Utils.FileUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by xiangyun_liu on 2017/9/6.
 * <p>
 * 配置OkHttpClient
 */

public class OkHttpClientSingle {
    private static OkHttpClient mOkHttpClient;
    private final static int CONNECT_TIME_OUT = 30;
    private final static int READ_TIME_OUT = 30;
    private final static int WRITE_TIME_OUT = 30;
    private final static String CACHE_FILE_NAME = "okHttp_cache";
    private final static int CACHE_FILE_MAX_SIZE = 100 * 1024 * 1024;

    private OkHttpClientSingle() {

    }

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (OkHttpClientSingle.class) {
                if (mOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
                    builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
                    builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
                    //错误重连
                    builder.retryOnConnectionFailure(true);
                    //Url添加公共参数
                    builder.addInterceptor(new AddPublicParameterInterceptor());
                    //debug模式时打印请求日志信息
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(httpLoggingInterceptor);
                    }
                    //添加缓存，设置缓存文件目录，及大小
                    Cache cache = new Cache(new File(FileUtil.getCacheDir(), CACHE_FILE_NAME), CACHE_FILE_MAX_SIZE);
                    builder.cache(cache);
                    //添加缓存拦截器用来配置缓存策略
                    builder.addInterceptor(new CacheInterceptor());
                    builder.addNetworkInterceptor(new CacheInterceptor());
                    mOkHttpClient = builder.build();
                }
            }
        }
        return mOkHttpClient;
    }
}
