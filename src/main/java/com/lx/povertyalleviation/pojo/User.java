package com.lx.povertyalleviation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/26 0:20
 * @Version 1.0
 */
@TableName("user")
@ApiModel(value="User对象", description="用户表")
@Repository
public class User implements Serializable,UserDetails {

   private static final long serialVersionUID = 1L;

   @ApiModelProperty(value = "用户编号，主键",example = "100000")
   @TableId(value = "id", type = IdType.AUTO)
   private Integer id;

   @ApiModelProperty(value = "用户真实姓名",example = "李兴")
   @TableField("userName")
   private String userName;

   @ApiModelProperty(value = "用户昵称",example = "SuperTchain")
   @TableField("nickName")
   private String nickName;

   @ApiModelProperty(value = "用户账号",example = "admin")
   @TableField("account")
   private String account;

   @ApiModelProperty(value = "用户密码",example = "123456")
   @TableField("password")
   private String password;

   @ApiModelProperty(value = "用户性别",example = "1")
   @TableField("gender")
   private Integer gender;

   @ApiModelProperty(value = "用户年龄",example = "21")
   @TableField("age")
   private Integer age;

   @ApiModelProperty(value = "用户邮箱地址",example = "SuperTchain@163.com")
   @TableField("email")
   private String email;

   @ApiModelProperty(value = "用户地址",example = "四川省成都市金牛区测试小区3-6")
   @TableField("address")
   private String address;


   private Integer roleId;

   @ApiModelProperty(value = "角色",example = "Role实体")
   private  List<Role> roles = new ArrayList<>();


   public Integer getRoleId() {
      return roleId;
   }

   public void setRoleId(Integer roleId) {
      this.roleId = roleId;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", userName='" + userName + '\'' +
              ", nickName='" + nickName + '\'' +
              ", account='" + account + '\'' +
              ", password='" + password + '\'' +
              ", gender=" + gender +
              ", age=" + age +
              ", email='" + email + '\'' +
              ", address='" + address + '\'' +
              ", roleId=" + roleId +
              ", roles=" + roles +
              '}';
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public List<Role> getRoles() {
      return roles;
   }

   public void setRoles(List<Role> roles) {
      this.roles = roles;
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

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getNickName() {
      return nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }


   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      for (Role role : roles) {
         authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
      }
      return authorities;
   }
   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return userName;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Integer getGender() {
      return gender;
   }

   public void setGender(Integer gender) {
      this.gender = gender;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }


}