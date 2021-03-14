package com.lx.povertyalleviation.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;

/**
 * @author lx
 * 基于layui的需求，所定义的返回数据的格式
 * "code": res.status, //解析接口状态
 * "msg": res.message, //解析提示文本
 * "count": res.total, //解析数据长度
 * "data": res.data.item //解析数据列表
 */
@ApiModel(value = "封装的返回结果类")
@Repository
public class Result {
    /**
     * 接口状态(表格的异步数据加载要求code为0，code对应的就是status)
     */
    @ApiModelProperty(value ="接口状态",example = "200")
    private Integer status = 0;
    /**
     * 文本信息(默认成功)
     */
    @ApiModelProperty(value = "文本信息",example = "success")
    private String message = "success";
    /**
     * 数据的长度total
     */
    @ApiModelProperty(value = "数据长度",example = "20")
    private Integer total;
    /**
     * 返回给前端的数据(可能是单一对象、集合、字符串)
     */
    @ApiModelProperty(value = "返回数据",example = "item")
    private Object item;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
