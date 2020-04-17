package com.nahib.login.entity;

public enum ResultEnum {

    //用户登录enum
    LOGIN_UNKNOW_ERROR(-1,"未知错误"),
    LOGIN_SUCCESS(2000,"用户登录成功"),
    NAME_OR_PWD_ERROR(3001,"用户名密码错误"),

    //查找用户
    FIND_USER_ERROR(3002,"未查找到该用户"),
    FIND_USER_SUCCESS(2002,"查找到该用户"),

    //签名验证
    TOKEN_VERIFY_ERROR(3003,"签名验证失败"),
    TOKEN_VERIFY_DISTORT_ERROR(3004,"数据被篡改"),

    //RSA验证
    RSA_KEY_ERROR(3005,"非法访问")
    ;
    private  Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
