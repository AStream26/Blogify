package com.app.blog.advice;

import com.app.blog.Constant.ResponseStatus;
import com.app.blog.dtos.Response;
import com.app.blog.exception.BlogAPIException;
import com.app.blog.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<?>> resourceNotFound(ResourceNotFoundException exp) {
        System.out.println("ControllerAdvices.resourceNotFound");
        Response<?> errorDetails = new Response<>(ResponseStatus.FAILURE, HttpStatus.NOT_FOUND.value(), exp.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<Response<Object>> handleBlogAPIException(BlogAPIException exp) {
        System.out.println("ControllerAdvices.handleBlogAPIException");
        Response<Object> errorDetails = new Response<>(ResponseStatus.FAILURE, HttpStatus.NOT_FOUND.value(), exp.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Map<String, String> validationErrorMap = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            validationErrorMap.put(fieldName, message);
        });
        Response<Object> errorDetails = new Response<>(ResponseStatus.FAILURE, HttpStatus.NOT_FOUND.value(), exp.getMessage(),
                validationErrorMap, LocalDateTime.now().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        // Note other way is to override the handleMethodArgumentNotValid()
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exp) {
        System.out.println("ControllerAdvices.handleConstraintViolationException");
        Response<Object> errorDetails = new Response<>(ResponseStatus.FAILURE, HttpStatus.BAD_REQUEST.value(), exp.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        // Note other way is to override the handleMethodArgumentNotValid()
    }

    @ExceptionHandler({UsernameNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<Response<Object>> handleUserNameNotFoundException(UsernameNotFoundException exp) {
        System.out.println("ControllerAdvices.handleUserNameNotFoundException");
        Response<Object> errorDetails = new Response<>(ResponseStatus.FAILURE, HttpStatus.UNAUTHORIZED.value(), exp.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleOtherException(Exception exp) {
        System.out.println("ControllerAdvices.handleOtherException");
        Response<Object> errorDetails = new Response<>(ResponseStatus.FAILURE, HttpStatus.INTERNAL_SERVER_ERROR.value(), exp.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
