package org.fix.sefixvue.business.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="PlanDisbursement")
public class PlanDisbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "plan_disbursement_seq", allocationSize = 1)
    @Column(name = "plan_no")
    private long plan_no;
    @Column(name = "plan_title")
    private String plan_title;
    @Column(name = "target_quantity")
    private int target_quantity;
    @Column(name = "target_amount")
    private int target_amount;
    @Column(name = "plan_detail")
    private String plan_detail;
    @Column(name = "plan_remarks")
    private String plan_remarks;
    @Column(name = "plan_date")
    private java.sql.Date plan_date;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "disbursement_supply_price")
    private int disbursement_supply_price;
    @Column(name = "disbursement_tax")
    private int disbursement_tax;
    @Column(name = "total_amount")
    private int total_amount;
    @Column(name = "tradetype")
    private int tradetype;
    @Column(name = "disbursement_detail")
    private String disbursement_detail;
    @Column(name = "disbursement_remarks")
    private String disbursement_remarks;
    @Column(name = "sent_date")
    private java.sql.Date sent_date;
    @Column(name = "status")
    private int status;
    @Column(name = "dept_name")
    private String dept_name;
    @Column(name = "dept_id")
    private int dept_id;
    @Column(name = "account_name")
    private String account_name;
    @Column(name = "product_no")
    private int product_no;
}
