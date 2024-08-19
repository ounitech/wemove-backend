package com.ounitech.wemove.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * com.ounitech.wemove.services.*.*(..))")
    private void publicMethodsFromServicePackage() {
    }

    @Before(value = "publicMethodsFromServicePackage()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String className = String.valueOf(joinPoint.getTarget().getClass().getClassLoader());
        String methodeName = joinPoint.getSignature().getName();
        log.info("class : {} : Starting methode: {}({})", className, methodeName, Arrays.asList(args));
    }

    @AfterReturning(pointcut = "publicMethodsFromServicePackage()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodeName = joinPoint.getSignature().getName();
        //log.info("Completed method: {} with result: {}", methodeName, result);
        log.info("Completed method: {} successfully", methodeName);
    }

    @AfterThrowing(pointcut = "publicMethodsFromServicePackage()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        log.error("Method {} threw exception: {}", methodName, exception.getMessage());
    }
}
