package org.example.springsecuritydemo.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Aspect
@Component
@Order(2)
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // Pointcut to match all public methods in the application and external code
    @Pointcut("execution(public * org.example.springsecuritydemo.*.*(..))")
    private void publicMethodsFromLoggingPackage() {}

    @Before(value = "publicMethodsFromLoggingPackage()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Logging before method execution.");
    }
    @Around(value = "publicMethodsFromLoggingPackage()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("Logging before method execution.");
        Object result = joinPoint.proceed();
        logger.debug("Logging after method execution.");
        return result;
    }
    @AfterReturning(value = "publicMethodsFromLoggingPackage()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Logging after method execution.");
    }
    @AfterThrowing(pointcut = "publicMethodsFromLoggingPackage()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method threw exception: " + exception.getMessage());
    }
}


