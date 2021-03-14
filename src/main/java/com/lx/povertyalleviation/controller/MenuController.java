package com.lx.povertyalleviation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MenuController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/6 15:19
 * @Version 1.0
 */
@Controller
@RequestMapping("/menu")
@Api(tags = "菜单模块")
public class MenuController {

    /**
     * 跳转到菜单界面
     * @return 菜单界面
     */
    @GetMapping("/toMenuList")
    @ApiOperation(value = "跳转到菜单界面")
    public String toMenuList(){
        return "menu/menuList";
    }
}
