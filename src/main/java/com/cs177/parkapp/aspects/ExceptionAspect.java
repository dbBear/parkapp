package com.cs177.parkapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {

  @AfterThrowing(
      pointcut = "execution(* com.cs177.parkapp.services.*.*(..))",
      throwing = "e"
  )
  void afterThrowingExceptionInService(
      JoinPoint joinPoint,
      Throwable e
  ){
    String method = joinPoint.getSignature().toLongString();
    String[] methodSplit = method.split(" ");
    log.error("------------------------------------------------");
    log.error("@SERVICE EXCEPTION >>> {}", methodSplit[2]);
    log.error("  {}", e.toString());
    String textColor = "\u001B[35m";
//    String bgColor = "\u001B[46m";
    String reset = "\u001B[0m";
    Arrays.stream(e.getStackTrace())
        .map(trace -> textColor + trace + reset)
        .forEach(log::error);
    log.error("------------------------------------------------");
  }

}
