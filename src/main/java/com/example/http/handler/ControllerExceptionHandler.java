package com.example.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.example.http.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e){
        log.error("Failed to return response", e);
        return "error/error500";
    }
}
