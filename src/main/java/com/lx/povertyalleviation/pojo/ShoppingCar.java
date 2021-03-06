package com.lx.povertyalleviation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

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

    @ApiModelProperty(value = "产品id",example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "产品名称",example = "四川篷杆")
    @TableField("productName")
    private String productName;

    @ApiModelProperty(value = "产品价格",example = "20")
    @TableField("productPrice")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "产品生产地址",example = "成都")
    @TableField("productionAddress")
    private String productionAddress;

    @ApiModelProperty(value = "产品数量",example = "20")
    @TableField("productNumber")
    private Integer productNumber;

    @ApiModelProperty(value = "产品描述",example = "又甜水分又多，真好恰")
    @TableField("productDesc")
    private String productDesc;

    @ApiModelProperty(value = "产品状态  1有余 0售罄",example = "1")
    @TableField("productStatus")
    private Integer productStatus;

    @ApiModelProperty(value = "产品图片",example = "pc1.png")
    @TableField("productImgName")
    private String productImgName;


    @ApiModelProperty(value = "产品类别 0水果 1农产品 2家畜",example = "0")
    @TableField("productKind")
    private String productKind;

    @ApiModelProperty(value = "热销产品 0 不是 1是",example = "0")
    @TableField("hotSaleStatus")
    private Integer hotSaleStatus;

    @ApiModelProperty(value = "购买数量",example = "1")
    @TableField("buyCount")
    private Integer buyCount;

    @ApiModelProperty(value = "用户iD",example = "1")
    @TableField("userId")
    private Integer userId;

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productionAddress='" + productionAddress + '\'' +
                ", productNumber=" + productNumber +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productImgName='" + productImgName + '\'' +
                ", productKind='" + productKind + '\'' +
                ", hotSaleStatus=" + hotSaleStatus +
                ", buyCount=" + buyCount +
                ", userId=" + userId +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductImgName() {
        return productImgName;
    }

    public void setProductImgName(String productImgName) {
        this.productImgName = productImgName;
    }

    public String getProductKind() {
        return productKind;
    }

    public void setProductKind(String productKind) {
        this.productKind = productKind;
    }

    public Integer getHotSaleStatus() {
        return hotSaleStatus;
    }

    public void setHotSaleStatus(Integer hotSaleStatus) {
        this.hotSaleStatus = hotSaleStatus;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }
}
