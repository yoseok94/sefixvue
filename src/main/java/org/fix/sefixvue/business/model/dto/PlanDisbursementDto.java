package org.fix.sefixvue.business.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDisbursementDto {
    private long plan_no;
    private String plan_title;
    private int target_quantity;
    private int target_amount;
    private String plan_detail;
    private String plan_remarks;
    private java.sql.Date plan_date;
    private int quantity;
    private int disbursement_supply_price;
    private int disbursement_tax;
    private int total_amount;
    private int trade_type;
    private String disbursement_detail;
    private String disbursement_remarks;
    private java.sql.Date sent_date;
    private int status;
    private String dept_name;
    private String emp_id;
    private String account_name;
    private Long product_no;

}
