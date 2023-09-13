package com.example.todolist.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAdvice {

    @Around("execution(* com.example.todolist.service.ToDoListService.createToDoList(com.example.todolist.dto.ToDoListDto))")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        proceedingJoinPoint.getTarget().getClass().toString();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object params = proceedingJoinPoint.getArgs();
        log.info("method invoked {}, class name {}, arguments {}",  methodName, className, params);
        return proceedingJoinPoint.proceed();
    }
}
