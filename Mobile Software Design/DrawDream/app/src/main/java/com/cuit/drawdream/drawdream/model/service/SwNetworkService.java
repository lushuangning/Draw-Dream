package com.cuit.drawdream.drawdream.model.service;

import com.cuit.drawdream.drawdream.bean.response.ResponseClassifyResult;
import com.cuit.drawdream.drawdream.bean.response.ResponseInfo;
import com.cuit.drawdream.drawdream.bean.response.ResponseLogin;
import com.cuit.drawdream.drawdream.bean.response.ResponseReview;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 数据网络接口访问
 * Created by husong on 7/28/16.
 */
public interface SwNetworkService {

    @POST("login/")
    @Headers("Content-type:application/json;charset=UTF-8")
    Observable<Response<ResponseLogin>> login (@Body RequestBody route);

    //TODO 这里要改成result
    @POST("index/")
    @Headers("Content-type:application/json;charset=UTF-8")
    Observable<Response<ResponseClassifyResult>> result(@Body RequestBody route);

    @POST("index/")
    @Headers("Content-type:application/json;charset=UTF-8")
    Observable<Response<ResponseClassifyResult>> index (@Body RequestBody route);

    @POST("comment/")
    @Headers("Content-type:application/json;charset=UTF-8")
    Observable<Response<ResponseReview>> review (@Body RequestBody route);

    @POST("user_info/")
    @Headers("Content-type:application/json;charset=UTF-8")
    Observable<Response<ResponseInfo>> userInfo (@Body RequestBody route);

    @POST("deliver_comment/")
    @Headers("Content-type:application/json;charset=UTF-8")
    Observable<Response<ResponseInfo>> comment (@Body RequestBody route);
}
