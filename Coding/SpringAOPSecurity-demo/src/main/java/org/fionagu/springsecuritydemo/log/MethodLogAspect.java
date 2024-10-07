package org.fionagu.springsecuritydemo.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//This tells Spring that this class contains AOP logic (extra behavior that you want to apply to methods)
@Aspect
//This makes the aspect a Spring bean, so it gets managed by the Spring container.
@Component
//This annotation comes from Lombok and automatically creates a logger for logging messages (you don’t need to manually create a logger)
@Slf4j


public class MethodLogAspect {
    /*自定义切点*/
    //define where the aop login should be applied
    @Pointcut("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
    public void MethodLog(){

    }

    /*  an example of using an empty method to define a Pointcut

    /*方法进入之前记录日志*/

    /*
    //joinPoint: This object provides access to details about the method
    @Before("MethodLog()")
    public void doBefore(JoinPoint joinPoint){
        //记录方法输入参数
        log.info("Request Method: {}, Request Param: {}",
                joinPoint.getSignature(),//joinPoint.getSignature(): This gets the method return type, arguments
                JSON.toJSONString(joinPoint.getArgs())//This converts the return value to a JSON string for logging.
        );
    }
    //"after the method that matches the pointcut (MethodLog()) finishes and returns a value, run this code."
    @AfterReturning(returning = "o", pointcut = "MethodLog()")
    public void afterReturning(Object o){
        //记录方法的返回
        log.info("Response Result:{}", JSONObject.toJSONString(o));
    }*/



    /*Bind JointPoints directly in the AOP code*/

    /*binding JointPoints directly means defining the Pointcut expression right inside the advice annotations (@Before, @After, @Around, etc.),*/
    // Around advice to handle both before and after execution
    @Around("MethodLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Get current HTTP request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

        // Log request details if available
        if (request != null) {
            String requestURI = request.getRequestURI();
            String httpMethod = request.getMethod();
            log.info("API Request - URI: {}, Method: {}", requestURI, httpMethod);
        }


        long startTime = System.currentTimeMillis(); // Capture start time

        // Before method execution: Log method name and input parameters
        log.info("Before - Method: {}, Params: {}",
                proceedingJoinPoint.getSignature(),
                JSON.toJSONString(proceedingJoinPoint.getArgs()));

        Object result = proceedingJoinPoint.proceed(); // Proceed with method execution

        long endTime = System.currentTimeMillis(); // Capture end time
        long executionTime = endTime - startTime; // Calculate execution time

        // After method execution: Log the return value and execution time
        log.info("After - Result: {}, Execution Time: {} ms",
                JSONObject.toJSONString(result), executionTime);

        return result;
    }


}
