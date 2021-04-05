package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.pojo.Order;
import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.pojo.ShoppingCar;
import com.lx.povertyalleviation.pojo.User;
import com.lx.povertyalleviation.service.OrderService;
import com.lx.povertyalleviation.service.ShoppingCarService;
import com.lx.povertyalleviation.utils.DateUtil;
import com.lx.povertyalleviation.utils.RedisUtil;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Date;

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

    @Autowired
    private OrderService orderService;

    private static int ExpireTime = 60 * 60 * 24 * 7;   // redis中存储的过期时间一周

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Result result;


    @GetMapping("/toShoppingCar")
    @ApiOperation(value = "跳转到购物车界面")
    public  String toShoppingCar(){
        return "shoppingcar/shoppingCar";
    }

    /**
     * 支付
     * @param shoppingCar
     * @return
     */
    @PostMapping("/toPay")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result toPay(@RequestBody ShoppingCar shoppingCar){
        Order order = new Order();
        logger.info(shoppingCar.toString());
        order.setPayStatus(1);
        order.setDelivery(0);
        order.setAddress(shoppingCar.getProductionAddress());
        order.setId(shoppingCar.getId());
        order.setProductName(shoppingCar.getProductName());
        order.setOrderPrice(shoppingCar.getProductPrice());
        order.setNumber(shoppingCar.getProductNumber());
        order.setUserId(shoppingCar.getUserId());
        order.setOrderCreateTime(DateUtil.getNowDate());
        order.setOrderStatus(0);
        logger.info(shoppingCar.getProductPrice());

        Result result = orderService.addOrder(order);
        if (result!=null){
            redisUtil.hdel(String.valueOf(shoppingCar.getUserId()),String.valueOf(shoppingCar.getId()));
            logger.info("支付后删除");
            result.setStatus(200);
        }else {
            result.setStatus(400);
        }
        return result;
    }

    @PostMapping("/deleteById")
    @ResponseBody
    @RecordOperation(name="删除购物车",url = "/shoppingcar/deleteById")
    public Result deleteById(@ApiParam(name = "id", value = "产品Id") Integer id,Integer userId) {
        redisUtil.hdel(String.valueOf(userId),String.valueOf(id));
        logger.info("成功删除产品");
        return result;
    }


    /**
     * 添加购物车
     * @param shoppingCar
     * @param session
     * @return
     */
    @PostMapping("/addToShoppingCar")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RecordOperation(name = "添加购物车",url = "/shoppingcar/addToShoppingCar")
    public String addToShoppingCar(ShoppingCar shoppingCar, HttpSession session) {
        Result result = new Result();
        Integer userId = (Integer) session.getAttribute("userId");
        String msg=null;
        try {
            //将Id作为key 商品Id作为filed product作为value
            boolean b = redisUtil.hset(String.valueOf(userId), String.valueOf(shoppingCar.getId()), shoppingCar);
            if(true==b){
                msg="添加成功";
            }
        } catch (Exception e) {
            logger.error( "error"+e.getMessage());
            msg="添加失败";
        }
        logger.info(redisUtil.hgetAll(String.valueOf(shoppingCar.getId())));
        return msg;
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

    /**
     * 获取所有该Id下的购物车商品信息
     * @param key 商品id
     * @return 结果集
     */
    @GetMapping("/findAllShoppingCars")
    @ResponseBody
    public Result getAllShoopingCars(@RequestParam("id") String key,Integer page,Integer limit) {

        result.setItem(redisUtil.hgetAll(key));
        result.setTotal(Math.toIntExact(redisUtil.hCount(key)));
        return result;
    }


    /**
     * 设置过期时间
     * @param key
     * @return
     */
    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }
}
