package com.lx.povertyalleviation.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-12 16:30
 * 声明一个验证码异常，用于抛出特定的验证码异常
 */
public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException(String msg) {
        super(msg);
    }
}
