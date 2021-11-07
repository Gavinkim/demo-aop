package com.wooriworld.chaboo.aop;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ApiParamAop {

  //controller 하위 모든 것
  @Pointcut("execution(* com.wooriworld.chaboo.controller..*.*(..))")
  private void myPointCut() {

  }

  //포인트 컷 의 하위 메서드 실행 전
  @Before("myPointCut()")
  public void before(JoinPoint joinPoint){
    Object[] args = joinPoint.getArgs();
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();
    log.info("method: {}", method.getName());
    for(Object obj : args) {
      log.info("Type: {}, ", obj.getClass().getSimpleName());
      log.info("value: {}, ", obj);
    }
  }

  //리턴 오브젝트 파라미터이름과 일치 해야 한다.
  ////포인트 컷 의 하위 메서드 실행 후
  @AfterReturning(value = "myPointCut()", returning = "returnObj")
  public void afterReturn(JoinPoint joinPoint, Object returnObj) {
    log.info("return obj: {}", returnObj);
  }

}
