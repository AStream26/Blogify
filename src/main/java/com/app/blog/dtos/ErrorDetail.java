package com.app.blog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ErrorDetail {

    private String message;
    private HttpStatus statusCode;
    private LocalDateTime time;
}
