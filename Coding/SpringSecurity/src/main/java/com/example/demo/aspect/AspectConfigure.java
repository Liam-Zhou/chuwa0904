package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Order(1)
@Component
@ConditionalOnExpression("${endpoint.aspect.enabled:true}")
public class AspectConfigure {
    static Logger log = Logger.getLogger(AspectConfigure.class);
    
    @Around("execution(* com.example.demo.*.*.*(..))")
    public void logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("Method " + joinPoint.getSignature().toShortString() + " executed in " + timeTaken + "ms");
    }

    // all methods in the application
    @Before("execution(* com.example.demo.*.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("=====>" + methodName + ": " + "Start execution");
    }

    @After("execution(* com.example.demo.*.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("=====>"+ methodName + ": " + "End execution");
    }

    // DAO requests and responses
    @Before("execution(* com.example.demo.dao.*.*(..))")
    public void logBeforeDao(JoinPoint p) {
        if (log.isTraceEnabled()) {
            log.trace(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " START");
            Object[] signatureArgs = p.getArgs();


            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            try {

                if (signatureArgs[0] != null) {
                    log.trace("\nRequest object: \n" + mapper.writeValueAsString(signatureArgs[0]));
                }
            } catch (JsonProcessingException e) {
            }
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.dao.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint p, Object returnValue) {
        if (log.isTraceEnabled()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            try {
                log.trace("\nResponse object: \n" + mapper.writeValueAsString(returnValue));
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
            log.trace(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " END");
        }
    }

    @AfterThrowing(pointcut = "execution(* com.example.demo.*.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("Exception thrown: " + methodName);
        System.out.println("Exception: " + ex.getMessage());
    }
}
