package com.lx.povertyalleviation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcConfig
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/31 9:37
 * @Version 1.0
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 配置默认加载路径
     * @param registry 注册
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //设置ViewController的优先级,将此处的优先级设为最高,当存在相同映射时依然优先执行
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/forget.html").setViewName("forget");
    }


    /**
     * 配置静态资源访问路径（swagger映射）
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源访问路径和存放路径配置
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","classpath:/public/");
        // swagger访问配置
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/","classpath:/META-INF/resources/webjars/");
    }

}
