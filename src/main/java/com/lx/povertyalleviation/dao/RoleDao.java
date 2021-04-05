package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.Role;
import com.lx.povertyalleviation.pojo.Role;
import com.lx.povertyalleviation.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    /**
     * 分页查询所有角色
     * @return 角色列表信息
     */
    List<Role> findAllRoleByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询角色总数
     * @return 总数
     */
    Integer selectCount();
    
    /**
     * 根据account查询角色信息
     * @param roleId
     * @return
     */
    List<Role> findRoleByRoleId(String roleId);


    /**
     * 添加角色时,添加角色信息
     * @return
     */
    Integer addRoleInfo(Role role);

    /**
     * 根据搜索条件查询角色
     * @param roleid 角色id
     * @param rolename 角色姓名
     * @param start 开始页数
     * @param limit 每页页数
     * @return 结果
     */
    List<Role> search(@Param("roleid") Integer roleid, @Param("rolename") String rolename, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据搜索条件查询数量
     * @param roleid 角色id
     * @param rolename 角色姓名
     * @return 查询结果
     */
    Integer searchCountLike(@Param("roleid") Integer roleid,@Param("rolename") String rolename);

    /**
     * 添加角色
     * @param role 角色信息
     * @return 结果
     */
    Integer addRole(Role role);

    /**
     * 根据角色id批量删除角色
     * @param ids 角色id数组
     * @return 结果
     */
    Integer batchDeleteByRoleId(@Param("ids") String[] ids);


    /**
     * 根据角色id删除角色
     * @param id 角色Id
     * @return 结果
     */
    Integer deleteById(Integer id);

    /**
     * 根据角色id查询角色信息
     * @param id 角色Id
     * @return 结果
     */
    Role findRoleById(Integer id);

    /**
     * 更新角色信息
     * @param role 角色实体
     * @return 结果
     */
    Integer updateRole(Role role);

    /**
     * 根据账户名查询信息
     * @param account 账户名
     * @return 角色信息
     */
    Role findRoleByName(String account);
}
