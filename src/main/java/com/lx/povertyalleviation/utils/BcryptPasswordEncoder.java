package com.lx.povertyalleviation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @ClassName BcryptPasswordEncoder
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/8 11:42
 * @Version 1.0
 */
public class BcryptPasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode = passwordEncoder.encode("Csmm1234");
        System.out.println(encode);
    }

}
