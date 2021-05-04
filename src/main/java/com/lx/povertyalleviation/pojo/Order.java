package com.lx.povertyalleviation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/27 11:29
 * @Version 1.0
 */
@TableName("order")
@ApiModel(value = "Order对象", description = "订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id，主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "下单时间")
    @TableField("orderCreateTime")
    private Date orderCreateTime;

    @ApiModelProperty(value = "发货时间")
    @TableField("orderDeliveryTime")
    private Date orderDeliveryTime;

    @ApiModelProperty(value = "商品名称")
    @TableField("productName")
    private String productName;

    @ApiModelProperty(value = "商品数量")
    @TableField("number")
    private Integer number;

    @ApiModelProperty(value = "订单状态 1 已完成， 0 未完成")
    @TableField("orderStatus")
    private Integer orderStatus;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "支付状态 1 已支付，0 未支付")
    @TableField("payStatus")
    private Integer payStatus;

    @ApiModelProperty(value = "收货时间")
    @TableField("orderEndTime")
    private Date orderEndTime;

    @ApiModelProperty(value = "下单地址")
    @TableField("address")
    private String address;


    private BigDecimal orderPrice;

    @ApiModelProperty(value = "是否发货0未发 1已发")
    private Integer delivery;


    @ApiModelProperty(value = "产品地址")
    private String productionAddress;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCreateTime=" + orderCreateTime +
                ", orderDeliveryTime=" + orderDeliveryTime +
                ", productName='" + productName + '\'' +
                ", number=" + number +
                ", orderStatus=" + orderStatus +
                ", userId=" + userId +
                ", payStatus=" + payStatus +
                ", orderEndTime=" + orderEndTime +
                ", address='" + address + '\'' +
                ", orderPrice=" + orderPrice +
                ", delivery=" + delivery +
                ", productionAddress='" + productionAddress + '\'' +
                '}';
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
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

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderDeliveryTime() {
        return orderDeliveryTime;
    }

    public void setOrderDeliveryTime(Date orderDeliveryTime) {
        this.orderDeliveryTime = orderDeliveryTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}