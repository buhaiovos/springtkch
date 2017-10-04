package com.epam.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * Created by Oleksandr_Buhaiov on 10/2/2017.
 */
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {

    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE : " +
        joinPoint.getTarget().getClass().getSimpleName() +
        " " + joinPoint.getSignature().getName());
    }
}
