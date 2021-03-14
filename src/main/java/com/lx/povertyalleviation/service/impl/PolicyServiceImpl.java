package com.lx.povertyalleviation.service.impl;

import com.lx.povertyalleviation.dao.PolicyDao;
import com.lx.povertyalleviation.pojo.Policy;
import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.service.PolicyService;
import com.lx.povertyalleviation.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author lx
 * @version 1.0
 * @date 2021/2/27 23:19
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    /**
     *开启日志
     */
    private static Logger logger=Logger.getLogger(PolicyServiceImpl.class);

    @Autowired
    private PolicyDao policyDao;


    /**
     * 查询所有政策信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @Override
    public Result findAllPolicy(Integer page, Integer limit) {
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        //分页查询所有政策信息集合
        List<Policy> policies = policyDao.findAllPolicyList(start, limit);
        result.setItem(policies);
        //查询政策信息的总个数
        Integer count = policyDao.selectCount();
        result.setTotal(count);
        return result;
    }


    /**
     * 根据传入的条件进行模糊查询
     * @param policyTitle 标题
     * @param publisher 发布人
     * @param timerange 时间
     * @param page 页数
     * @param limit 条数
     * @return 封装结果
     */
    @Override
    public Result search(String policyTitle, String publisher, String timerange, Integer page, Integer limit) {
        //开始时间
        String startTime = null;
        //结束时间
        String endTime = null;
        String isNull = "";
        //判断时间是否为空字符串
        if (!timerange.equals(isNull)) {
            //根据开始时间和结束时间查询
            //使用split切割，返回数组 数组第一个元素：起始时间 数组第二个元素：结束时间
            String[] timeArray = timerange.split("~");
            //为其赋值,trim():去除前后的空格，
            startTime = timeArray[0].trim();
            endTime = timeArray[1].trim();
        }
        Result result = new Result();
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        //分页查询所有政策信息
        List<Policy> policies = policyDao.search(policyTitle, publisher, startTime, endTime, start, limit);
        result.setItem(policies);
        //查询条数
        Integer count = policyDao.searchCountLike(policyTitle, publisher, startTime, endTime);
        result.setTotal(count);
        return result;
    }

    /**
     * 添加政策
     * @param policy 政策实体类
     * @return 结果
     */
    @Override
    public Result addPolicy(Policy policy) {
        Result result = new Result();
        try {
            policyDao.addPolicy(policy);
            result.setStatus(200);
            result.setItem("添加成功");
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
     * 根据政策ID查询信息
     * @param id 政策ID
     * @return 结果
     */
    @Override
    public Result findPolicyById(Integer id) {
        Result result = new Result();
        Policy policy = policyDao.findPolicyById(id);
        result.setStatus(200);
        result.setItem(policy);
        return result;
    }

    /**
     * 根据政策类别查询信息
     * @param policyKind 政策类别
     * @return 结果
     */
    @Override
    public Result findPolicyByPolicyKind(Integer policyKind,Integer page, Integer limit) {
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        List<Policy> policies = policyDao.findAllPolicyByKind(policyKind, start, limit);
        result.setStatus(200);
        result.setItem(policies);
        //查询政策信息的总个数
        Integer count = policyDao.selectCountByKind(policyKind);
        result.setTotal(count);
        return result;
    }

    /**
     * 根据政策ID删除政策信息
     * @param id 政策ID
     * @return 结果
     */
    @Override
    public Result deletePolicyById(Integer id) {
        Result result = new Result();
        try {
            policyDao.deleteById(id);
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
     * 批量删除政策信息
     * @param ids 政策信息ID数组
     * @return 结果
     */
    @Override
    public Result batchDeleteByPolicyId(String[] ids) {
        Result result = new Result();
        try {
            policyDao.batchDeleteByPolicyId(ids);
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
     * 更新政策信息
     * @param policy 政策体类
     * @return 封装结果
     */
    @Override
    public Result updatePolicy(Policy policy) {
        policyDao.updatePolicy(policy);
        Result result = new Result();
        result.setStatus(200);
        result.setItem("更新成功");
        return result;
    }
}
