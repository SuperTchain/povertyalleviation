package com.lx.povertyalleviation.utils;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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

    /**
     * 获取本月第一天
     * @return
     */
    public static LocalDate getFirstDay(){
        LocalDate today = LocalDate.now();
        //本月的第一天
        LocalDate firstDay = LocalDate.of(today.getYear(),today.getMonth(),1);
        return firstDay;
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public static LocalDate getLastDay(){
        LocalDate today = LocalDate.now();
        //本月的最后一天
        LocalDate lastDay =today.with(TemporalAdjusters.lastDayOfMonth());
        return lastDay;
    }


    /**
     * 传入的Localdate时间基础上加10天
     * @param localDate
     * @return
     */
    public static LocalDate getPlusLocalDate(LocalDate localDate,Long num){
        return localDate.plusDays(num);
    }


    public static void main(String[] args) {

        String days1 = DateUtil.localDateTimeToString(DateUtil.getFirstDay());
        String days2 = DateUtil.localDateTimeToString(DateUtil.getPlusLocalDate(DateUtil.getFirstDay(),9L));
        String days3=DateUtil.localDateTimeToString(DateUtil.getPlusLocalDate(DateUtil.getFirstDay(),19L));
        System.out.println(days1+" "+days2+" "+days3);
    }

    /**
     * 将当前Localdate转化为string
     * @param localDate
     * @return
     */
    public static String localDateTimeToString(LocalDate localDate){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = localDate.format(fmt);
        return dateStr;
    }
}
