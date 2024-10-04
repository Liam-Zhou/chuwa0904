package com.hw11.https;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;


@Aspect
@Component
public class AOP {
    private static final Logger log = LoggerFactory.getLogger(AOP.class);
    Instant start;
    Instant end;

    @Pointcut(value = "execution(* com.hw11.https.HTTPSRestController.*(..))")
    public void pointCut() {}

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String s = "[@Before] " + "Name of target method: " + name;
        log.info(s);
        start = Instant.now();
    }

    @AfterReturning(value = "pointCut()", returning = "res")
    public void afterReturning(JoinPoint joinPoint, Object res) {
        String name = joinPoint.getSignature().getName();
        String s = "[@AfterReturning] " + "Name of target method: " + name + " | Return: " + res;
        log.info(s);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void afterReturning(JoinPoint joinPoint, Throwable exception) {
        String name = joinPoint.getSignature().getName();
        String s = "[@AfterThrowing] " + "Name of target method: " + name + " | Exception: " + exception;
        log.info(s);
    }

    @After(value = "pointCut()")
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        end = Instant.now();
        Duration duration = Duration.between(start, end);
        String s = "[@After] " + "Name of target method: " + name + " | Execution Time: " + duration;
        log.info(s);
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object result = null;
        try {
            log.info("[@Around] -> @Before");
            result = joinPoint.proceed();
            log.info("[@Around] -> @AfterReturning");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("[@Around] -> @AfterThrowing");
        } finally {
            log.info("[@Around] -> @After");
        }
        return result;
    }
}
