package com.lx.povertyalleviation.dao;

import com.lx.povertyalleviation.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AnalysisDao {

    BigDecimal findAllSaleOfMonth();

    BigDecimal findPartOfSale(@Param("days1") String days1,@Param("days2") String days2);

    Order analysisProduct(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
