package com.lx.povertyalleviation.service.impl;

import com.lx.povertyalleviation.dao.RoleDao;
import com.lx.povertyalleviation.dao.RoleDao;
import com.lx.povertyalleviation.pojo.Role;
import com.lx.povertyalleviation.service.RoleService;
import com.lx.povertyalleviation.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    /**
     * 引入接口
     */
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private Result result;

    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

    /**
     * 密码加密
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 查询所有角色信息
     *
     * @return 角色列表
     */
    @Override
    public Result findAllRoleByPage(Integer page, Integer limit) {

        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        Result result = new Result();
        //分页查询所有角色集合
        List<Role> role = roleDao.findAllRoleByPage(start, limit);
        result.setItem(role);
        //查询角色的总个数
        Integer count = roleDao.selectCount();
        result.setTotal(count);
        return result;
    }

    /**
     * 查询角色总数
     *
     * @return 总数
     */
    @Override
    public Integer selectCount() {
        return roleDao.selectCount();
    }

    /**
     * 根据搜索条件进行查询
     *
     * @param roleid   角色id
     * @param rolename 角色名
     * @param page     页数
     * @param limit    分页页数
     * @return result结果
     */
    @Override
    public Result search(Integer roleid, String rolename, Integer page, Integer limit) {
        Result result = new Result();
        //计算查询的起始位置
        Integer start = (page - 1) * limit;
        //分页查询所有角色集合
        List<Role> role = roleDao.search(roleid, rolename, start, limit);
        result.setItem(role);
        //查询角色的总个数
        Integer count = roleDao.searchCountLike(roleid, rolename);
        result.setTotal(count);
        return result;
    }

    /**
     * 根据搜索条件查询数量
     *
     * @param roleid   角色id
     * @param rolename 角色姓名
     * @return result结果
     */
    @Override
    public Integer searchCountLike(Integer roleid, String rolename) {
        return roleDao.searchCountLike(roleid, rolename);
    }

    /**
     * 添加角色
     *
     * @param role 角色信息
     * @return result结果
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Result addRole(Role role) {
        Result result = new Result();
        try {
            roleDao.addRoleInfo(role);
            result.setStatus(200);
            result.setItem("添加成功");
        } catch (Exception e) {
            logger.error("错误", e);
            result.setStatus(500);
            result.setItem("添加失败");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    /**
     * 批量删除角色
     *
     * @param ids 角色id数组
     * @return result结果
     */
    @Override
    public Result batchDeleteByRoleId(String[] ids) {
        Result result = new Result();
        try {
            roleDao.batchDeleteByRoleId(ids);
            result.setStatus(200);
            result.setItem("删除成功");
        } catch (Exception e) {
            logger.error("错误", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 结果
     */
    @Override
    public Result deleteById(Integer id) {
        Result result = new Result();
        try {
            roleDao.deleteById(id);
            result.setStatus(200);
            result.setItem("删除成功");
        } catch (Exception e) {
            logger.error("错误", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setItem("删除失败");
        }
        return result;
    }

    /**
     * 根湖角色Id查询角色信息
     *
     * @param id 角色Id
     * @return 封装结果
     */
    @Override
    public Result findRoleById(Integer id) {
        Result result = new Result();
        Role role = roleDao.findRoleById(id);
        result.setStatus(200);
        result.setItem(role);
        return result;
    }

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     * @return 封装结果
     */
    @Override
    public Result updateRole(Role role) {
        roleDao.updateRole(role);
        Result result = new Result();
        result.setStatus(200);
        result.setItem("更新成功");
        return result;
    }
}
