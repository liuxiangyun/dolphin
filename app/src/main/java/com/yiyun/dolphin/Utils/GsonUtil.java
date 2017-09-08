package com.yiyun.dolphin.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.yiyun.dolphin.model.http.ResponseResult;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by xiangyun_liu on 2017/9/4.
 * <p>
 * Gson解析工具类
 */

public class GsonUtil {
    private static Gson mGson;

    public static Gson getGson() {
        if (mGson == null) {
            synchronized (GsonUtil.class) {
                if (mGson == null) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    /**
                     * 配置Gson
                     * 1.excludeFieldsWithoutExposeAnnotation() 对没有用@Expose注解的变量不进行解析，或者对根据使用的@Expose注解的serialize
                     *  （序列化）和deserialize（反序列化）值进行判断解析，@Expose属性值不写默认为true；
                     * 2.serializeNulls() Gson在默认情况下是不主动导出值为null的键的,需要手动设置serializeNulls()；
                     * 3.setDateFormat() 设置日期格式，序列化和反序列化时均有效
                     * 4.disableInnerClassSerialization() 禁止序列化内部类
                     * 5.disableHtmlEscaping() 禁止转义html标签
                     * 6.setPrettyPrinting() 格式化输出
                     * 7.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()) 手动管理String类型的序列化和反序列化，将null替
                     *   换成""
                     */
                    gsonBuilder.excludeFieldsWithoutExposeAnnotation();
                    gsonBuilder.serializeNulls();
//                    gsonBuilder.setDateFormat("yyyy-MM-dd");
//                    gsonBuilder.disableInnerClassSerialization();
                    gsonBuilder.disableHtmlEscaping();
                    gsonBuilder.setPrettyPrinting();
                    gsonBuilder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
                    mGson = gsonBuilder.create();
                }
            }
        }
        return mGson;
    }

    /**
     * 解析ResponseResult中data为对象的情况
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> fromJsonObject(String json, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(ResponseResult.class, new Class[]{clazz});
        return getGson().fromJson(json, type);
    }

    /**
     * 解析ResponseResult中data为数组的情况
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<List<T>> fromJsonArray(String json, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(ResponseResult.class, new Type[]{listType});
        return getGson().fromJson(json, type);
    }

    /**
     * 自定义Type
     */
    static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

    /**
     * 针对String类型进行手动管理序列化和反序列化
     */
    static class NullStringToEmptyAdapterFactory implements TypeAdapterFactory {
        public TypeAdapter create(Gson gson, TypeToken type) {
            Class rawType = type.getRawType();
            if (rawType == String.class) {
                return new StringNullAdapter();
            }
            return null;
        }
    }

    /**
     * 手动管理序列化和反序列化
     */
    static class StringNullAdapter extends TypeAdapter<String> {
        @Override
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.value("");
                return;
            }
            writer.value(value);
        }
    }
}
