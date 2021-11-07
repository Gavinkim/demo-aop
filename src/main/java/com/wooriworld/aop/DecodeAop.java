package com.wooriworld.aop;

import com.wooriworld.dto.UserDto;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DecodeAop {

  //controller 하위 모든 것
  @Pointcut("execution(* com.wooriworld.controller..*.*(..))")
  private void myPointCut() {

  }

  @Pointcut("@annotation(com.wooriworld.annotation.Decode)")
  private void enableDecode(){

  }

  @Before("myPointCut() && enableDecode()")
  public void before(JoinPoint joinPoint){
    Object[] args = joinPoint.getArgs();

    for(Object obj: args) {
      if(obj instanceof UserDto) {
        UserDto user = UserDto.class.cast(obj);
        String email = user.getEmail();
        user.setEmail(new String(Base64.getDecoder().decode(email.getBytes(StandardCharsets.UTF_8))));
      }
    }
  }

  @AfterReturning(value = "myPointCut() && enableDecode()", returning = "returnObj")
  public void afterReturn(JoinPoint joinPoint, Object returnObj){
    if(returnObj instanceof UserDto) {
      UserDto user = UserDto.class.cast(returnObj);
      String email = user.getEmail();
      user.setEmail(new String(Base64.getEncoder().encode(email.getBytes(StandardCharsets.UTF_8))));
    }
  }
}
