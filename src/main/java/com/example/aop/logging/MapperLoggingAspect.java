package com.example.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MapperLoggingAspect {
    @Pointcut("within(com.example.*CreateEditMapper)")
    public void isCreateEditMapper(){}

    @Pointcut("within(com.example.*ReadMapper)")
    public void isReadMapper(){}

    @Pointcut("isReadMapper() && execution(public * map(*))")
    public void anyMapFromReadMapper(){}

    @Pointcut("isCreateEditMapper() && execution(public * map(*))")
    public void anyMapWithOneParamFromCreateEditMapper(){}

    @Pointcut("isCreateEditMapper() && execution(public * map(*, *))")
    public void anyMapWithTwoParamFromCreateEditMapper(){}


    @Around("anyMapWithOneParamFromCreateEditMapper() && target(mapper) && args(object)")
    public void addLoggingAroundForMapWithOneParamFromAnyCreateEditMapper(ProceedingJoinPoint joinPoint, Object mapper, Object object) throws Throwable {
        log.info("around before - invoked map method in class {}, from {}", mapper, object);
        try{
            Object result = joinPoint.proceed();
            log.info("around after returning - invoked map method in class {}, with result {}", mapper, result);
        }catch (Throwable ex){
            log.error("arround after throwwing - invoked map method in class {}, with throwwing {}", mapper, ex);
            throw ex;
        }finally {
            log.info("around after - invoked map method in class {}", mapper);
        }
    }

    @Around("anyMapWithTwoParamFromCreateEditMapper() && target(mapper) && args(fromObject, toObject)")
    public void addLoggingAroundForMapWithTwoParamFromAnyCreateEditMapper(ProceedingJoinPoint joinPoint, Object mapper, Object fromObject, Object toObject) throws Throwable {
        log.info("around before - invoked map method in class {}, from {}, to {}", mapper, fromObject, toObject);
        try{
            Object result = joinPoint.proceed();
            log.info("around after returning - invoked map method in class {}, with result {}", mapper, result);
        }catch (Throwable ex){
            log.error("arround after throwwing - invoked map method in class {}, with throwwing {}", mapper, ex);
            throw ex;
        }finally {
            log.info("around after - invoked map method in class {}", mapper);
        }
    }

    @Around("anyMapFromReadMapper() && target(mapper) && args(fromObject)")
    public void addLoggingAroundForMapFromAnyReadMapper(ProceedingJoinPoint joinPoint, Object mapper, Object fromObject) throws Throwable {
        log.info("around before - invoked map method in class {}, from {}", mapper, fromObject);
        try{
            Object result = joinPoint.proceed();
            log.info("around after returning - invoked map method in class {}, with result {}", mapper, result);
        }catch (Throwable ex){
            log.error("arround after throwwing - invoked map method in class {}, with throwwing {}", mapper, ex);
            throw ex;
        }finally {
            log.info("around after - invoked map method in class {}", mapper);
        }
    }


}
