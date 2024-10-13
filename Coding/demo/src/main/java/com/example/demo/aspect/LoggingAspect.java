package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // 定义切入点：匹配 com.example.demo.controller 包中的所有方法
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controllerMethods() {
        // 此处为空，切入点定义
    }


    // 在方法执行前记录日志
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Method {} is about to be called", joinPoint.getSignature().getName());
    }

    // 在方法执行后记录日志
    @After("controllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Method {} has been called", joinPoint.getSignature().getName());
    }
}
