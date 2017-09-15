package com.yiyun.dolphin.model.http;

import com.yiyun.dolphin.BuildConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xiangyun_liu on 2017/9/7.
 * <p>
 * 给每个请求添加统一的公共参数
 */

public class AddPublicParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Map<String, String> publicParams = getPublicParams();
        switch (request.method()) {
            case "GET":
                //GET请求添加公共参数
                HttpUrl.Builder httpBuilder = request.url().newBuilder();
                for (Map.Entry<String, String> entry : publicParams.entrySet()) {
                    httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
                HttpUrl modifiedUrl = httpBuilder.build();
                request = request.newBuilder().url(modifiedUrl).build();
                break;
            case "POST":
                RequestBody body = request.body();
                //POST请求FormBody添加公共参数
                if (body != null && body instanceof FormBody) {
                    FormBody formBody = (FormBody) body;
                    Map<String, String> formParams = new HashMap<>();
                    for (int i = 0; i < formBody.size(); i++) {
                        formParams.put(formBody.name(i), formBody.value(i));
                    }
                    formParams.putAll(publicParams);
                    FormBody.Builder formBuilder = new FormBody.Builder();
                    for (Map.Entry<String, String> entry : formParams.entrySet()) {
                        formBuilder.add(entry.getKey(), entry.getValue());
                    }
                    request = request.newBuilder().method(request.method(), formBuilder.build()).build();

                    //POST请求MultipartBody添加公共参数
                } else if (body != null && body instanceof MultipartBody) {
                    MultipartBody multipartBody = (MultipartBody) body;
                    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    for (Map.Entry<String, String> entry : publicParams.entrySet()) {
                        multipartBuilder.addFormDataPart(entry.getKey(), entry.getValue());
                    }
                    List<MultipartBody.Part> parts = multipartBody.parts();
                    for (MultipartBody.Part part : parts) {
                        multipartBuilder.addPart(part);
                    }
                    request = request.newBuilder().post(multipartBuilder.build()).build();
                }
                break;
        }

        return chain.proceed(request);
    }

    /**
     * 需要添加的公共参数
     *
     * @return
     */
    private Map<String, String> getPublicParams() {
        Map<String, String> params = new HashMap<>();
        params.put("token", "");
        params.put("platform", "android");
        params.put("version", BuildConfig.VERSION_NAME);
        params.put("deviceId", "");
        //....
        return params;
    }
}
