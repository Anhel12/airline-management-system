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
public class ServiceLoggingAspect {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer(){}

    @Pointcut("isServiceLayer() && execution(public * findById(*))")
    public void anyServiceFindById(){}

    @Pointcut("isServiceLayer() && execution(public * create(*))")
    public void anyServiceCreate(){}

    @Pointcut("isServiceLayer() && execution(public * update(*, *))")
    public void anyServiceUpdate(){}

    @Pointcut("isServiceLayer() && execution(public * delete(*))")
    public void anyServiceDelete(){}

    @Around("anyServiceFindById() && target(service) && args(id)")
    public void addLoggingAroundForFindById(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("around before - invoked findById method in class {}, with id {}", service, id);
        try{
            Object result = joinPoint.proceed();
            log.info("around after returning - invoked findById method in class {}, with result {}", service, result);
        }catch (Throwable ex){
            log.error("arround after throwwing - invoked findById method in class {}, with throwwing {}", service, ex);
            throw ex;
        }finally {
            log.info("around after - invoked findById method in class {}", service);
        }
    }

    @Around("anyServiceCreate() && target(service) && args(dto)")
    public void addLoggingAroundForCreate(ProceedingJoinPoint joinPoint, Object service, Object dto) throws Throwable {
        log.info("around before - invoked create method in class {}, with dto {}", service, dto);
        try{
            Object result = joinPoint.proceed();
            log.info("around after returning - invoked create method in class {}, with result {}", service, result);
        }catch (Throwable ex){
            log.error("arround after throwwing - invoked create method in class {}, with throwwing {}", service, ex);
            throw ex;
        }finally {
            log.info("around after - invoked create method in class {}", service);
        }
    }

    @Around("anyServiceUpdate() && target(service) && args(id, dto)")
    public void addLoggingAroundForUpdate(ProceedingJoinPoint joinPoint, Object service, Object id, Object dto) throws Throwable {
        log.info("around before - invoked update method in class {}, from entity with id {}, to {}", service, id, dto);
        try{
            Object result = joinPoint.proceed();
            log.info("around after returning - invoked update method in class {}, with result {}", service, result);
        }catch (Throwable ex){
            log.error("arround after throwwing - invoked update method in class {}, with throwwing {}", service, ex);
            throw ex;
        }finally {
            log.info("around after - invoked update method in class {}", service);
        }
    }

    @Around("anyServiceDelete() && target(service) && args(id)")
    public void addLoggingAroundForDelete(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("around before - invoked delete method in class {}, with id {}", service, id);
        try{
            Object result = joinPoint.proceed();
            log.info("around after returning - invoked delete method in class {}, with result {}", service, result);
        }catch (Throwable ex){
            log.error("arround after throwwing - invoked delete method in class {}, with throwwing {}", service, ex);
            throw ex;
        }finally {
            log.info("around after - invoked delete method in class {}", service);
        }
    }



}
