package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.pojo.Role;
import com.lx.povertyalleviation.service.RoleService;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/role")
public class RoleController {
    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(RoleController.class);
    
    @Autowired
    private RoleService roleService;



    @GetMapping("/findAllRoles")
    @ResponseBody
    @ApiOperation(value = "查询所有角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数"),
            @ApiImplicitParam(name = "limit",value = "每页页数")
    })
    @RecordOperation(name = "查询所有角色列表",url = "/role/findAllRoles")
    public Result findAllRole(Integer page, Integer limit) {
        Result result = roleService.findAllRoleByPage(page, limit);
        logger.info("查询角色列表成功");
        return result;
    }


    @GetMapping("/toRoleList")
    @ApiOperation(value = "跳转到角色界面")
    public String tuRoleList() {
        return "role/roleList";
    }


    @GetMapping("/toAddRole")
    @ApiOperation(value = "跳转到添加界面")
//    @Secured("ROLE_ADMIN")
    public String toaddRole() {
        return "role/addRole";
    }


    @GetMapping("/toViewRole")
    @ApiOperation(value = "跳转到查看界面")
    public String toCheckRole() {
        return "role/viewRole";
    }


    @GetMapping("/toEditRole")
    @ApiOperation(value = "跳转到编辑界面")
    public String toEditRole() {
        return "role/editRole";
    }



    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "根据条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Roleid", value = "角色Id", dataType = "Integer"),
            @ApiImplicitParam(name = "Rolename", value = "角色姓名", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "根据传入条件进行角色搜索",url = "/role/search")
    public Result serachRole(Integer Roleid, String Rolename, Integer page, Integer limit) {
        Result result = roleService.search(Roleid, Rolename, page, limit);
        logger.info("角色条件搜索查询成功");
        return result;
    }



    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色")
    @ResponseBody
    @RecordOperation(name = "添加角色",url = "/role/addRole")
//    @Secured({"ROLE_SALES","ROLE_ADMIN","ROLE_MANAGE","ROLE_USER"})
    public Result addRole(@ApiParam(name = "role", value = "角色实体类") Role Role) {
        Result result = roleService.addRole(Role);
        logger.info("成功添加角色");
        result.setStatus(200);
        return result;
    }


    @PostMapping("/batchDelete")
    @ResponseBody
    @ApiOperation(value = "批量删除角色")
    @RecordOperation(name = "批量删除角色",url = "/role/batchDelete")
    public Result batchDeleteByRoleId(@ApiParam(name = "ids", value = "角色名数组") String[] ids) {
        Result result = roleService.batchDeleteByRoleId(ids);
        logger.info("成功批量删除角色");
        result.setStatus(200);
        result.setItem("批量删除成功");
        return result;
    }


    @PostMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value = "删除角色")
    @RecordOperation(name="删除角色",url = "/role/deleteById")
    public Result deleteById(@ApiParam(name = "id", value = "角色Id") Integer id) {
        Result result = roleService.deleteById(id);
        logger.info("成功删除角色");
        return result;
    }


    @PostMapping("/findRoleById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询角色")
    @RecordOperation(name = "根据Id查询角色信息",url = "/role/findRoleById")
    public Result findRoleById(@ApiParam(name = "id", value = "角色Id") Integer id) {
        Result result = roleService.findRoleById(id);
        logger.info("根据角色ID查询成功");
        return result;
    }


    @PostMapping("/updateRole")
    @ResponseBody
    @ApiOperation(value = "更新角色信息")
    @RecordOperation(name = "更新角色信息",url = "/role/updateRole")
    public Result updateRole(@ApiParam(name = "role", value = "角色实体类")Role role) {
        logger.info(role.getId()+role.getRoleDesc()+role.getRoleName());
        Result result = roleService.updateRole(role);
        logger.info("更新角色成功" + result);
        return result;
    }
}
