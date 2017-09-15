package com.yiyun.dolphin.model.http;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 网络请求接口管理
 */

public interface ApiService {
    String API_ADDRESS = "http://www.github.com/";

    @GET("/")
    Observable<String> refresh();
}
