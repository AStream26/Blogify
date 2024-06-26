package com.app.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class BlogAPIException extends RuntimeException{
    private String message;
    private HttpStatus status;

    public BlogAPIException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
