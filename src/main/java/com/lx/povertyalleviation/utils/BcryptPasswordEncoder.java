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

        boolean equals = passwordEncoder.matches("LiXing123456", "$2a$10$XIsk94wWaIr/z/lxju3yVO4bfJiMpfJMInJ/W0vceBhAnTFb7e8/W");
        System.out.println(equals);
    }

}
