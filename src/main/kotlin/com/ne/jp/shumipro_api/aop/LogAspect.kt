package com.ne.jp.shumipro_api.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
@Aspect
class LogAspect {
    @Before("execution(* *..*Controller.*(..))")
    fun startLog(joinPoint: JoinPoint) {
        val logger: Logger = LoggerFactory.getLogger(joinPoint.javaClass)
        logger.info("Start:" + joinPoint.signature.name)
    }

    @After("execution(* *..*Controller.*(..))")
    fun endLog(joinPoint: JoinPoint) {
        val logger: Logger = LoggerFactory.getLogger(joinPoint.javaClass)
        logger.info("End:" + joinPoint.signature.name)
    }
}
