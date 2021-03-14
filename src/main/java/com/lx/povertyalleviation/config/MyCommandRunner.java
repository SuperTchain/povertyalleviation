//package com.lx.authoritymanagement.config;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName MyCommandRunner
// * @Description TODO
// * @Author ASUS
// * @Date 2020/6/2 0:39
// * @Version 1.0
// * 程序运行自动打开谷歌浏览器
// */
//@Component
//public class MyCommandRunner implements CommandLineRunner {
//    /**
//     * 开启日志
//     */
//    private static Logger logger = Logger.getLogger(MyCommandRunner.class);
//
//    /**
//     * 页面访问路径
//     */
//    @Value("${spring.web.loginurl}")
//    private String loginUrl;
//
//    /**
//     * 谷歌浏览器位置
//     */
//    @Value("${spring.web.googleexcute}")
//    private String googleExcutePath;
//
//    /**
//     * 是否开启自动打开浏览器
//     */
//    @Value("${spring.auto.openurl}")
//    private boolean isOpen;
//
//    @Override
//    public void run(String... args) throws Exception {
//        if(isOpen){
//            String cmd = googleExcutePath +" "+ loginUrl;
//            Runtime run = Runtime.getRuntime();
//            try{
//                run.exec(cmd);
//                logger.debug("启动浏览器打开项目成功");
//            }catch (Exception e){
//                e.printStackTrace();
//                logger.error(e.getMessage());
//            }
//        }
//    }
//}
