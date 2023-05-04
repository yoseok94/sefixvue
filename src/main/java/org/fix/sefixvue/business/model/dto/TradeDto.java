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
    private long trading_no;
    private java.sql.Date trading_day;
    private java.sql.Date due_date;
    private String product_name;
    private int unit_price;
    private int quantity;
    private char include_tax;
    private int supply_price;
    private int tax;
    private int total_price;
    private java.sql.Date orders_date;
    private String orders_type;
    private int orders_price;
    private int total_orders_price;
    private String account_name;
    private Long dept_no;
    private String emp_id;

}
