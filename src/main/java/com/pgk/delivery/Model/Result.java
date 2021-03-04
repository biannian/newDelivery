package com.pgk.delivery.Model;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T result;

    public T getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public static <T> Result<T> success(T result) {
        Result<T> res = new Result<>();
        res.code = 200;
        res.msg = "成功";
        res.result = result;
        return res;
    }

    public static Result<?> success() {
        return success(null);
    }

    public static <T> Result<T> fail(int code, String msg, T result) {
        Result<T> res = new Result<>();
        res.code = code;
        res.msg = msg;
        res.result = result;
        return res;
    }

    public static <T> Result<T> fail() {
        return fail(-1);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> Result<T> fail(int code) {
        return fail(code, "");
    }

    public static <T> Result<T> fail(ErrorCode errorCode, T result) {
        return fail(errorCode.getValue(), errorCode.getDesc(), result);
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        return fail(errorCode, null);
    }

}
