package com.yiyun.dolphin.model.http;

import java.util.concurrent.TimeUnit;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangyun_liu on 2017/9/12.
 * <p>
 */

public class RxTransformer {
    /**
     * Observable io->main
     */
    public static final ObservableTransformer SCHEDULERS_OBSERVABLE_OI_TO_MAIN = observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    /**
     * Observable cup-main
     */
    public static final ObservableTransformer SCHEDULERS_OBSERVABLE_CPU_TO_MAIN = observable -> observable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    /**
     * Flowable io->main
     */
    public static final FlowableTransformer SCHEDULERS_FLOWABLE_OI_TO_MAIN = flowable -> flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    /**
     * Flowable cpu->main
     */
    public static final FlowableTransformer SCHEDULERS_FLOWABLE_CPU_TO_MAIN_ = flowable -> flowable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());

    /**
     * 点击事件300毫秒内只能触发一次
     */
    public static final ObservableTransformer CLICK_THROTTLE = observable -> observable.throttleFirst(300, TimeUnit.MILLISECONDS);
}
