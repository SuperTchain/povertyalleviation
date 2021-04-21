package com.lx.povertyalleviation.service;

import com.lx.povertyalleviation.pojo.Order;
import com.lx.povertyalleviation.utils.DateUtil;
import com.lx.povertyalleviation.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
     * @param page       开始页面
     * @param limit       每页条数
     * @return 封装结果
     */
    Result search(Integer orderId, String productName,Integer page, Integer limit);


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
     * @param order 订单实体类
     * @return 影响结果
     */
    Result updateOrder(Order order);

    /**
     * 根据用户id查询订单
     * @param id 用户id
     * @return 结果
     */
    Result findOrderByUserId(Integer id,Integer page,Integer limit);

    /**
     * 商家发货
     * @param orderId 订单id
     * @return 结果
     */
    Result deliveryProduct(Integer orderId, Date nowDate);

    /**
     * 买家收货
     * @param id 订单id
     * @return 结果
     */
    Result receiveProduct(Integer id,Date nowDate);

    /**
     * 根据商家Id查询所有订单信息
     * @param userId 商家Id
     * @param page 页数
     * @param limit 条数
     * @return 结果
     */
    Result findOrderBySalesId(Integer userId, Integer page, Integer limit);
}
