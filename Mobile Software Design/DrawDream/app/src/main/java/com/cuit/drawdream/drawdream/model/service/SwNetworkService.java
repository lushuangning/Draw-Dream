package com.cuit.drawdream.drawdream.model.service;

import com.cuit.drawdream.drawdream.bean.response.ResponseLogin;

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

    @POST("/login")
    @Headers("Content-type:application/json;charset=UTF-8")
    Observable<Response<ResponseLogin>> login (@Body RequestBody route);
}
