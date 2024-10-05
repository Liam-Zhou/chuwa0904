package org.example.springsecuritydemo.aop;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.Arrays;
@Aspect
@Component
@Order(2)
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // Pointcut to match all public methods in the application and external code
//    @Pointcut("execution(public * org.example.springsecuritydemo.*.*(..))")
//    public void serviceLayer() {}

    @Before("execution(public  * org.example.springsecuritydemo.controller.HelloController.sayHello())")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Logging before method execution.");
    }
    @Around("execution(public  * org.example.springsecuritydemo.controller.HelloController.sayHello())")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object result = joinPoint.proceed();

        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String functionName = signature.getName();

        logger.info("Request URl:{} {}",request.getRequestURL().toString(),request.getMethod());
        logger.info("Request Method: {}, {}",signature.getDeclaringType(),functionName);
        long endTime = System.currentTimeMillis();
        logger.info("Run time {}",endTime - startTime);

        return result;
    }
    @After("execution(public  * org.example.springsecuritydemo.controller.HelloController.sayHello())")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Logging after method execution.");
    }
}


