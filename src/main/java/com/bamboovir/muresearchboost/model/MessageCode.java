package com.bamboovir.muresearchboost.model;

public class MessageCode {
    // 请求成功
    public static final int SUCCESS = 2000 ;
    // 警告
    public static final int WARN = 2001;

    // token
    public static final int TOKEN_ERROR = 4001;//token 不合法
    public static final int TOKEN_OVERDUE = 4002;//token 已过期

    // 登录失败
    public static final int LOGIN_FAILED = 4003;

    // 没有权限
    public static final int PERMISSION_DENIED = 4004;

    // 服务器处理失败(常用错误)
    public static final int ERROR = 5000 ;
}
