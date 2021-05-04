package com.lx.povertyalleviation.service.impl;

import com.lx.povertyalleviation.dao.AnalysisDao;
import com.lx.povertyalleviation.pojo.Order;
import com.lx.povertyalleviation.service.AnalysisService;
import com.lx.povertyalleviation.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    /**
     * 开启日志
     */
    private static final Logger logger= Logger.getLogger(AnalysisServiceImpl.class);


    @Resource
    private AnalysisDao analysisDao;

    @Resource
    private Result result;

    @Override
    public BigDecimal findAllSaleOfMonth() {
        BigDecimal allSaleOfMonth = analysisDao.findAllSaleOfMonth();
        return allSaleOfMonth;
    }

    @Override
    public BigDecimal findPartOfSale(String days1, String days2) {
        BigDecimal partOfSale = analysisDao.findPartOfSale(days1, days2);
        return partOfSale;
    }

    @Override
    public Result analysisProduct(String timerange) {
        //开始时间
        String startTime=null;
        //结束时间
        String endTime=null;
        String isNull="";
        //判断时间是否为空字符串
        if(!timerange.equals(isNull)){
            //根据开始时间和结束时间查询
            //使用split切割，返回数组 数组第一个元素：起始时间 数组第二个元素：结束时间
            String [] timeArray = timerange.split("~");
            //为其赋值,trim():去除前后的空格，
            startTime= timeArray[0].trim();
            endTime= timeArray[1].trim();
        }
        Order order = analysisDao.analysisProduct(startTime, endTime);
        result.setStatus(200);
        result.setItem(order);
        return result;
    }
}
