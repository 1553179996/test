package com.xdd.test.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
@Data
public class BasicOutputParam<T> implements Serializable {

    private int code = HttpStatus.OK.value();
    private int count;
    private T data;
    private Object msg;

    public BasicOutputParam() {
    }

    public BasicOutputParam(T data) {
        this.data = data;
    }

    public BasicOutputParam( T data,int count) {
        this.count = count;
        this.data = data;
    }

    public BasicOutputParam(T data, int count, int code, String msg) {
        this.code = code;
        this.count = count;
        this.data = data;
        this.msg = msg;
    }
}
