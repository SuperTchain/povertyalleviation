package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.pojo.ShoppingCar;
import com.lx.povertyalleviation.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lx
 */
@Repository
public interface ProductDao {

    /**
     * 查询所有产品信息
     *
     * @param start 页数
     * @param limit 每页限制
     * @return 产品列表
     */
    List<Product> findAllProductByPage(@Param("start") Integer start, @Param("limit") Integer limit);


    /**
     * 根据类别查询所有产品信息
     * @param productKind 类别
     * @param hotSaleStatus 热销状态
     * @param start 页数
     * @param limit 每页限制
     * @return 产品列表
     */
    List<Product> findAllProductByCondition(@Param("productKind")String productKind, @Param("hotSaleStatus")Integer hotSaleStatus,@Param("start") Integer start, @Param("limit") Integer limit);


    /**
     * 查询产品数量
     *
     * @return 数量
     */
    Integer selectCount();


    /**
     * 根据条件查询产品数量
     * @param productKind 产品类别
     * @param hotSaleStatus 热销状态
     * @return 数量
     */
    Integer selectCountByCondition(@Param("productKind")String productKind, @Param("hotSaleStatus")Integer hotSaleStatus);

    /**
     * 根据传入条件模糊查询
     *
     * @param productId   产品ID
     * @param productName 产品名称
     * @param productKind 产品类别
     * @param start       开始页面
     * @param limit       每页条数
     * @return 封装结果
     */
    List<Product> search(@Param("productId") Integer productId, @Param("productName") String productName,@Param("productKind") String productKind,  @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据传入条件模糊查询数量
     *
     * @param productId   产品ID
     * @param productName 产品名称
     * @param productKind 产品类别
     * @return 数量
     */
    Integer searchCountLike(@Param("productId") Integer productId, @Param("productName") String productName,@Param("productKind") String productKind);

    /**
     * 添加产品
     * @param product 产品实体类
     * @return 结果
     */
    Integer addProduct(Product product);

    /**
     * 根据产品ID查询产品信息
     * @param id 产品ID
     * @return 产品信息
     */
    Product findProductById(Integer id);

    /**
     * 根据产品ID删除产品
     * @param id 产品ID
     * @return 影响数量
     */
    Integer deleteById(Integer id);

    /**
     * 根据传入产品ID数组批量删除产品
     * @param ids 产品ID数组
     * @return 影响数量
     */
    Integer batchDeleteByProductId(@Param("ids")String[] ids);

    /**
     * 更新产品信息
     * @param product 产品实体类
     * @return 影响结果
     */
    Integer updateProduct(Product product);

    /**
     * 查询所有购物车商品信息
     * @return 结果
     */
    List<ShoppingCar> findAllShoppingCar();

    /**
     * 添加商品与商家关系映射
     * @param productName 产品名称
     * @param userId 商家信息
     * @return 结果
     */
    Integer addRealationship(@Param("productName") String productName, @Param("userId") Integer userId);

    List<Product> findProductListById(Integer userId, Integer start, Integer limit);

    Integer selectCountById(Integer userId);
}
