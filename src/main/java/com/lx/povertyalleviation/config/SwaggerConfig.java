package com.lx.povertyalleviation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author lx
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 读取application中的激活环境
     */
    @Value("${swagger.enable}")
    private boolean enableSwagger;

    /**
     * 配置swagger的docket bean实例
     *
     * @return docket实例
     */

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户1");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户2");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户3");
    }

    @Bean
    public Docket docket4(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户4");
    }



    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                //加载页面配置信息
                .apiInfo(apiInfo())
                //根据当前环境进行判断是否加载swagger
                .enable(enableSwagger)
                //扫描那些东西
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage 配置要扫描的包
                //any 扫描全部
                //none 不扫描
                //withClassAnnotation 扫描类注解，参数是一个注解的反射对象
                //withMethodAnnotation 扫描方法注解
                //enable 开启swagger
                //分组组名
                .groupName("lx")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lx.povertyalleviation.controller"))
                //扫描指定路径
//                .paths(PathSelectors.ant("/lx/**"))
                .build();
    }

    /**
     * 构造器修改ApiInfo值
     *
     * @return 配置信息
     */
    private ApiInfo apiInfo() {
//        //作者信息
//        Contact contact = new Contact("lx", "127.0.0.1:8080", "2401700911@qq.com");
        //配置信息
        return new ApiInfoBuilder()
                .title("接口总览")
                .description("测试")
                .version("1.0")
                .build();

    }
}
