package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.dao.LogDao;
import com.lx.povertyalleviation.service.LogService;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName OperationController
 * @Description TODO
 * @Author ASUS
 * @Date 2020/6/5 12:55
 * @Version 1.0
 */
@Controller
@Api(tags = "日志记录")
@RequestMapping("/log")
public class LogController {

    /**
     * 引入日志
     */
    private static Logger logger = Logger.getLogger(LogController.class);

    /**
     * 引入service
     */
    @Autowired
    private LogService logService;

    /**
     * 跳转到日志界面
     *
     * @return 日志界面
     */
    @GetMapping("/toLogList")
    @ApiOperation(value = "跳转到日志列表界面")
    public String toLogList() {
        return "log/logList";
    }

    /**
     * 跳转带查看界面
     *
     * @return 查看日志界面
     */
    @GetMapping("/toViewLog")
    @ApiOperation(value = "跳转到日志查看界面")
    public String toViewLog() {
        return "log/viewLog";
    }

    /**
     * 查询所有日志信息
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/findAllLog")
    @ResponseBody
    @ApiOperation(value = "查询所有日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数"),
            @ApiImplicitParam(name = "limit", value = "每页页数")
    })
    @RecordOperation(name = "查询所有日志", url = "/log/findAllLog")
    public Result findAllLog(Integer page, Integer limit) {
        Result result = logService.findAllLogByPage(page, limit);
        logger.info("查询日志列表成功");
        return result;
    }

    /**
     * 根据日志ID查询日志信息
     * @param id 日志ID
     * @return 封装结果
     */
    @PostMapping("/findLogById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询日志")
    @RecordOperation(name = "根据Id查询日志信息",url = "/log/findLogById")
    public Result findLogById(@ApiParam(name = "id", value = "日志Id") Integer id) {
        Result result = logService.findLogById(id);
        logger.info("根据产品ID查询成功");
        return result;
    }

    /**
     * 批量删除日志
     * @param ids 日志ID数组
     * @return 封装结果
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    @ApiOperation(value = "批量删除日志")
    @RecordOperation(name = "批量删除日志", url = "/log/batchDelete")
    public Result batchDeleteByLogId(@ApiParam(name = "ids", value = "日志id数组") String[] ids) {
        Result result = logService.batchDeleteByLogId(ids);
        logger.info("成功批量删除日志");
        result.setStatus(200);
        result.setItem("批量删除成功");
        return result;
    }

    /**
     * 根据ID删除日志
     * @param id id
     * @return 封装结果
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @ApiOperation(value = "删除日志")
    @RecordOperation(name="删除日志",url = "/log/deleteById")
    public Result deleteById(@ApiParam(name = "id", value = "日志Id") Integer id) {
        Result result = logService.deleteById(id);
        logger.info("成功删除日志");
        return result;
    }


    /**
     * 条件搜索日志
     * @param userName 用户姓名
     * @param timerange 时间范围
     * @param page 页数
     * @param limit 每页条数
     * @return 封装结果
     */
    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "根据条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户姓名", dataType = "String"),
            @ApiImplicitParam(name = "timerange", value = "时间范围", dataType = "String", example = "2020-05-30~2020-05-31"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页页数", dataType = "Integer")}
    )
    @RecordOperation(name = "条件查询日志信息",url = "/log/search")
    public Result serachLog(String userName, String timerange, Integer page, Integer limit) {
        Result result = logService.search( userName, timerange, page, limit);
        logger.info("条件搜索查询成功");
        return result;
    }

}
