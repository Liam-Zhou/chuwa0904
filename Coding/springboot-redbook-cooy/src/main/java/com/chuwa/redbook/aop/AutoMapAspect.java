package com.chuwa.redbook.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AutoMapAspect {

    private final ModelMapper modelMapper = new ModelMapper();

    @Around("@annotation(autoMap)")
    public Object mapResponse(ProceedingJoinPoint joinPoint, AutoMap autoMap) throws Throwable {
        // Proceed with the method execution
        Object returnValue = joinPoint.proceed();

        // If the return value is an instance of the source class, map it to the target class
        if (autoMap.source().isInstance(returnValue)) {
            return modelMapper.map(returnValue, autoMap.target());
        }
        // Return the original value if mapping is not required
        return returnValue;
    }
}
