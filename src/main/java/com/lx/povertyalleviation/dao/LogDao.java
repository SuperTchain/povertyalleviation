package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author lx
 */
@Repository
public interface LogDao {

    /**
     * 插入记录
     * @param username 用户名
     * @param methodOperation 操作
     * @param urlOperation 操作路径
     * @param operationTime 操作时间
     * @return 结果
     */
    Integer insertLog(@Param("username") String username,@Param("methodOperation") String methodOperation,@Param("urlOperation") String urlOperation,@Param("operationTime") Date operationTime);

    /**
     * 查询所有日志信息
     * @param start 开始
     * @param limit  每页条数
     * @return 结果
     */
    List<Log> findAllLogByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询日志条数
     * @return 数量
     */
    Integer selectCount();

    /**
     * 批量删除日志
     * @param ids 数组
     */
    void batchDeleteByLogId(@Param("ids")String[] ids);

    /**
     * 根据id查询日志信息
     * @param id id
     * @return 日志信息
     */
    Log findLogById(Integer id);

    /**
     * 根据id删除日志
     * @param id id
     */
    void deleteById(Integer id);

    /**
     * 条件搜索
     * @param userName 用户姓名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param start 页数
     * @param limit 每页条数
     * @return 日志列表
     */
    List<Log> search(@Param("userName") String userName, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 条件查询数量
     * @param userName 用户名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 数量
     */
    Integer searchCountLike(@Param("userName") String userName,@Param("startTime") String startTime,@Param("endTime") String endTime);
}
