package com.lx.povertyalleviation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.povertyalleviation.pojo.Policy;
import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.utils.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @since 2021-02-27
 */
public interface PolicyService  {
    /**
     * 查询所有政策信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    Result findAllPolicy(Integer page, Integer limit);


    /**
     * 根据传入的条件进行模糊查询
     * @param policyTitle 标题
     * @param publisher 发布人
     * @param timerange 时间
     * @param page 页数
     * @param limit 条数
     * @return 封装结果
     */
    Result search(String policyTitle, String publisher, String timerange, Integer page, Integer limit);

    /**
     * 添加政策
     * @param policy 政策实体类
     * @return 结果
     */
    Result addPolicy(Policy policy);

    /**
     * 根据政策ID查询信息
     * @param id 政策ID
     * @return 结果
     */
    Result findPolicyById(Integer id);

    /**
     * 根据政策类别查询信息
     * @param policyKind 政策类别
     * @return 结果
     */
    Result findPolicyByPolicyKind(Integer policyKind,Integer page, Integer limit);

    /**
     * 根据政策ID删除政策信息
     * @param id 政策ID
     * @return 结果
     */
    Result deletePolicyById(Integer id);

    /**
     * 批量删除政策信息
     * @param ids 政策信息ID数组
     * @return 结果
     */
    Result batchDeleteByPolicyId(String[] ids);

    /**
     * 更新政策信息
     * @param policy 政策体类
     * @return 封装结果
     */
    Result updatePolicy(Policy policy);

}
