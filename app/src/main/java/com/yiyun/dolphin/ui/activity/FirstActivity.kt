package com.yiyun.dolphin.ui.activity

import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import com.yiyun.dolphin.R
import com.yiyun.dolphin.databinding.ActivityFirstBinding
import com.yiyun.dolphin.model.http.RxTransformer
import com.yiyun.dolphin.presenter.FirstPresenter
import com.yiyun.dolphin.ui.view.FirstView
import com.yiyun.dolphin.utils.ToastUtil

/**
 * Created by xiangyun_liu on 2017/9/21.
 */
class FirstActivity : BaseActivity<FirstView, FirstPresenter, ActivityFirstBinding>(), FirstView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxView.clicks(binding.fabClick)
                .compose(RxTransformer.CLICK_THROTTLE)
                .compose(bindToLifecycle())
                .subscribe({ refresh() })
    }

    override fun getResLayoutId(): Int {
        return R.layout.activity_first
    }

    override fun createView(): FirstView {
        return this
    }

    override fun createPresenter(): FirstPresenter {
        return FirstPresenter()
    }

    override fun refresh() {
        presenter.refresh()
    }

    override fun refreshResult(isSuccess: Boolean) {
        ToastUtil.toastShort(if (isSuccess) "success" else "failure")
    }
}