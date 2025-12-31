package com.wqs.core.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    private int code;
    private String message;
    private long total;
    private List<T> list;
    private long timestamp;

    public PageResult(long total, List<T> list) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.total = total;
        this.list = list;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> PageResult<T> success(long total, List<T> list) {
        return new PageResult<>(total, list);
    }
}
