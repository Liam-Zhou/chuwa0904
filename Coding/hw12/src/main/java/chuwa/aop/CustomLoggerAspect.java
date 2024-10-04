package chuwa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class CustomLoggerAspect {

    // Define a reusable pointcut
    @Around("execution(* chuwa..*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Get request details (if available)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (attributes != null) ? attributes.getRequest() : null;

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Log REST API details if available
        if (request != null) {
            System.out.println("HTTP Request: " + request.getMethod() + " " + request.getRequestURI());
        }

        // Log the method signature and execution time
        System.out.println("Method " + joinPoint.getSignature() + " executed in " + executionTime + " ms");

        return result;
    }

    // Logging before method execution
    @Before("execution(* chuwa..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("Before executing method: " + joinPoint.getSignature());
    }

    // Logging after method execution
    @After("execution(* chuwa..*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        System.out.println("After executing method: " + joinPoint.getSignature());
    }

    // Logging method return values
    @AfterReturning(pointcut = "execution(* chuwa..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method " + joinPoint.getSignature() + " returned: " + result);
    }
}
