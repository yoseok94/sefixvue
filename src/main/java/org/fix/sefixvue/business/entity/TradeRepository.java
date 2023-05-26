package org.fix.sefixvue.business.entity;

import org.fix.sefixvue.accounting.model.dto.TradeSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {


        @Query("SELECT FUNCTION('YEAR', t.tradingday), FUNCTION('MONTH', t.tradingday), SUM(t.totalprice) " +
                "FROM Trade t GROUP BY FUNCTION('YEAR', t.tradingday), FUNCTION('MONTH', t.tradingday) " +
                "ORDER BY FUNCTION('YEAR', t.tradingday), FUNCTION('MONTH', t.tradingday)")
        List<Object[]> findMonthlySales();

}