package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.pojo.Policy;
import com.lx.povertyalleviation.service.PolicyService;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lx
 * @version 1.0
 * @date 2021/2/27 23:20
 */
@Controller
@Api(tags = "政策信息模块")
@RequestMapping("/policy")
public class PolicyController {

    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(PolicyController.class);

    /**
     * 引入服务
     */
    @Autowired
    private PolicyService policyService;

    /**
     * 跳转到国家政策信息列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toFindPolicyByPolicyKind1")
    @ApiOperation(value = "跳转到政策信息列表界面")
    public String toPolicyList1() {
        return "policy/PolicyList1";
    }


    /**
     * 跳转到查看产品界面
     * @return 界面
     */
    @GetMapping("/toViewPolicy")
    @ApiOperation(value = "跳转到查看政策界面")
    public String toViewPolicy(HttpServletRequest request){
        String productId = request.getParameter("id");
        HttpSession session = request.getSession();
        session.setAttribute("policyId",productId);
        return "policy/viewPolicy";
    }



    @GetMapping("/toPolicyList")
    @ApiOperation(value = "跳转到政策界面")
    public String toProductList() {
        return "policy/policyList";
    }

    @GetMapping("/toViewPolicy2")
    @ApiOperation(value = "跳转到政策界面")
    public String toViewList2() {
        return "policy/viewPolicy2";
    }




    /**
     * 跳转到扶贫政策信息列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toFindPolicyByPolicyKind2")
    @ApiOperation(value = "跳转到扶贫政策信息列表界面")
    public String toPolicyList2() {
        return "policy/PolicyList2";
    }

    /**
     * 跳转到地方政策信息列表界面
     *
     * @return 列表界面
     */
    @GetMapping("/toFindPolicyByPolicyKind3")
    @ApiOperation(value = "跳转到地方政策信息列表界面")
    public String toPolicyList3() {
        return "policy/PolicyList3";
    }

    /**
     * 跳转到添加政策界面
     *
     * @return 添加政策列表界面
     */
    @GetMapping("/toAddPolicy")
    @ApiOperation(value = "跳转到添加政策界面")
    public String toAddPolicy() {
        return "policy/addPolicy";
    }



    @GetMapping("/toEditPolicy")
    @ApiOperation(value = "跳转到编辑界面")
    public String toEditPolicy() {
        return "policy/editPolicy";
    }

    /**
     * 查询所有政策信息
     *
     * @param page  页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/findAllPolicy")
    @ResponseBody
    @ApiOperation(value = "查询所有政策信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询所有政策信息", url = "/policy/findAllPolicy")
    public Result findAllPolicy(Integer page, Integer limit) {
        Result result = policyService.findAllPolicy(page, limit);
        logger.info("查询政策列表成功");
        return result;
    }

    /**
     * 根据传入条件查询政策信息
     *
     * @param policyTitle 政策ID
     * @param publisher  政策名称
     * @param timerange   时间范围
     * @param page        页数
     * @param limit       每页条数
     * @return 封装结果
     */
    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "根据条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "policyTitle", value = "政策标题", dataType = "String"),
            @ApiImplicitParam(name = "publisher", value = "发布人", dataType = "String"),
            @ApiImplicitParam(name = "timerange", value = "时间范围", dataType = "String", example = "2020-05-30~2020-05-31"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "根据传入条件查询政策信息", url = "/policy/search")
    public Result serachPolicy(String policyTitle, String publisher, String timerange, Integer page, Integer limit) {
        logger.info(policyTitle+publisher+timerange);
        Result result = policyService.search(policyTitle, publisher, timerange, page, limit);
        logger.info("政策条件搜索查询成功");
        return result;
    }


    /**
     * 添加政策
     *
     * @param policy 政策实体类
     * @return 结果
     */
    @PostMapping("/addPolicy")
    @ResponseBody
    @ApiOperation(value = "添加政策")
    @RecordOperation(name = "添加政策", url = "/policy/addPolicy")
    public Result addPolicy(@ApiParam(name = "policy", value = "政策实体类") Policy policy) {
        Result result = policyService.addPolicy(policy);
        logger.info("成功添加政策");
        result.setStatus(200);
        return result;
    }

    /**
     * 根据Id查询政策信息
     *
     * @param id id
     * @return 结果
     */
    @PostMapping("/findPolicyById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询政策")
    @RecordOperation(name = "根据Id查询政策信息", url = "/policy/findPolicyById")
    public Result findPolicyById(@ApiParam(name = "id", value = "政策Id") Integer id) {
        Result result = policyService.findPolicyById(id);
        logger.info("根据政策ID查询成功");
        return result;
    }


    /**
     * 根据类别查询政策信息
     *
     * @param policyKind 类别
     * @return 结果
     */
    @PostMapping("/findPolicyByPolicyKind")
    @ResponseBody
    @ApiOperation(value = "根据类别查询政策")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "policyKind", value = "类别id"),
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "根据类别查询政策信息", url = "/policy/findPolicyByPolicyKind")
    public Result findPolicyByPolicyKind(Integer policyKind, Integer page, Integer limit) {
        Result result = policyService.findPolicyByPolicyKind(policyKind, page, limit);
        logger.info("根据政策类别查询成功");
        return result;
    }


    /**
     * 删除政策
     *
     * @param id 政策id
     * @return 封装结果集
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value = "删除政策")
    @RecordOperation(name = "删除政策", url = "/policy/deleteById")
    public Result deleteById(@ApiParam(name = "id", value = "政策Id") Integer id) {
        Result result = policyService.deletePolicyById(id);
        logger.info("成功删除政策");
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
    @ApiOperation(value = "批量删除政策")
    @RecordOperation(name = "批量删除政策", url = "/policy/batchDelete")
    public Result batchDeleteByPolicyId(@ApiParam(name = "ids", value = "政策名数组") String[] ids) {
        System.out.println(ids);
        Result result = policyService.batchDeleteByPolicyId(ids);
        logger.info("成功批量删除政策");
        result.setStatus(200);
        result.setItem("批量删除成功");
        return result;
    }


    /**
     * 更新用户信息
     *
     * @param policy 政策信息
     * @return 封装结果
     */
    @PostMapping("/updatePolicy")
    @ResponseBody
    @ApiOperation(value = "更新政策信息")
    @RecordOperation(name = "更新政策信息", url = "/policy/updatePolicy")
    public Result updatePolicy(@ApiParam(name = "policy", value = "政策实体类") Policy policy) {
        Result result = policyService.updatePolicy(policy);
        logger.info("更新政策成功" + result);
        return result;
    }
}
