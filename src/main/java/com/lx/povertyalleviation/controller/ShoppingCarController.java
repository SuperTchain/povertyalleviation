package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.pojo.ShoppingCar;
import com.lx.povertyalleviation.service.ShoppingCarService;
import com.lx.povertyalleviation.utils.RedisUtil;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    private static int ExpireTime = 60 * 60 * 24 * 7;   // redis中存储的过期时间一周

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 添加购物车
     *
     * @param product 产品信息
     * @return
     */
    @PostMapping("/addToShoppingCar")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public Result addToShoppingCar(ShoppingCar shoppingCar, HttpSession session) {
        Result result = new Result();
        Integer userId = (Integer) session.getAttribute("userId");
        try {
            //将Id作为key 商品Id作为filed product作为value
            boolean b = redisUtil.hset(String.valueOf(userId), String.valueOf(shoppingCar.getId()), shoppingCar);
            if(true==b){
                result.setStatus(200);
            }
        } catch (Exception e) {
            logger.error( "error"+e.getMessage());
        }
        logger.info(redisUtil.hgetAll(String.valueOf(shoppingCar.getId())));
        return result;
    }

    @RequestMapping("set")
    public boolean redisset(String key, String value) {
//        UserEntity userEntity =new UserEntity();
//        userEntity.setId(Long.valueOf(1));
//        userEntity.setGuid(String.valueOf(1));
//        userEntity.setName("zhangsan");
//        userEntity.setAge(String.valueOf(20));
//        userEntity.setCreateTime(new Date());

        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key, value);
    }

    @RequestMapping("get")
    public Object redisget(String key) {
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }
}
