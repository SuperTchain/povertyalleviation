package com.lx.povertyalleviation.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccesHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws ServletException, IOException {
        RequestCache requestCache = new HttpSessionRequestCache();
        // 获取拦截前的访问对象
        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest,httpServletResponse);
        if(savedRequest != null){
        // String url = savedRequest.getRedirectUrl();
        }else{
            // 失败重定向
            getRedirectStrategy().sendRedirect(httpServletRequest,httpServletResponse,"/index");
        }
        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }

}

