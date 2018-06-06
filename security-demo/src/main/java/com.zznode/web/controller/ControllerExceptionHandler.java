package com.zznode.web.controller;

import com.zznode.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sc_ma on 2018/1/25.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UserNotExistException.class)//处理哪个异常
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回的状态码
    public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }
}
