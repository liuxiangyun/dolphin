package com.yiyun.dolphin.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yiyun.dolphin.R;
import com.yiyun.dolphin.databinding.ActivityFirstBinding;
import com.yiyun.dolphin.model.http.RxTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FirstActivity extends BaseActivity<ActivityFirstBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.create((ObservableOnSubscribe<String>) e -> {
            for (int i = 0; i < 120; i++) {
                Thread.sleep(1000);
                e.onNext("wo bu zhi dao " + i);
            }
        }).compose(RxTransformer.SCHEDULERS_OBSERVABLE_OI_TO_MAIN)
                //不关联Rxlifecycle,在事件没执行完的时候关闭activity，就会出现内存泄漏
                .compose(this.bindToLifecycle())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logger.d(s);
                        Toast.makeText(FirstActivity.this, s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    int getResLayoutId() {
        return R.layout.activity_first;
    }
}
