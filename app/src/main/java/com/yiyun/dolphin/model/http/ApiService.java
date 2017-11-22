package com.yiyun.dolphin.model.http;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 网络请求接口管理
 */

public interface ApiService {
    String API_ADDRESS = "https://www.github.com/";

    @GET("https://kyfw.12306.cn/otn/")
    Observable<String> refresh();
}
