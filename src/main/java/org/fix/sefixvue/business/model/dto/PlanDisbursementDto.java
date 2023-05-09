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
    private long planno;
    private String plantitle;
    private int targetquantity;
    private int targetamount;
    private String plandetail;
    private String planremarks;
    private java.sql.Date plandate;
    private int quantity;
    private int disbursementsupplyprice;
    private int disbursementtax;
    private int totalamount;
    private int tradetype;
    private String disbursementdetail;
    private String disbursementremarks;
    private java.sql.Date sentdate;
    private int status;
    private String deptname;
    private String empid;
    private String accountname;
    private Long productno;

}
