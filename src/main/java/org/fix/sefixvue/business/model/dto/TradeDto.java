package org.fix.sefixvue.business.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDto {
    private long tradingno;
    private java.sql.Date tradingday;
    private java.sql.Date duedate;
    private String productname;
    private int unitprice;
    private int quantity;
    private char includetax;
    private int supplyprice;
    private int tax;
    private int totalprice;
    private java.sql.Date ordersdate;
    private String orderstype;
    private int ordersprice;
    private int totalordersprice;
    private String accountname;
    private Long deptno;
    private String empid;

}
