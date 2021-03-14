package com.lx.povertyalleviation.annotations;

import java.lang.annotation.*;

/**
 * @ClassName RecordOperation
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/31 18:44
 * @Version 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOperation {
    //详细操作
    String name() default "";
    //操作地址
    String url() default "";
}
