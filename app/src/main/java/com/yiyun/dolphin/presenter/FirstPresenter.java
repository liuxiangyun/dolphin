package com.yiyun.dolphin.presenter;

import com.yiyun.dolphin.model.http.ApiClient;
import com.yiyun.dolphin.model.http.RxTransformer;
import com.yiyun.dolphin.ui.view.FirstView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiangyun_liu on 2017/9/15.
 */

public class FirstPresenter extends BasePresenter<FirstView> {
    public void refresh() {
        ApiClient.getApiService().refresh()
                .compose(RxTransformer.SCHEDULERS_OBSERVABLE_OI_TO_MAIN)
                .compose(getView().bindLifecycle())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        getView().refreshResult(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().refreshResult(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
