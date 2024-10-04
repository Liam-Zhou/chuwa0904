package com.example.demo.aspect;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class logAspect {

    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void cotrollerPointCut(){}
    private final static Logger LOG = LoggerFactory.getLogger(logAspect.class);

    @Before("execution(public * com.example.demo.controller.testController.testHTTPS(..))")
    public void doBefore(JoinPoint joinPoint){
        LOG.info("Before");
    }

    @Around("execution(public * com.example.demo.controller.testController.testHTTPS(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String functionName = signature.getName();

        LOG.info("AOP LOG");
        LOG.info("Request URl:{} {}",request.getRequestURL().toString(),request.getMethod());
        LOG.info("Request Method type name: {}, {}",signature.getDeclaringType(),functionName);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        LOG.info("Run time {}",endTime - startTime);

        return result;
    }

    @After("execution(public * com.example.demo.controller.testController.testHTTPS(..))")
    public void doAfter(JoinPoint joinPoint){
        LOG.info("After");
    }
}
