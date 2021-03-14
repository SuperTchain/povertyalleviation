package com.lx.povertyalleviation.utils;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.service.LogService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 记录用户操作切面类
 *
 * @ClassName RecordOperationAspect
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/19 20:11
 * @Version 1.0
 */
@Component
@Aspect
public class RecordOperationAspect {
    /**
     * 引入service
     */
    @Autowired
    private LogService logService;
    /**
     * 日志监控
     */
    private static Logger logger = Logger.getLogger(RecordOperationAspect.class);

    /**
     * 抽象切点方法 到时候只代理添加注解的方法
     */
    @Pointcut("@annotation(com.lx.povertyalleviation.annotations.RecordOperation)")
    public void pt1() {
        //切点
    }


    @After("pt1()")
    public void logMessage(JoinPoint joinPoint) {
        // 1:在切面方法里面获取一个request，
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 2:通过springAOP切面JoinPoint类对象，获取该类，或者该方法，或者该方法的参数
        Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        // 3:获取方法名
        String controllerOperation = clazz.getName();
        if (clazz.isAnnotationPresent(RecordOperation.class)) {
            // 当前controller操作的名称
            controllerOperation = clazz.getAnnotation(RecordOperation.class).name();
        }
        //获取当前方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //clazz类下的所有方法
        Method[] methods = clazz.getDeclaredMethods();
        //操作
        String methodOperation = "";
        //操作地址
        String urlOperation = "";
        for (Method m : methods) {
            if (m.equals(method)) {
                methodOperation = m.getName();
                if (m.isAnnotationPresent(RecordOperation.class)) {
                    methodOperation = m.getAnnotation(RecordOperation.class).name();
                    urlOperation = m.getAnnotation(RecordOperation.class).url();
                }
            }
        }
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null) {
            logger.info(username + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
                    + request.getRemoteHost());
        } else {
            logger.info("未知用户 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
                    + request.getRemoteHost());
        }
        //获取操作时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //时间转换
        Date operationTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        logger.info("执行时间" + operationTime + "操作地址" + urlOperation);

        //将操作保存至数据库
        Integer integer = logService.insertLog(username, methodOperation, urlOperation, operationTime);
        if (integer == 1) {
            logger.info("记录操作成功");
        } else {
            logger.info("记录操作失败");
        }

    }
}
