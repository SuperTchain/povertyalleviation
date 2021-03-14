package com.lx.povertyalleviation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-12 20:51
 */
@Repository
@TableName("shoppingcar")
@ApiModel(value="购物车", description="购物车表")
public class ShoppingCar {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id",example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "图片名称",example = "2.png")
    @TableField("imgName")
    private String imgName;


    @ApiModelProperty(value = "图片路径",example = "/img/products")
    @TableField("imgUrl")
    private String imgUrl;



    @ApiModelProperty(value = "产品id",example = "1")
    @TableId(value = "productId")
    private Integer productId;

    @ApiModelProperty(value = "产品名称",example = "大大大")
    @TableField("producName")
    private String producName;


    @Override
    public String toString() {
        return "ShoppingCar{" +
                "id=" + id +
                ", imgName='" + imgName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", productId=" + productId +
                ", producName='" + producName + '\'' +
                '}';
    }

    public String getProducName() {
        return producName;
    }

    public void setProducName(String producName) {
        this.producName = producName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
