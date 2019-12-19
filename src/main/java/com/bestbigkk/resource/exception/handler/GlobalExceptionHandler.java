package com.bestbigkk.resource.exception.handler;

import com.bestbigkk.resource.entity.RespBO;
import com.bestbigkk.resource.entity.ExceptionResp;
import com.bestbigkk.resource.exception.KkException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
*  @author: xugongkai
*  @data: 2019-12-19 13:39:35
*  @describe: 异常处理
**/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({KkException.class, ConstraintViolationException.class})
    public RespBO fun0(Exception e, HttpServletResponse response) {
        response.setStatus(401);
        ExceptionResp exceptionResp = new ExceptionResp();
        exceptionResp.setError(e.getMessage());
        exceptionResp.setDetail(e.getStackTrace());
        exceptionResp.setStatus(false);
        return exceptionResp;
    }

}
