package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.pojo.User;
import com.lx.povertyalleviation.service.UserService;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName UserController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/5/27 10:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(UserController.class);
    /**
     * 引入服务
     */
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @param page  页数
     * @param limit 每页页数
     * @return 封装的结果集
     */
    @GetMapping("/findAllUser")
    @ResponseBody
    @ApiOperation(value = "查询所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数"),
            @ApiImplicitParam(name = "limit",value = "每页页数")
    })
    @RecordOperation(name = "查询所有用户",url = "/user/findAllUser")
    public Result findAllUser( Integer page,  Integer limit) {
        Result result = userService.findAllUserByPage(page, limit);
        logger.info("查询用户列表成功");
        return result;
    }

    /**
     * 跳转到用户列表界面
     *
     * @return 用户列表界面
     */
    @GetMapping("/toUserList")
    @Secured({"ROLE_ADMIN","ROLE_MANAGE"})
    @ApiOperation(value = "跳转到用户界面")
    public String tuUserList() {
        return "user/userList";
    }

    /**
     * 跳转到添加用户界面
     *
     * @return 添加界面
     */
    @GetMapping("/toAddUser")
    @ApiOperation(value = "跳转到添加界面")
    @Secured("ROLE_ADMIN")
    public String toaddUser() {
        return "user/addUser";
    }

    /**
     * 跳转到查看用户界面
     *
     * @return 查看界面
     */
    @GetMapping("/toViewUser")
    @Secured({"ROLE_ADMIN","ROLE_MANAGE"})
    @ApiOperation(value = "跳转到查看界面")
    public String toCheckUser() {
        return "user/viewUser";
    }

    /**
     * 跳转都用户编辑界面
     *
     * @return 编辑界面
     */
    @GetMapping("/toEditUser")
    @Secured({"ROLE_ADMIN"})
    @ApiOperation(value = "跳转到编辑界面")
    public String toEditUser() {
        return "user/editUser";
    }

    @GetMapping("/toUpdatePersonMsg")
    @ApiOperation(value = "跳转到编辑界面")
    public String toUpdatePersonMsg(){
        return "user/updatePersonMsg";
    }


    /**
     * 根据传入的条件进行搜索
     *
     * @param userid   用户id
     * @param username 用户姓名
     * @param account  用户账号
     * @param page     页数
     * @param limit    每页条数
     * @return 查询结果
     */
    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "根据条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户Id", dataType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户姓名", dataType = "String"),
            @ApiImplicitParam(name = "account", value = "用户账号", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "根据传入条件进行用户搜索",url = "/user/search")
    public Result serachUser(Integer userid, String username, String account, Integer page, Integer limit) {
        Result result = userService.search(userid, username, account, page, limit);
        logger.info("用户条件搜索查询成功");
        return result;
    }


    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 添加结果
     */
    @PostMapping("/addUser")
    @ResponseBody
    @ApiOperation(value = "添加用户")
    @RecordOperation(name = "添加用户",url = "/user/addUser")
    @Secured({"ROLE_SALES","ROLE_ADMIN","ROLE_MANAGE"})
    public Result addUser(@ApiParam(name = "user", value = "用户实体类") User user, HttpServletRequest request) {
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        Result result = userService.addUser(user,roleId);
        logger.info("成功添加用户");
        result.setStatus(200);

        return result;
    }

    /**
     * 批量删除
     *
     * @param ids 删除id数组
     * @return 封装结果集
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    @ApiOperation(value = "批量删除用户")
    @RecordOperation(name = "批量删除用户",url = "/user/batchDelete")
    public Result batchDeleteByUserId(@ApiParam(name = "ids", value = "用户名数组") String[] ids) {
        Result result = userService.batchDeleteByUserId(ids);
        logger.info("成功批量删除用户");
        result.setStatus(200);
        result.setItem("批量删除成功");
        return result;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 封装结果集
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value = "删除用户")
    @RecordOperation(name="删除用户",url = "/user/deleteById")
    public Result deleteById(@ApiParam(name = "id", value = "用户Id") Integer id) {
        Result result = userService.deleteById(id);
        logger.info("成功删除用户");
        return result;
    }

    /**
     * 根据Id查询用户信息
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("/findUserById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询用户")
    @RecordOperation(name = "根据Id查询用户信息",url = "/user/findUserById")
    public Result findUserById(@ApiParam(name = "id", value = "用户Id") Integer id) {
        Result result = userService.findUserById(id);
        logger.info("根据用户ID查询成功");
        return result;
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 封装结果
     */
    @PostMapping("/updateUser")
    @ResponseBody
    @ApiOperation(value = "更新用户信息")
    @RecordOperation(name = "更新用户信息",url = "/user/updateUser")
    public Result updateUser(@ApiParam(name = "user", value = "用户实体类") User user) {
        Result result = userService.updateUser(user);
        logger.info("更新用户成功" + result);
        return result;
    }
}
