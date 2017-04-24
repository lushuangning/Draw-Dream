package sunnylu.me.okhttptest.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装OkHttp
 * Created by 路双宁 on 2017/4/14.
 */

public class OkHttpHelper {
    /**
     * 采用DCL单例模式使用OkHttpClient
     */
    private volatile static OkHttpHelper mOkHttpHelperInstance = null;
    private static OkHttpClient mClientInstance;
    private Handler mHandler;
    private Gson mGson;

    private OkHttpHelper(){
        mClientInstance = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .build();

//        mClientInstance.connectTimeoutMillis();//默认10000毫秒
//        mClientInstance.readTimeoutMillis();
//        mClientInstance.writeTimeoutMillis();
        mGson = new Gson();

        mHandler = new Handler(Looper.getMainLooper());

    }

    public static OkHttpHelper getmOkHttpHelperInstance(){
        if (mOkHttpHelperInstance == null){
            synchronized (OkHttpHelper.class){
                if (mOkHttpHelperInstance == null){
                    mOkHttpHelperInstance = new OkHttpHelper();
                }
            }
        }

        return mOkHttpHelperInstance;
    }

    /**
    *封装一个Request方法
    *
     */

    public void request(final Request request, final BaseCallback callback){

        callback.onRequestBefore();
        mClientInstance.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //返回失败
                callbackFailure(request, callback, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //成功返回回调
                    String resString = response.body().string();

                    if (callback.mType == String.class){
                        //如果返回的是String类型
                        callbackSuccess(response, resString, callback);
                    }else{
                        //如果返回其他类型
                        try {
                            Object o = mGson.fromJson(resString, callback.mType);
                            callbackSuccess(response, o, callback);
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                            callbackError(response, callback, e);
                        }
                    }
                }else {
                    callbackError(response, callback, null);
                }
            }
        });
    }

    /**
     * 在主线程中执行的回调
     *
     * @param response
     * @param o
     * @param callback
     */
    private void callbackSuccess(final Response response, final Object o, final BaseCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, o);
            }
        });
    }

    /**
     * 在主线程中执行的回调
     * @param response
     * @param callback
     * @param e
     */
    private void callbackError(final Response response, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, response.code(), e);
            }
        });
    }

    /**
     * 在主线程中执行的回调
     * @param request
     * @param callback
     * @param e
     */
    private void callbackFailure(final Request request, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(request, e);
            }
        });
    }

    /**
     * 对外公开的get方法
     *
     * @param url
     * @param callback
     */
    public void get(String url, BaseCallback callback) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        request(request, callback);
    }

    /**
     * 对外公开的post方法
     *
     * @param url
     * @param params
     * @param callback
     */
    public void post(String url, Map<String, String> params, BaseCallback callback) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        request(request, callback);
    }

    /**
     * 构建请求对象
     *
     * @param url
     * @param params
     * @param type
     * @return
     */
    private Request buildRequest(String url, Map<String, String> params, HttpMethodType type) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (type == HttpMethodType.GET) {
            builder.get();
        } else if (type == HttpMethodType.POST) {
            builder.post(buildRequestBody(params));
        }
        return builder.build();
    }

    /**
     * 通过Map的键值对构建请求对象的body
     *
     * @param params
     * @return
     */
    private RequestBody buildRequestBody(Map<String, String> params) {

        FormBody.Builder formBody = new FormBody.Builder();

        if (params != null) {

            for (Map.Entry<String, String> entity : params.entrySet()) {

                formBody.add(entity.getKey(), entity.getValue());
            }
        }
        return formBody.build();
    }

    /**
     * 这个枚举用于指明是哪一种提交方式
     */
    enum HttpMethodType {
        GET,
        POST
    }


}
