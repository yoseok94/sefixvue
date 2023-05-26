package org.fix.sefixvue.accounting.model.dto;

import lombok.Data;

@Data
public class TradeSummaryDto {
    private int year;
    private int month;
    private Long totalSales;

    public TradeSummaryDto(int year, int month, Long totalSales) {
        this.year = year;
        this.month = month;
        this.totalSales = totalSales;
    }
}
