package com.yiyun.dolphin.model.http;

import com.orhanobut.logger.Logger;
import com.yiyun.dolphin.BuildConfig;
import com.yiyun.dolphin.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 配置OkHttpClient
 *
 * @author xiangyun_liu
 */

public class OkHttpClientSingle {
    private static OkHttpClient mOkHttpClient;
    private static SSLSocketFactory mSslSocketFactory;
    private final static int CONNECT_TIME_OUT = 30;
    private final static int READ_TIME_OUT = 30;
    private final static int WRITE_TIME_OUT = 30;
    private final static String CACHE_FILE_NAME = "okHttp_cache";
    private final static int CACHE_FILE_MAX_SIZE = 100 * 1024 * 1024;

    private OkHttpClientSingle() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        //Url添加公共参数
        builder.addInterceptor(new AddPublicParamsInterceptor());
        //debug模式时打印请求日志信息
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        //添加缓存，设置缓存文件目录，及大小
        Cache cache = new Cache(new File(FileUtil.getCacheDir(), CACHE_FILE_NAME), CACHE_FILE_MAX_SIZE);
        builder.cache(cache);
        //添加缓存拦截器用来配置缓存策略
        builder.addInterceptor(new CacheInterceptor());
        builder.addNetworkInterceptor(new CacheInterceptor());
        if (mSslSocketFactory != null) {
            builder.sslSocketFactory(mSslSocketFactory);
        }
        mOkHttpClient = builder.build();
    }

    /**
     * 获取OKHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (OkHttpClientSingle.class) {
                if (mOkHttpClient == null) {
                    new OkHttpClientSingle();
                }
            }
        }
        return mOkHttpClient;
    }

    /**
     * 如果需要Https的支持在调用getOkHttpClient前需要额外的初始化操作
     *
     * @param certificates
     */
    public static void init(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null) {
                        certificate.close();
                    }
                } catch (IOException e) {
                    Logger.d(e);
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            mSslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            Logger.d(e);
        }
    }
}
