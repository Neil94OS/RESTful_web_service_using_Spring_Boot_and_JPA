package com.example.neilassignment2af;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class MyAspects {

    @Before(value = "execution(* com.example.neilassignment2af.repositories.PropertyRepository.findById(..))")
    public void logFindPropertyById(JoinPoint joinPoint)
    {
        log.info("**** I am in findById for properties with arguments " + Arrays.toString(joinPoint.getArgs()));
    }

    @Before(value = "execution(* com.example.neilassignment2af.repositories.PropertyRepository.*(..))")
    public void logProperties(JoinPoint joinPoint)
    {
        log.info("**** I am in " + joinPoint.getSignature().toShortString()+ " with arguments " + Arrays.toString(joinPoint.getArgs()));
    }


    //@Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RestController)")  could not get this to log any info, only worked with @within
    @Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController)")
    public void allMethodsInControllers(){}
    @Before("allMethodsInControllers()")
    public void logControllers(JoinPoint joinPoint){
        log.info("**** All Controllers: I am in " + joinPoint.getSignature().toShortString()+ " with arguments " + Arrays.toString(joinPoint.getArgs()));
    }

}
