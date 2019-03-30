package com.ysj.spike.base;

public class Result<T> {

    private int code;

    private String msg;

    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) return;
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
