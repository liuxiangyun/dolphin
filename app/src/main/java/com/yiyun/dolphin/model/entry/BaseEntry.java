package com.yiyun.dolphin.model.entry;

import android.databinding.BaseObservable;

/**
 * Created by xiangyun_liu on 2017/9/13.
 * <p>
 * 继承BaseObservable，在需要进行数据绑定的字段get方法添加 @Bindable注解，并在set方法中调用 notifyPropertyChanged(fieldId)，
 * 那么在数据改变的时候，UI也会跟着改变
 */

public class BaseEntry extends BaseObservable {

}
