package com.lx.povertyalleviation.service;

import com.lx.povertyalleviation.pojo.Order;
import com.lx.povertyalleviation.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    /**
     * 查询所有订单信息
     *
     * @param page 页数
     * @param limit 每页限制
     * @return 订单列表
     */
    Result findAllOrderByPage(Integer page, Integer limit);


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
    Result search(Integer orderId, String productName, Integer orderStatus, Integer page, Integer limit);


    /**
     * 添加订单
     * @param order 订单实体类
     * @return 结果
     */
    Result addOrder(Order order);

    /**
     * 根据订单ID查询订单信息
     * @param id 订单ID
     * @return 订单信息
     */
    Result findOrderById(Integer id);

    /**
     * 根据订单ID删除订单
     * @param id 订单ID
     * @return 影响数量
     */
    Result deleteById(Integer id);

    /**
     * 根据传入订单ID数组批量删除订单
     * @param ids 订单ID数组
     * @return 影响数量
     */
    Result batchDeleteByOrderId(String[] ids);

    /**
     * 更新订单信息
     * @param product 订单实体类
     * @return 影响结果
     */
    Result updateOrder(Order order);
}
