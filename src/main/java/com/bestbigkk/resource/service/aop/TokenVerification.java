package com.bestbigkk.resource.service.aop;

import com.bestbigkk.resource.annotation.CheckToken;
import com.bestbigkk.resource.exception.KkException;
import com.bestbigkk.resource.service.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
*  @author: xugongkai
*  @data: 2019-12-19 14:01:58
*  @describe: 切面
**/
@Aspect
@Component
public class TokenVerification {

    private static final String CAN_NOT_FOUND_CUT_POINT = "无法找到切点";
    private static final String INVALID_TOKEN = "无效的令牌";

    @Autowired
    private TokenService tokenService;

    @Before("@annotation(checkToken)")
    public void verificationToken(JoinPoint joinPoint, CheckToken checkToken) {

        Class<?> className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();

        Method targetMethod = null;
        Method[] methods = className.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                targetMethod = method;
                break;
            }
        }
        if (Objects.isNull(targetMethod)) {
            throw new KkException(CAN_NOT_FOUND_CUT_POINT);
        }

        String targetValue = null;
        String propertyName = checkToken.propertyName();
        Parameter[] methodParameters = targetMethod.getParameters();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < methodParameters.length; i++) {
            if (methodParameters[i].getName().equals(propertyName)) {
                targetValue = (String) args[i];
            }
        }

        boolean verification = tokenService.useToken(targetValue);
        if (!verification) {
            throw new KkException(INVALID_TOKEN);
        }
    }
}
