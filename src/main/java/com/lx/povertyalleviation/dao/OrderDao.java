package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.Order;
import com.lx.povertyalleviation.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author lx
 */
@Repository
public interface OrderDao {
    /**
     * 查询所有订单信息
     *
     * @param start 页数
     * @param limit 每页限制
     * @return 订单列表
     */
    List<Order> findAllOrderByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询订单数量
     *
     * @return 数量
     */
    Integer selectCount();

    /**
     * 根据传入条件模糊查询
     *
     * @param orderId   订单ID
     * @param productName 产品名称
     * @param start       开始页面
     * @param limit       每页条数
     * @return 封装结果
     */
    List<Order> search(@Param("orderId") Integer orderId, @Param("productName") String productName,  @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据传入条件模糊查询数量
     *
     * @param orderId   订单ID
     * @param productName 产品名称
     * @return 数量
     */
    Integer searchCountLike(@Param("orderId") Integer orderId, @Param("productName") String productName);

    /**
     * 添加订单
     * @param order 订单实体类
     * @return 结果
     */
    Integer addOrder(Order order);

    /**
     * 根据订单ID查询订单信息
     * @param id 订单ID
     * @return 订单信息
     */
    Order findOrderById(Integer id);

    /**
     * 根据订单ID删除订单
     * @param id 订单ID
     * @return 影响数量
     */
    Integer deleteById(Integer id);

    /**
     * 根据传入订单ID数组批量删除订单
     * @param ids 订单ID数组
     * @return 影响数量
     */
    Integer batchDeleteByOrderId(@Param("ids")String[] ids);

    /**
     * 更新订单信息
     * @param order 订单实体类
     * @return 影响结果
     */
    Integer updateOrder(Order order);

    /**
     * 根据用户id查询结果
     * @param id 用户Id
     * @return 结果
     */
    List<Order> findOrderByUserId(@Param("id") Integer id,@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 发货
     * @param orderId 订单id
     * @return 结果
     */
    Integer deliveryProduct(Integer orderId,Date nowDate);

    /**
     * 根据用户id查询订单数量
     * @param id 用户id
     * @return 结果
     */
    Integer selectCountByUserId(Integer id);

    /**
     * 收货
     * @param id 订单id
     * @return 结果
     */
    Integer receiveProduct(@Param("id") Integer id,@Param("date") Date date);

    List<Product> findOrderBySalesId(Integer userId, Integer start, Integer limit);

    Integer selectCountBySalesId(Integer userId);
}
