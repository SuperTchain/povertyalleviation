package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.Policy;
import com.lx.povertyalleviation.pojo.Product;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyDao {

    /**
     * 分页查询所有政策信息
     * @param start 开始页面
     * @param limit 条数
     * @return 政策信息列表
     */
    List<Policy> findAllPolicyList(@Param("start") Integer start, @Param("limit") Integer limit);


    /**
     * 查询政策数量
     *
     * @return 数量
     */
    Integer selectCount();


    /**
     * 根据传入条件模糊查询政策信息
     * @param policyTitle 发布标题
     * @param publisher 发布人
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param start 开始页面
     * @param limit 条数
     * @return 政策信息
     */
    List<Policy> search(@Param("policyTitle")String policyTitle, @Param("publisher") String publisher,@Param("startTime") String startTime, @Param("endTime") String endTime,  @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据传入条件模糊查询数量
     *
     * @param policyTitle   政策标题
     * @param publisher 发布人
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @return 数量
     */
    Integer searchCountLike(@Param("policyTitle") String policyTitle, @Param("publisher") String publisher, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 添加政策
     * @param policy 政策信息
     * @return 添加信息
     */
    Integer addPolicy(Policy policy);

    /**
     * 根据政策ID查询政策信息
     * @param id 政策ID
     * @return 政策信息
     */
    Policy findPolicyById(Integer id);

    /**
     * 根据政策类别查询政策信息
     * @param policyKind 政策类别
     * @return 政策信息
     */
    List<Policy> findAllPolicyByKind(@Param("policyKind")Integer policyKind, @Param("start") Integer start, @Param("limit") Integer limit);



    /**
     * 根据政策类别查询政策数量
     *
     * @return 数量
     */
    Integer selectCountByKind(Integer policyKind);

    /**
     * 根据政策ID删除产品
     * @param id 政策ID
     * @return 影响数量
     */
    Integer deleteById(Integer id);

    /**
     * 根据传入政策ID数组批量删除产品
     * @param ids 政策ID数组
     * @return 影响数量
     */
    Integer batchDeleteByPolicyId(@Param("ids")String[] ids);

    /**
     * 更新政策信息
     * @param policy 政策实体类
     * @return 影响结果
     */
    Integer updatePolicy(Policy policy);

}
