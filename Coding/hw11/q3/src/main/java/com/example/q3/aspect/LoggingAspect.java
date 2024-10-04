package com.example.q3.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

   private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

   // Log before method execution
   @Before("execution(* com.example.q3..*(..))")
   public void logBeforeMethodExecution(JoinPoint joinPoint) {
      logger.info("Before execution of method: " + joinPoint.getSignature().getName());
      logger.info("Input Arguments: " + joinPoint.getArgs().length);
   }

   // Log after method execution
   @After("execution(* com.example.q3..*(..))")
   public void logAfterMethodExecution(JoinPoint joinPoint) {
      logger.info("After execution of method: " + joinPoint.getSignature().getName());
   }

   // Log after returning from a method
   @AfterReturning(pointcut = "execution(* com.example.q3..*(..))", returning = "result")
   public void logAfterReturning(JoinPoint joinPoint, Object result) {
      logger.info("Method " + joinPoint.getSignature().getName() + " returned: " + result);
   }

   // Measure method execution time and log around method execution
   @Around("execution(* com.example.q3..*(..))")
   public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
      long startTime = System.currentTimeMillis();
      Object result = joinPoint.proceed();  // Execute target method
      long elapsedTime = System.currentTimeMillis() - startTime;
      logger.info("Execution time of method " + joinPoint.getSignature().getName() + ": " + elapsedTime + " ms");
      return result;
   }
}
