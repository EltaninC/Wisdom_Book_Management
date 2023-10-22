package com.example.wisdom_book_management.component;

public enum ResultEnum {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(10000,"成功"),
    USER_NOT_EXIST(1,"用户不存在"),
    USER_IS_EXISTS(2,"用户已存在"),
    Limited_Authority(3,"权限不够"),
    UNLAWFUL_ACT(4,"违法操作"),
    DATA_IS_NULL(6,"数据为空");



    private Integer code;
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
