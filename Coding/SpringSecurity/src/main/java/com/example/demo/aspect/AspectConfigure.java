package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Aspect
@Order(1)
@Component
@ConditionalOnExpression("${endpoint.aspect.enabled:true}")
public class AspectConfigure {
    static Logger log = Logger.getLogger(AspectConfigure.class);
    
    // @Around("execution(* com.example.demo.*.*.*(..))")
    // public void logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    //     long startTime = System.currentTimeMillis();
    //     Object result = joinPoint.proceed();
    //     long timeTaken = System.currentTimeMillis() - startTime;
    //     System.out.println("Method " + joinPoint.getSignature().toShortString() + " executed in " + timeTaken + "ms");
    // }

    // DAO requests and responses
    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logBeforeDao(JoinPoint p) {
        System.out.println(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " START");
        Object[] signatureArgs = p.getArgs();


        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {

            if (signatureArgs.length > 0 && signatureArgs[0] != null) {
                System.out.println("\nRequest object: \n" + mapper.writeValueAsString(signatureArgs[0]));
            }
        } catch (JsonProcessingException e) {
        }
    }

    @AfterReturning(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", returning = "returnValue")
    public void logAfterReturning(JoinPoint p, Object returnValue) {
        System.out.println(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " Value shall be returned");
        System.out.println("\nResponse object: \n" + returnValue);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            System.out.println("\nResponse object: \n" + mapper.writeValueAsString(returnValue));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " END");
    }

    @AfterThrowing(pointcut = "execution(* com.example.demo.*.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("Exception thrown: " + methodName);
        System.out.println("Exception: " + ex.getMessage());
    }
}
