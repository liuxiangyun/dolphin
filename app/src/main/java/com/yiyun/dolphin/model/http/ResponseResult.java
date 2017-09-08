package com.yiyun.dolphin.model.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * 接口返回的json数据解析对象
 * <p>
 * 支持的Gson结构
 * {"code":"0","message":"success","data":{}}
 * {"code":"0","message":"success","data":[]}
 */

public class ResponseResult<T> {
    @Expose
    public int code;


    @Expose
    //该变量可以解析成message，msg中的任意一个,如果同时出现多个，以最后一个匹配的字段的值为准
    @SerializedName(value = "message", alternate = {"msg"})
    public String message = "";

    //定义成泛型,灵活方便
    @Expose
    public T data;

    /*
     * 创建Gson时如果调用了new GsonBuilder().excludeFieldsWithoutExposeAnnotation().crate(),此时所有没有加上@Expose的变
     * 量都不会被解析，@Expose 默认有两个属性：serialize(序列化) 和 deserialize(反序列化)，默认值都为 true，把需要解析的
     * 变量加上@Expose,如果要控制序列化和反序列化更改serialize或者deserialize的值即可，所有值为true的属性其实可以不写。
     */
    @Expose(serialize = false, deserialize = false)
    public String error;

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
