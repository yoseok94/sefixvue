package org.fix.sefixvue.business.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fix.sefixvue.admin.entity.Product;
import org.fix.sefixvue.hrm.entity.Employee;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="PLAN_DISBURSEMENT")
public class PlanDisbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "plan_disbursement_seq", allocationSize = 1)
    @Column(name = "plan_no")
    private Long planno;
    @Column(name = "plan_title")
    private String plantitle;
    @Column(name = "target_quantity")
    private int targetquantity;
    @Column(name = "target_amount")
    private int targetamount;
    @Column(name = "plan_detail")
    private String plandetail;
    @Column(name = "plan_remarks")
    private String planremarks;
    @Column(name = "plan_date")
    private Date plandate;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "disbursement_supply_price")
    private int disbursementsupplyprice;
    @Column(name = "disbursement_tax")
    private int disbursementtax;
    @Column(name = "total_amount")
    private int totalamount;
    @Column(name = "tradetype")
    private int tradetype;
    @Column(name = "disbursement_detail")
    private String disbursementdetail;
    @Column(name = "disbursement_remarks")
    private String disbursementremarks;
    @Column(name = "sent_date")
    private Date sentdate;
    @Column(name = "status")
    private int status;
    @Column(name = "dept_name")
    private String deptname;
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "account_name")
    private String accountname;
    @Column(name = "product_no")
    private Long productno;

}