package com.yiyun.dolphin.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.yiyun.dolphin.R;
import com.yiyun.dolphin.Utils.NetworkUtil;
import com.yiyun.dolphin.databinding.ActivityMainBinding;
import com.yiyun.dolphin.model.http.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiangyun_liu on 2017/9/4.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate ....");

        mViewDataBinding.fabSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                netWorkCheck();

                ApiClient.getApiService().getIndex().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        mViewDataBinding.text1.setText(response.body());
                        Logger.d("send request");

                        startActivity(new Intent(MainActivity.this, FirstActivity.class));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mViewDataBinding.text1.setText(t.getMessage());
                    }
                });
            }
        });
    }

    private void netWorkCheck() {
        Logger.d("当前网络是否可用：" + NetworkUtil.isNetworkConnected()
                + "\n当前使用的网络类型：" + NetworkUtil.getConnectedType()
                + "\n当前使用的网络是否是WiFi网络" + NetworkUtil.isWifiConnectedType()
                + "\n当前使用的网络是否是mobile网络" + NetworkUtil.isMobileConnectedType());
    }

    @Override
    int getResLayoutId() {
        return R.layout.activity_main;
    }
}
