package org.example.hw12springapo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("within(org.example.hw12springapo..*)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            logger.info(joinPoint.getSignature() + " executed in " + duration + "ms");
            return result;
        } finally {
            // Additional logging can be added here if needed
        }
    }

    @Before("within(org.example.hw12springapo..*)")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("About to execute: " + joinPoint.getSignature());
    }

    @After("within(org.example.hw12springapo..*)")
    public void logAfterMethod(JoinPoint joinPoint) {
        logger.info("Finished executing: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "within(org.example.hw12springapo..*)", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        logger.info(joinPoint.getSignature() + " returned with value: " + result);
    }

    @AfterThrowing(pointcut = "within(org.example.hw12springapo..*)", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in " + joinPoint.getSignature(), error);
    }
}
