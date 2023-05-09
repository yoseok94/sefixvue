package org.fix.sefixvue.hrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder                              // 클래스로 컴파일 되라.
@Table(name = "Employee")                // 자동으로 생성될 테이블 이름이 BOARD 이다
@Entity
public class Employee {
    @Id                               // PK 처리
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "employee_seq", allocationSize = 1)
    @Column(name = "emp_no")
    private long empno;
    @Column(name = "emp_id")
    private String empid;
    @Column(name = "emp_pw")
    private String emppw;
    @Column(name = "emp_name")
    private String empname;
    @Column(name = "emp_phone")
    private String empphone;
    @Column(name = "emp_address")
    private String empaddress;
    @Column(name = "emp_email")
    private String empemail;
    @Column(name = "emp_birth")
    private java.sql.Date empbirth;
    @Column(name = "emp_hiredate")
    private java.sql.Date emphiredate;
    @Column(name = "emp_level")
    private int emplevel;
    @Column(name = "emp_status")
    private String empstatus;
    @Column(name = "dept_name")
    private String deptname;
    @Column(name = "emp_annual")
    private Integer empannual;
    @Column(name = "emp_profile")
    private String empprofile;
}
