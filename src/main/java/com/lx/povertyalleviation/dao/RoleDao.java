package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    /**
     * 根据account查询角色信息
     * @param account
     * @return
     */
    List<Role> findRoleByAccount(String account);


    /**
     * 添加用户时,添加角色信息
     * @param account 用户账号
     * @return
     */
    Integer addRoleInfo(String account);
}
