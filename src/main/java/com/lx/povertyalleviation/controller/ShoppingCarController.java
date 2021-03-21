package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.service.ShoppingCarService;
import com.lx.povertyalleviation.utils.RedisUtil;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

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

    private static int ExpireTime = 60*60*24*7;   // redis中存储的过期时间一周

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 添加购物车
     * @param product 产品信息
     * @return
     */
    @PostMapping("/addToShoppingCar")
    public Result addToShoppingCar(Product product){
        Result result = new Result();
        //存放数据
        return result;
    }
    @RequestMapping("set")
    public boolean redisset(String key, String value){
//        UserEntity userEntity =new UserEntity();
//        userEntity.setId(Long.valueOf(1));
//        userEntity.setGuid(String.valueOf(1));
//        userEntity.setName("zhangsan");
//        userEntity.setAge(String.valueOf(20));
//        userEntity.setCreateTime(new Date());

        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}
