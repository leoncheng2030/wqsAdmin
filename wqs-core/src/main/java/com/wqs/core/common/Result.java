package com.wqs.core.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    private long timestamp;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    // ============================ Success ============================

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }
    
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    // ============================ Failure ============================

    public static <T> Result<T> failure() {
        return failure(ResultCode.FAILURE);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(ResultCode.FAILURE.getCode(), message, null);
    }
    
    public static <T> Result<T> failure(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }
    
    // ============================ Helper ============================
    
    public boolean isSuccess() {
        return this.code == ResultCode.SUCCESS.getCode();
    }
}
