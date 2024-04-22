package com.app.blog.dtos;

import com.app.blog.Constant.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Response<T> {
    private ResponseStatus status;
    private Integer statusCode;
    private String message;
    private T data;
    private String time;

    public Response(ResponseStatus status,Integer statusCode,T data){
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
        this.time = LocalDateTime.now().toString();
    }

    public Response(ResponseStatus status,Integer statusCode,String message){
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.time = LocalDateTime.now().toString();
    }
}
