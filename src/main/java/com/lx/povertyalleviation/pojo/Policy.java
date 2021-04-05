package com.lx.povertyalleviation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@TableName("policy")
@ApiModel(value="Policy对象", description="政策表")
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "政策id，主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "政策标题")
    @TableField("policyTitle")
    private String policyTitle;

    @ApiModelProperty(value = "政策内容")
    @TableField("policyContent")
    private String policyContent;

    @ApiModelProperty(value = "发布人")
    @TableField("publisher")
    private String publisher;

    @ApiModelProperty(value = "来源")
    @TableField("source")
    private String source;

    @ApiModelProperty(value = "浏览次数")
    @TableField("viewed")
    private Integer viewed;

    @ApiModelProperty(value = "发布时间")
    @TableField("publishTime")
    private Date publishTime;

    @ApiModelProperty(value = "政策类别")
    @TableField("policyKind")
    private String policyKind;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getPolicyContent() {
        return policyContent;
    }

    public void setPolicyContent(String policyContent) {
        this.policyContent = policyContent;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPolicyKind() {
        return policyKind;
    }

    public void setPolicyKind(String policyKind) {
        this.policyKind = policyKind;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", policyTitle='" + policyTitle + '\'' +
                ", policyContent='" + policyContent + '\'' +
                ", publisher='" + publisher + '\'' +
                ", source='" + source + '\'' +
                ", viewed=" + viewed +
                ", publishTime=" + publishTime +
                ", policyKind='" + policyKind + '\'' +
                '}';
    }
}
