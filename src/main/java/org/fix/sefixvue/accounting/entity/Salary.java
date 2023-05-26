package org.fix.sefixvue.accounting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fix.sefixvue.hrm.entity.Employee;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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
    private String empId;
    @Column(name = "emp_name")
    private String empname;
    @Column(name = "emp_level")
    private String emplevel;
    @Column(name = "dept_name")
    private String deptname;
    @Column(name = "workhours")
    private int workhours;
    @Column(name = "overtimehours")
    private int overtimehours;
    @Column(name = "basesalary")
    private int basesalary;
    @Column(name = "overtimesalary")
    private int overtimesalary;
    @Column(name = "totalpaymentsalary")
    private long totalpaymentsalary;
    @Column(name = "paymentdate")
    private Date paymentdate;
    @Column(name = "emp_hiredate")
    private Date emphiredate;
    @Column(name = "earnedincometax")
    private int earnedincometax;
    @Column(name = "localincometax")
    private int localincometax;
    @Column(name = "nationalpensionfee")
    private int nationalpensionfee;
    @Column(name = "healthinsurancepremium")
    private int healthinsurancepremium;
    @Column(name = "employmentinsurancepremium")
    private int employmentinsurancepremium;
    @Column(name = "longtermcareinsurancepremium")
    private int longtermcareinsurancepremium;
    @Column(name = "totaldeductionsamount")
    private int totaldeductionsamount;
    @Column(name = "actualpaymentsalary")
    private long actualpaymentsalary;
}