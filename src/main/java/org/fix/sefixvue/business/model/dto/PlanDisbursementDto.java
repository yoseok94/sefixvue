package org.fix.sefixvue.business.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fix.sefixvue.admin.model.dto.ProductDto;
import org.fix.sefixvue.business.entity.Account;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDisbursementDto {
    private Long planno;
    private String plantitle;
    private int targetquantity;
    private int targetamount;
    private String plandetail;
    private String planremarks;
    private Date plandate;
    private int quantity;
    private int disbursementsupplyprice;
    private int disbursementtax;
    private int totalamount;
    private int tradetype;
    private String disbursementdetail;
    private String disbursementremarks;
    private Date sentdate;
    private int status;
    private String deptname;
    private String empId;
    private String accountname;
    private Long productno;
    private String productname;
    private String empname;
    private String emplevel;
    private Long accountno;
    private int purchaseprice;

}
