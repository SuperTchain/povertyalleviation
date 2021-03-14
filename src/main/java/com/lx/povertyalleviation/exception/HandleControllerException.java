package com.lx.povertyalleviation.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName HandleControllerException
 * @Description 处理403异常
 * @Author ASUS
 * @Date 2020/6/9 11:28
 * @Version 1.0
 */
@ControllerAdvice
public class HandleControllerException {
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView exceptionHandler(RuntimeException e) {
        ModelAndView mv = new ModelAndView();
        if (e instanceof AccessDeniedException) {
            //如果是权限不足异常，则跳转到权限不足页面！
            mv.setViewName("error/403");
        }
        return mv;
    }
}
