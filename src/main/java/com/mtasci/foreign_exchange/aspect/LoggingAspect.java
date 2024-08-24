package com.mtasci.foreign_exchange.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        logger.info("Request: " + joinPoint.getSignature().toShortString());

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("Execution Time: " + executionTime);
            logger.severe("Error: " + throwable.getMessage());
            throw throwable;
        }

        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("Execution Time: " + executionTime);
        return result;
    }


}
