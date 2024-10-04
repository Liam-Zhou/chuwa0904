package com.example.hwcoding11.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class CustomLoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(CustomLoggerAspect.class);

    @Before("execution(* com.example.hwcoding11..*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        logger.info("Before method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.example.hwcoding11..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After method: " + joinPoint.getSignature().getName());
        logger.info("Method returned: " + result);
    }

    @AfterThrowing(value = "execution(* com.example.hwcoding11..*(..))", throwing = "exception")
    public void logAfterException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method threw exception: " + joinPoint.getSignature().getName());
        logger.error("Exception: " + exception.getMessage());
    }

    @Around("execution(* com.example.hwcoding11..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return result;
    }
}
