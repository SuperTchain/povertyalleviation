package com.lx.povertyalleviation.service.impl;

import com.lx.povertyalleviation.controller.ProductController;
import com.lx.povertyalleviation.dao.LogDao;
import com.lx.povertyalleviation.pojo.Log;
import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.pojo.User;
import com.lx.povertyalleviation.service.LogService;
import com.lx.povertyalleviation.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/5 13:09
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(LogServiceImpl.class);

    /**
     * 引入dao
     */
    @Autowired
    private LogDao logDao;

    /**
     * 插入记录
     * @param username 用户名
     * @param methodOperation 操作
     * @param urlOperation 操作地址
     * @param operationTime 操作时间
     */
    @Override
    public Integer insertLog(String username, String methodOperation, String urlOperation, Date operationTime) {
        Integer integer= logDao.insertLog(username,methodOperation,urlOperation,operationTime);
        return integer==1?1:0;
    }

    /**
     * 查询所有日志信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @Override
    public Result findAllLogByPage(Integer page, Integer limit) {
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        //分页查询所有用户集合
        List<Log> logs = logDao.findAllLogByPage(start, limit);
        result.setItem(logs);
        //查询用户的总个数
        Integer count = logDao.selectCount();
        result.setTotal(count);
        return result;
    }

    /**
     * 批量删除日志
     * @param ids 日志数组
     * @return 封装结果
     */
    @Override
    public Result batchDeleteByLogId(String[] ids) {
        Result result = new Result();
        try{
            logDao.batchDeleteByLogId(ids);
            result.setStatus(200);
            result.setItem("删除成功");
        }catch (Exception e){
            logger.error("错误",e);
            //失败回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }

    /**
     * 根据id查询日志
     * @param id id
     * @return 封装结果
     */
    @Override
    public Result findLogById(Integer id) {
        Result result = new Result();
        Log log = logDao.findLogById(id);
        result.setStatus(200);
        result.setItem(log);
        return result;
    }

    /**
     * 根据id删除日志
     * @param id 日志id
     * @return 封装结果
     */
    @Override
    public Result deleteById(Integer id) {
        Result result = new Result();
        try{
            logDao.deleteById(id);
            result.setStatus(200);
            result.setItem("删除成功");
        }catch (Exception e){
            logger.error("错误",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }

    /**
     * 条件查询
     * @param userName 用户姓名
     * @param timerange 时间范围
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @Override
    public Result search(String userName, String timerange, Integer page, Integer limit) {
        //开始时间
        String startTime=null;
        //结束时间
        String endTime=null;
        String isNull="";
        //判断时间是否为空字符串
        if(!timerange.equals(isNull)){
            //根据开始时间和结束时间查询
            //使用split切割，返回数组 数组第一个元素：起始时间 数组第二个元素：结束时间
            String [] timeArray = timerange.split("~");
            //为其赋值,trim():去除前后的空格，
            startTime= timeArray[0].trim();
            endTime= timeArray[1].trim();
        }
        Result result = new Result();
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        //分页查询所有日志集合
        List<Log> logs = logDao.search(userName, startTime,endTime, start, limit);
        result.setItem(logs);
        //查询日志的总个数
        Integer count = logDao.searchCountLike(userName, startTime,endTime);
        result.setTotal(count);
        return result;
    }
}
