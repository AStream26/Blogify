package com.app.blog.advice;

import com.app.blog.dtos.ErrorDetail;
import com.app.blog.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvices  {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> resourceNotFound(ResourceNotFoundException exp){
        System.out.println("ControllerAdvices.resourceNotFound");
        ErrorDetail detail = new ErrorDetail(exp.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return new ResponseEntity<ErrorDetail>(detail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail>handleOtherException(Exception exp){
        System.out.println("ControllerAdvices.handleOtherException");
        ErrorDetail detail = new ErrorDetail(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<ErrorDetail>(detail,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        Map<String,String> validationErrorMap = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            validationErrorMap.put(fieldName,message);
        });
        ErrorDetail detail = new ErrorDetail(validationErrorMap, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(detail,HttpStatus.BAD_REQUEST);
        // Note other way is to override the handleMethodArgumentNotValid()
    }
}
