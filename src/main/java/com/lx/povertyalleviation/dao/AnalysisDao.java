package com.lx.povertyalleviation.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AnalysisDao {

    BigDecimal findAllSaleOfMonth();

    BigDecimal findPartOfSale(@Param("days1") String days1,@Param("days2") String days2);

    String analysisProduct(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
