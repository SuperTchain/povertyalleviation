package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.pojo.Order;
import com.lx.povertyalleviation.service.OrderService;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-11 12:19
 */
@Controller
@RequestMapping("/order")
@Api(tags = "订单模块")
public class OrderController {
    /**
     * 开启日志
     */
    private static Logger logger=Logger.getLogger(OrderController.class);

    /**
     * 引入服务
     */
    @Autowired
    private OrderService orderService;

    /**
     * 跳转到订单信息列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toOrdertList")
    @ApiOperation(value = "跳转到订单信息列表界面")
    public String toOrderList() {
        return "order/orderList";
    }

    /**
     * 跳转到添加订单界面
     *
     * @return 添加订单列表界面
     */
    @GetMapping("/toAddOrder")
    @ApiOperation(value = "跳转到添加订单界面")
    public String toAddOrder() {
        return "order/addOrder";
    }

    /**
     * 跳转到查看订单界面
     * @return 界面
     */
    @GetMapping("/toViewOrder")
    @ApiOperation(value = "跳转到查看订单界面")
    public String toViewOrder(){
        return "order/viewOrder";
    }

    @GetMapping("/toEditOrder")
    @ApiOperation(value = "跳转到编辑界面")
    public String toEditOrder(){
        return "order/editOrder";
    }

    /**
     * 查询所有订单信息
     *
     * @param page  页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/findAllOrder")
    @ResponseBody
    @ApiOperation(value = "查询所有订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询所有订单信息",url = "/order/findAllOrder")
    public Result findAllOrder(Integer page, Integer limit) {
        Result result = orderService.findAllOrderByPage(page, limit);
        logger.info("查询订单列表成功");
        return result;
    }

    /**
     * 根据传入条件模糊查询
     *
     * @param orderId   订单ID
     * @param productName 产品名称
     * @param orderStatus 订单状态
     * @param page       开始页面
     * @param limit       每页条数
     * @return 封装结果
     */
    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "根据条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "Integer"),
            @ApiImplicitParam(name = "productName", value = "产品名称", dataType = "String"),
            @ApiImplicitParam(name = "Integer", value = "订单状态", dataType = "Intger"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "根据传入条件查询订单信息",url = "/order/search")
    public Result serachOrder(Integer orderId, String publisher, Integer Integer, Integer page, Integer limit) {
        Result result = orderService.search(orderId, publisher, Integer, page, limit);
        logger.info("订单条件搜索查询成功");
        return result;
    }


    /**
     * 添加订单
     * @param Order 订单实体类
     * @return 结果
     */
    @PostMapping("/addOrder")
    @ResponseBody
    @ApiOperation(value = "添加订单")
    @RecordOperation(name = "添加订单",url = "/order/addOrder")
    public Result addOrder(@ApiParam(name = "order", value = "订单实体类") Order order) {
        Result result = orderService.addOrder(order);
        logger.info("成功添加订单");
        result.setStatus(200);
        return result;
    }

    /**
     * 根据Id查询订单信息
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("/findOrderById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询订单")
    @RecordOperation(name = "根据Id查询订单信息",url = "/order/findOrderById")
    public Result findOrderById(@ApiParam(name = "id", value = "订单Id") Integer id) {
        Result result = orderService.findOrderById(id);
        logger.info("根据订单ID查询成功");
        return result;
    }

    /**
     * 删除订单
     *
     * @param id 订单id
     * @return 封装结果集
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value = "删除订单")
    @RecordOperation(name="删除订单",url = "/order/deleteById")
    public Result deleteById(@ApiParam(name = "id", value = "订单Id") Integer id) {
        Result result = orderService.deleteById(id);
        logger.info("成功删除订单");
        return result;
    }

    /**
     * 批量删除
     *
     * @param ids 删除id数组
     * @return 封装结果集
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    @ApiOperation(value = "批量删除订单")
    @RecordOperation(name = "批量删除订单",url = "/order/batchDelete")
    public Result batchDeleteByOrderId(@ApiParam(name = "ids", value = "订单名数组") String[] ids) {
        System.out.println(ids);
        Result result = orderService.batchDeleteByOrderId(ids);
        logger.info("成功批量删除订单");
        result.setStatus(200);
        result.setItem("批量删除成功");
        return result;
    }


    /**
     * 更新订单信息
     *
     * @param Order 订单信息
     * @return 封装结果
     */
    @PostMapping("/updateOrder")
    @ResponseBody
    @ApiOperation(value = "更新订单信息")
    @RecordOperation(name = "更新订单信息",url = "/order/updateOrder")
    public Result updateOrder(@ApiParam(name = "order", value = "订单实体类") Order order) {
        Result result = orderService.updateOrder(order);
        logger.info("更新订单成功" + result);
        return result;
    }
}
