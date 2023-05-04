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
    private Integer salary_no;
    @Column(name = "emp_id")
    private String emp_id;
    @Column(name = "dept_id")
    private String dept_id;
    @Column(name = "ad_no")
    private Integer ad_no;
    @Column(name = "salaryclassification")
    private int salaryclassification;
    @Column(name = "totalamountpaid")
    private long totalamountpaid;
    @Column(name = "actualpaymentamount")
    private long actualpaymentamount;
    @Column(name = "workdays")
    private int workdays;
    @Column(name = "totalworkhours")
    private int totalworkhours;
    @Column(name = "payment_date")
    private java.sql.Date payment_date;
    @Column(name = "paystubsend_date")
    private java.sql.Date paystubsend_date;

}
