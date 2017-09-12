package com.yiyun.dolphin.model.http;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 网络请求接口管理
 */

public interface ApiService {
    String API_ADDRESS = "http://www.jianshu.com/";

    @Headers("Cache-Control:public, max-age=0")
    @GET("/p/464fa025229e")
    Call<String> getIndex();

    @GET("p/8818b98c44e2")
    Observable<String> register();

    @Headers("Cache-Control:no-cache,no-store")
    @GET("http://www.baidu.com")
    Observable<String> login();

}
