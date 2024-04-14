package com.app.blog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorDetail {

    Object messages;
    private Integer statusCode;
    private LocalDateTime time;
}
