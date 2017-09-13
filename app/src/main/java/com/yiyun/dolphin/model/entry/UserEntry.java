package com.yiyun.dolphin.model.entry;


import android.databinding.Bindable;

import com.yiyun.dolphin.BR;

/**
 * Created by xiangyun_liu on 2017/9/13.
 */

public class UserEntry extends BaseEntry {
    private String name;
    private int age;

    public UserEntry(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

}
