/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.util;

import com.google.gson.Gson;

/**
 * @author shitianshu on 2018/5/4 下午8:10.
 */
public class ApiResponse<T> {


    /** 业务响应编码,非http响应码 */
    private int code;

    /** 响应文案 */
    private String message;

    /** 响应数据 */
    private T result;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> ApiResponse success(String message, T result) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.code = ResponseCode.SUCCESS_CODE;
        response.message = message;
        response.result = result;
        return response;
    }

    public static <T> ApiResponse success(T object) {
        return success(EMPTY_CONTENT, object);
    }

    public static ApiResponse<String> success() {
        return success(EMPTY_CONTENT);
    }

    public static <T> ApiResponse fail(int code, String message, T result) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.code = code;
        response.message = message;
        response.result = result;
        return response;
    }

    public static ApiResponse<String> fail(int code, String message) {
        return fail(code, message, EMPTY_OBJECT);
    }

    public static ApiResponse<String> fail(int code) {
        return fail(code, EMPTY_CONTENT, EMPTY_CONTENT);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    private static final String EMPTY_CONTENT = "";

    private static final Object EMPTY_OBJECT = new Object();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiResponse{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
