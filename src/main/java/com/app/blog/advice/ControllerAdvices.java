package com.app.blog.advice;

import com.app.blog.dtos.ErrorDetail;
import com.app.blog.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvices {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> resourceNotFound(ResourceNotFoundException exp){
        System.out.println("ControllerAdvices.resourceNotFound");
        ErrorDetail detail = new ErrorDetail(exp.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return new ResponseEntity<ErrorDetail>(detail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail>handleOtherException(Exception exp){
        System.out.println("ControllerAdvices.handleOtherException");
        ErrorDetail detail = new ErrorDetail(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());

        return new ResponseEntity<ErrorDetail>(detail,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
