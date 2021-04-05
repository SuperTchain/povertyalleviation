package com.lx.povertyalleviation.service.impl;

import com.lx.povertyalleviation.dao.OrderDao;
import com.lx.povertyalleviation.dao.UserDao;
import com.lx.povertyalleviation.pojo.Order;
import com.lx.povertyalleviation.pojo.User;
import com.lx.povertyalleviation.service.OrderService;
import com.lx.povertyalleviation.utils.DateUtil;
import com.lx.povertyalleviation.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-11 11:55
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 开启日志
     */
    private static Logger logger= Logger.getLogger(OrderServiceImpl.class);
    
    
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;


    /**
     * 查询所有订单信息
     *
     * @param page 页数
     * @param limit 每页限制
     * @return 订单列表
     */
    @Override
    public Result findAllOrderByPage(Integer page, Integer limit) {
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        //分页查询所有订单信息集合
        List<Order> orders = orderDao.findAllOrderByPage(start, limit);
        result.setItem(orders);
        //查询订单信息的总个数
        Integer count = orderDao.selectCount();
        result.setTotal(count);
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
    @Override
    public Result search(Integer orderId, String productName, Integer orderStatus, Integer page, Integer limit) {
        Result result = new Result();
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        //分页查询所有订单信息
        List<Order> orders = orderDao.search(orderId, productName, orderStatus,start, limit);
        result.setItem(orders);
        //查询条数
        Integer count = orderDao.searchCountLike(orderId, productName, orderStatus);
        result.setTotal(count);
        return result;
    }


    /**
     * 添加订单
     * @param order 订单实体类
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addOrder(Order order) {
        Result result = new Result();
        try {
            Integer integer = orderDao.addOrder(order);
            if (integer>0){
                result.setStatus(200);
                result.setItem("添加成功");
            }else {
                result.setStatus(500);
                result.setItem("添加失败");
            }
        } catch (Exception e) {
            logger.error("错误", e);
            result.setStatus(500);
            result.setItem("添加失败");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    /**
     * 根据订单ID查询订单信息
     * @param id 订单ID
     * @return 订单信息
     */
    @Override
    public Result findOrderById(Integer id) {
        Result result = new Result();
        Order order = orderDao.findOrderById(id);
        result.setStatus(200);
        result.setItem(order);
        return result;
    }

    /**
     * 根据订单ID删除订单
     * @param id 订单ID
     * @return 影响数量
     */
    @Override
    public Result deleteById(Integer id) {
        Result result = new Result();
        try {
            orderDao.deleteById(id);
            result.setStatus(200);
            result.setItem("删除成功");
        } catch (Exception e) {
            logger.error("错误", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }


    /**
     * 根据传入订单ID数组批量删除订单
     * @param ids 订单ID数组
     * @return 影响数量
     */
    @Override
    public Result batchDeleteByOrderId(String[] ids) {
        Result result = new Result();
        try {
            orderDao.batchDeleteByOrderId(ids);
            result.setStatus(200);
            result.setItem("删除成功");
        } catch (Exception e) {
            logger.error("错误", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }

    /**
     * 更新订单信息
     * @param order 订单实体类
     * @return 影响结果
     */
    @Override
    public Result updateOrder(Order order) {
        orderDao.updateOrder(order);
        Result result = new Result();
        result.setStatus(200);
        result.setItem("更新成功");
        return result;
    }
}
