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
        mApiService = RetrofitSingle.getRetrofit().create(ApiService.class);
    }

    /**
     * 获取ApiService对象
     *
     * @return
     */
    public static ApiService getApiService() {
        if (mApiService == null) {
            synchronized (ApiClient.class) {
                if (mApiService == null) {
                    new ApiClient();
                }
            }
        }
        return mApiService;
    }
}
