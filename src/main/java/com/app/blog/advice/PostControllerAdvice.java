package com.app.blog.advice;

import com.app.blog.exception.ErrorDetail;
import com.app.blog.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class PostControllerAdvice {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> resourceNotFound(ResourceNotFoundException exp){
        System.out.println("PostControllerAdvice.resourceNotFound");
        ErrorDetail detail = new ErrorDetail(exp.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return new ResponseEntity<ErrorDetail>(detail,HttpStatus.NOT_FOUND);
    }

}
