package com.yiyun.dolphin.model.http;

/**
 * Created by xiangyun_liu on 2017/9/5.
 * <p>
 * 网络请求客户端
 * <p>
 */

public class ApiClient {
    private static ApiService mApiService;

    private ApiClient() {

    }

    public static ApiService getApiService() {
        if (mApiService == null) {
            synchronized (ApiClient.class) {
                if (mApiService == null) {
                    mApiService = RetrofitSingle.getRetrofit().create(ApiService.class);
                }
            }
        }
        return mApiService;
    }
}
