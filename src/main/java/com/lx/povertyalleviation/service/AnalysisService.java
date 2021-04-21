package com.lx.povertyalleviation.service;


import java.math.BigDecimal;

public interface AnalysisService {
    /**
     * 查询所有销售额
     * @return 销售额
     */
    BigDecimal findAllSaleOfMonth();

    /**
     * 查本月分段销售额
     * @param days1 起始时间
     * @param days2 结尾时间
     * @return 销售额
     */
    BigDecimal findPartOfSale(String days1,String days2);
}
