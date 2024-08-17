package com.ams.dev.sale.point.Dtos;

import java.util.Objects;

public class ApiResponseDto<T> {
    private Integer statusCode;
    private String message;
    private T data;


    public ApiResponseDto() {
    }

    public ApiResponseDto(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
