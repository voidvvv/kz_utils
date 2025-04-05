package com.kz.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> {
    private int code;
    private String message;
    private T data;

    public static <S> ResponseDTO<S> ok(String msg, S data) {
        ResponseDTO<S> response = new ResponseDTO<>();
        response.setCode(200);
        response.setMessage(msg);
        response.setData(data);
        return response;
    }

    public static <S> ResponseDTO<S> ok() {
        return ok("success", null);
    }

    public static <S> ResponseDTO<S> ok(S data) {
        return ok("success", data);
    }

    public static <S> ResponseDTO<S> fail(String msg, S data, int code) {
        ResponseDTO<S> response = new ResponseDTO<>();
        response.setCode(code);
        response.setMessage(msg);
        response.setData(data);
        return response;
    }

    public static <S> ResponseDTO<S> fail(String msg, S data) {
        return fail(msg, data, 500);
    }

    public static <S> ResponseDTO<S> fail(String msg) {
        return fail(msg, null, 500);
    }


}
