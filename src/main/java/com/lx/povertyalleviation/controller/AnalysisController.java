package com.lx.povertyalleviation.controller;

import com.lx.povertyalleviation.service.AnalysisService;
import com.lx.povertyalleviation.utils.DateUtil;
import com.lx.povertyalleviation.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/analysis")
@Controller
public class AnalysisController {
    /**
     * 引入日志
     */
    private static Logger logger = Logger.getLogger(AnalysisController.class);
    private int lastweekday;

    @Resource
    private Result result;

    @Resource
    private AnalysisService analysisService;

    @GetMapping("/toAnalysis")
    public String  toAnalysis(){
        return "analysis/analysis";
    }


    /**
     * layui 和 echart结合，展示图表
     *
     * 1.展示本月各个星期的销售金额--直方图
     *
     * 2.展示 销售总额 vs 广告总费用--饼状图
     * @return
     */
    @PostMapping("/layuiEchart")
    @ResponseBody
    public Result learnLayuiAndEchart() {
        /**
         * 直方图---展示每个星期的销售金额
         */
        // 设置 周数
        List<String> sale_weekList = new ArrayList<String>();
        sale_weekList.add("第1周");
        sale_weekList.add("第2周");
        sale_weekList.add("第3周");
        // 设置 每周销售金额
        List<String> sale_feeList = new ArrayList<String>();

        String days1 = DateUtil.localDateTimeToString(DateUtil.getFirstDay());
        String days2 = DateUtil.localDateTimeToString(DateUtil.getPlusLocalDate(DateUtil.getFirstDay(),9L));
        String days3=DateUtil.localDateTimeToString(DateUtil.getPlusLocalDate(DateUtil.getFirstDay(),19L));

        BigDecimal preSaleOfMonth = analysisService.findPartOfSale(days1,days2);
        BigDecimal middleSaleOfMonth = analysisService.findPartOfSale(days2,days3);
        BigDecimal lastSaleOfMonth = analysisService.findPartOfSale(days3,DateUtil.localDateTimeToString(DateUtil.getLastDay()));

        sale_feeList.add(String.valueOf(preSaleOfMonth));
        sale_feeList.add(String.valueOf(middleSaleOfMonth));
        sale_feeList.add(String.valueOf(lastSaleOfMonth));
        // 封装 销售数据
        Map<String, Object> sale_data = new LinkedHashMap<String, Object>();
        sale_data.put("week", sale_weekList);
        sale_data.put("fee", sale_feeList);

        /**
         * 饼状图---销售总额 对比 广告总费用
         */
        Map<String, Object> saleTotalFee = new LinkedHashMap<String, Object>();
        BigDecimal allSaleOfMonth = analysisService.findAllSaleOfMonth();
        saleTotalFee.put("name", "销售总额");
        saleTotalFee.put("value", allSaleOfMonth);

        Map<String, Object> adTotalFee = new LinkedHashMap<String, Object>();
        BigDecimal partOfSale = analysisService.findPartOfSale(days1, DateUtil.localDateTimeToString(DateUtil.getLastDay()));
        adTotalFee.put("name", "本月销售额");
        adTotalFee.put("value", partOfSale);
        // 封装 总额数据
        List<Map<String, Object>> feeList = new ArrayList<Map<String,Object>>();
        feeList.add(saleTotalFee);
        feeList.add(adTotalFee);

        // 封装 数据
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("sale", sale_data);
        data.put("total", feeList);
        // 返回结果
        result.setStatus(0);
        result.setItem(data);
        result.setMessage("成功获取到图表数据！");
        return result;
    }
}
