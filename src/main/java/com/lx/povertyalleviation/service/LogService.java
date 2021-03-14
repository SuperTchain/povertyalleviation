package com.lx.povertyalleviation.service;

import com.lx.povertyalleviation.utils.Result;

import java.util.Date;

/**
 * 记录·操作业务
 */
public interface LogService {
    /**
     * 插入操作记录
     * @param username 用户名
     * @param methodOperation 操作
     * @param urlOperation 操作地址
     * @param operationTime 操作时间
     */
    Integer insertLog(String username, String methodOperation, String urlOperation, Date operationTime);

    /**
     * 查询所有日志信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    Result findAllLogByPage(Integer page, Integer limit);

    /**
     * 批量删除日志ID
     * @param ids 日志数组
     * @return 结果
     */
    Result batchDeleteByLogId(String[] ids);

    /**
     * 根据日志ID查询日志信息
     * @param id id
     * @return 查询信息
     */
    Result findLogById(Integer id);

    /**
     * 根据日志id删除日志
     * @param id 日志id
     * @return 封装结果
     */
    Result deleteById(Integer id);

    /**
     * 条件查询日志
     * @param userName 用户姓名
     * @param timerange 时间范围
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    Result search(String userName, String timerange, Integer page, Integer limit);
}
