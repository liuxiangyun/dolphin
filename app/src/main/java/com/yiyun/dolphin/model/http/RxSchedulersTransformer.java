package com.yiyun.dolphin.model.http;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangyun_liu on 2017/9/12.
 * <p>
 * 线程切换
 */

public class RxSchedulersTransformer {
    /**
     * observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
     */
    public static final ObservableTransformer OBSERVABLE_OI_TO_MAIN = observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    /**
     * observable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
     */
    public static final ObservableTransformer OBSERVABLE_CPU_TO_MAIN = observable -> observable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    /**
     * flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
     */
    public static final FlowableTransformer FLOWABLE_OI_TO_MAIN = flowable -> flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    /**
     * flowable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
     */
    public static final FlowableTransformer FLOWABLE_CPU_TO_MAIN = flowable -> flowable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
}
