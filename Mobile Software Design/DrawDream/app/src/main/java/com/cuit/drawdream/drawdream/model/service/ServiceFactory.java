package com.cuit.drawdream.drawdream.model.service;

import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.model.service.cookie.CookiesManager;
import com.cuit.drawdream.drawdream.utils.easylog.EasyLog;
import com.cuit.drawdream.drawdream.utils.tool.Config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by husong on 7/28/16.
 */
public class ServiceFactory {

    /**
     * 获取网络访问服务
     *
     * @return
     */
    public static SwNetworkService getNetworkService() {
        Interceptor authInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("AU", MyApplication.getToken())
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "multipart/form-data")
                        .build();
                return chain.proceed(request);
            }
        };

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                EasyLog.i(message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        BossX509TrustManager trustMgr = new BossX509TrustManager();
            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{trustMgr}, new SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
        }

        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslContext.getSocketFactory(), trustMgr)
                .hostnameVerifier(DO_NOT_VERIFY)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cookieJar(CookiesManager.getManager())
                .addInterceptor(authInterceptor)
                .addInterceptor(logInterceptor)
                .build();

        Retrofit client = new Retrofit.Builder()
                .baseUrl(Config.PATH_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return client.create(SwNetworkService.class);


    }


}
