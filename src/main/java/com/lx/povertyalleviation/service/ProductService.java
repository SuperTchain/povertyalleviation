package com.lx.povertyalleviation.service;

import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.utils.Result;

public interface ProductService {

    /**
     * 查询所有产品信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    Result findAllProduct(Integer page, Integer limit);

    /**
     * 根据传入的条件进行模糊查询
     * @param productId 产品id
     * @param productName 产品名称
     * @param productKind 产品类别
     * @param page 页数
     * @param limit 每页页数
     * @return 封装结果
     */
    Result search(Integer productId, String productName,String productKind, Integer page, Integer limit);

    /**
     * 商家添加产品
     * @param product 产品实体类
     * @return 结果
     */
    Result addProduct(Product product,Integer userId);

    /**
     * 根据产品ID查询信息
     * @param id 产品ID
     * @return 结果
     */
    Result findProductById(Integer id);

    /**
     * 根据产品ID删除产品
     * @param id 产品ID
     * @return 结果
     */
    Result deleteById(Integer id);

    /**
     * 批量删除产品
     * @param ids 产品ID数组
     * @return 结果
     */
    Result batchDeleteByProductId(String[] ids);

    /**
     * 更新产品信息
     * @param product 产品实体类
     * @return 封装结果
     */
    Result updateProduct(Product product);

    /**
     * 根据产品种类查找产品
     * @param productKind 类别
     * @param hotSaleStatus 热销状态
     * @return 结果
     */
    Result findProductByCondition(String productKind,Integer hotSaleStatus,Integer page, Integer limit);

    Result findProductListById(Integer userId, Integer page, Integer limit);

    Result findProductLikeName(String productName, Integer page, Integer limit);
}
