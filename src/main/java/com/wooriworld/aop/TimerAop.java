package com.wooriworld.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class TimerAop {

  //controller 하위 모든 것
  @Pointcut("execution(* com.wooriworld.controller..*.*(..))")
  private void myPointCut() {

  }

  @Pointcut("@annotation(com.wooriworld.annotation.Timer)")
  private void enableTimer(){

  }

  //두가지 조건을 같이 사용 한다.
  @Around("myPointCut() && enableTimer()")
  public void around(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    stopWatch.prettyPrint();
    stopWatch.start();

    Object result = joinPoint.proceed();

    //실행 후
    stopWatch.stop();
    log.info("Total Time: {}", stopWatch.getTotalTimeSeconds());

  }
}
