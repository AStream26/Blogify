package com.app.blog.config;

import com.app.blog.Constant.ResponseStatus;
import com.app.blog.dtos.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Response<Object> body = new Response<>(ResponseStatus.FAILURE,HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(response.getOutputStream(), body);
    }
}
