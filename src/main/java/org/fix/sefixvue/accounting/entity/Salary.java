package org.fix.sefixvue.accounting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Salary")
@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "salary_seq", allocationSize = 1)
    @Column(name = "salary_no")
    private long salaryno;
    @Column(name = "emp_id")
    private String empid;
    @Column(name = "dept_id")
    private String deptid;
    @Column(name = "ad_no")
    private Long adno;
    @Column(name = "salaryclassification")
    private Integer salaryclassification;
    @Column(name = "totalamountpaid")
    private Integer totalamountpaid;
    @Column(name = "actualpaymentamount")
    private long actualpaymentamount;
    @Column(name = "workdays")
    private Integer workdays;
    @Column(name = "totalworkhours")
    private Integer totalworkhours;
    @Column(name = "payment_date")
    private java.sql.Date paymentdate;
    @Column(name = "paystubsend_date")
    private java.sql.Date paystubsenddate;

}
