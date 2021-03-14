package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao {
    /**
     * 分页查询所有用户
     * @return 用户列表信息
     */
    List<User> findAllUserByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询用户总数
     * @return 总数
     */
    Integer selectCount();

    /**
     * 根据搜索条件查询用户
     * @param userid 用户id
     * @param username 用户姓名
     * @param account 用户账号
     * @param start 开始页数
     * @param limit 每页页数
     * @return 结果
     */
    List<User> search(@Param("userid") Integer userid,@Param("username") String username, @Param("account")String account, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据搜索条件查询数量
     * @param userid 用户id
     * @param username 用户姓名
     * @param account 用户账号
     * @return 查询结果
     */
    Integer searchCountLike(@Param("userid") Integer userid,@Param("username") String username,@Param("account")String account);

    /**
     * 添加用户
     * @param user 用户信息
     * @return 结果
     */
    Integer addUser(User user);

    /**
     * 根据用户id批量删除用户
     * @param ids 用户id数组
     * @return 结果
     */
    Integer batchDeleteByUserId(@Param("ids") String[] ids);


    /**
     * 根据用户id删除用户
     * @param id 用户Id
     * @return 结果
     */
    Integer deleteById(Integer id);

    /**
     * 根据用户id查询用户信息
     * @param id 用户Id
     * @return 结果
     */
    User findUserById(Integer id);

    /**
     * 更新用户信息
     * @param user 用户实体
     * @return 结果
     */
    Integer updateUser(User user);

    /**
     * 根据账户名查询信息
     * @param account 账户名
     * @return 用户信息
     */
    User findUserByName(String account);

    /**
     * 添加邮箱验证码
     * @param code 验证码
     * @param account 账户
     * @param date 时间
     * @return
     */
    Integer addValidateCode(@Param("code") String code,@Param("account")String account,@Param("date") Date date);

    /**
     * 从数据库读取邮箱验证码
     * @param account 账户
     * @return
     */
    String findValidateCode(String account);

    /**
     * 更新用户密码
     * @param pwd
     * @param account
     * @return
     */
    Integer updateUserPwd(String pwd,String account);


    /**
     * 删除验证码
     * @param account
     * @return
     */
    Integer deleteValidate(String account);
}
