package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.service.ProductService;
import com.lx.povertyalleviation.service.ShoppingCarService;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-20 18:37
 */
@Controller
@RequestMapping("/shoppingcar")
@Api(tags = "购物车模块")
public class ShoppingCarController {

    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(ProductController.class);
    /**
     * 引入服务
     */
    @Autowired
    private ShoppingCarService shoppingCarService;


    /**
     * 添加购物车
     * @param product 产品信息
     * @return
     */
    @PostMapping("/addToShoppingCar")
    public Result addToShoppingCar(Product product){
        Result result = new Result();
        return result;
    }
}
