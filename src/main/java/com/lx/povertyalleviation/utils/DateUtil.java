package com.lx.povertyalleviation.utils;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-12 0:16
 */
@Repository
public class DateUtil {
    /**
     * 获取当前时间
     * @return
     */
     public static Date getNowDate(){
        //获取操作时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //时间转换
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
