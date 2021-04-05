package com.lx.povertyalleviation.service;

import com.lx.povertyalleviation.pojo.Role;
import com.lx.povertyalleviation.utils.Result;

public interface RoleService {
    /**
     * 分页查询所有角色
     *
     * @return 角色列表信息
     */
    Result findAllRoleByPage(Integer page, Integer limit);

    /**
     * 查询角色总数
     *
     * @return 总数
     */
    Integer selectCount();

    /**
     * 根据条件查询角色信息
     *
     * @param roleid   角色id
     * @param rolename 角色名
     * @param page     页数
     * @param limit    分页页数
     * @return 结果
     */
    Result search(Integer roleid, String rolename,  Integer page, Integer limit);

    /**
     * 根据搜索条件查询数量
     *
     * @param roleid   角色id
     * @param rolename 角色姓名
     * @param account  角色账号
     * @return 角色数量
     */
    Integer searchCountLike(Integer roleid, String rolename);

    /**
     * 添加角色
     *
     * @param role 角色信息
     * @return 结果
     */
    Result addRole(Role role);

    /**
     * 根据角色id批量删除角色
     *
     * @param ids 角色id数组
     * @return 结果
     */
    Result batchDeleteByRoleId(String[] ids);

    /**
     * 根据角色id删除角色
     *
     * @param id 角色id
     * @return 结果
     */
    Result deleteById(Integer id);

    /**
     * 根据角色id显示角色信息
     *
     * @param id 角色Id
     * @return 封装结果
     */
    Result findRoleById(Integer id);

    /**
     * 更新角色信息
     * @param role 角色信息
     * @return 封装结果
     */
    Result updateRole(Role role);
}
