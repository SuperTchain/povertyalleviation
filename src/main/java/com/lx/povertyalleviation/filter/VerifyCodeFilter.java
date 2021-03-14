package com.lx.povertyalleviation.filter;

import com.lx.povertyalleviation.exception.VerifyCodeException;
import com.lx.povertyalleviation.handler.MyAuthenticationFailHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-12 16:37
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {

    /**
     * 开启日志
     */
    private static Logger logger=Logger.getLogger(VerifyCodeFilter.class);

    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        if(request.getRequestURI().equals("/login")&&request.getMethod().equalsIgnoreCase("post")){
            try {
                validate(request);
            } catch (VerifyCodeException e) {
                myAuthenticationFailHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        // 3. 校验通过，就放行
        filterChain.doFilter(request, response);
    }
    /* 验证保存在session的验证码和表单提交的验证码是否一致 */
    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        String captcha = ServletRequestUtils.getStringParameter(request, "code");
        String code = (String) request.getSession().getAttribute("vareficode");
        logger.info("获取提交的code"+captcha);
        logger.info("获取保存的code"+code);
        if(!code.equalsIgnoreCase(captcha)){
            throw new VerifyCodeException("验证码不正确！");
        }
        request.getSession().removeAttribute("vareficode");
    }
}
