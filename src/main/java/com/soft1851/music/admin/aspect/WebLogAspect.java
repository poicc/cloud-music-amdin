package com.soft1851.music.admin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/30
 * @Version 1.0
 */
@Slf4j
@Component
@Order(100)
@Aspect
public class WebLogAspect {
    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
    /**
     * 定义切点，横切controller包下所有公有方法
     */
    @Pointcut("execution(public * com.soft1851.music.admin..admin.controller..*.*(..))")
    public void webLog(){
    }

    @Before(value = "webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接收请求，获得请求的request对象
        RequestAttributes at = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) at;
        //以下通过连接点和注解获取相关信息
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        log.info("token:" + request.getHeader("token"));
        log.info("请求URI:" + request.getRequestURI());
        log.info("请求URL:" + request.getRequestURL());
        log.info("请求头的User-Agent:" + request.getHeader("User-Agent"));
        log.info("请求方法:" + request.getMethod());
        log.info("请求地址:" + request.getRemoteAddr());
        log.info("连接点对象通过反射获得类名和方法名" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("AOP拦截获得参数:" + Arrays.toString(joinPoint.getArgs()));
        // 定义一个map用来记录日志信息，并将其put入threadLocal
        Map<String, Object> map = new HashMap<>(16);
        map.put("uri", request.getRequestURI());
        map.put("url", request.getRequestURL());
        map.put("user-agent", request.getHeader("User-Agent"));
        map.put("request-method", request.getMethod());
        map.put("remote-address", request.getRemoteAddr());
        map.put("class-method", joinPoint.getSignature().getDeclaringTypeName() +  "."
                + joinPoint.getSignature().getName());
        map.put("arguments", Arrays.toString(joinPoint.getArgs()));
        threadLocal.set(map);
    }
}
