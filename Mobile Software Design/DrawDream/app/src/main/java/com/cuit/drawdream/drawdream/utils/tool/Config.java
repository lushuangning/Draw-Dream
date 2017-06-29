package com.cuit.drawdream.drawdream.utils.tool;

import com.cuit.drawdream.drawdream.bean.ordinary.UserEntity;

/**
 * Created by JWZ on 2016/9/5 0005.
 */
public class Config {

//    http://192.168.191.1:8000/server/

    //iphone地址
//    public static String PATH_ROOT="http://172.20.10.14:8000/server/";

//    public static String PATH_ROOT="http://10.0.3.2:8000/server/";
        public static String PATH_ROOT="http://192.168.191.1:8000/server/";

    /**
     * 是否进入后台
     */
    public static Boolean isActive = true;

    /**
     * 当前用户id
     */
    public static Long USER_ID = new Long(0);
    /**
     * 当前用户姓名
     */
//    public static String USER_NAME = "";
    /**
     * 服务器错误
     */
    public static String CODE_ERROR = "302";
    /**
     * 数据不存在
     */
    public static String CODE_NONE = "404";
    /**
     * 当前用户信息
     */
    public static UserEntity USER_INFO = new UserEntity();


}
