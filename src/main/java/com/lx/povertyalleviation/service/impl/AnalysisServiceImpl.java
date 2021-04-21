package com.lx.povertyalleviation.service.impl;

import com.lx.povertyalleviation.dao.AnalysisDao;
import com.lx.povertyalleviation.service.AnalysisService;
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
}
