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
    private long emp_no;
    @Column(name = "emp_id")
    private String emp_id;
    @Column(name = "emp_pw")
    private String emp_pw;
    @Column(name = "emp_name")
    private String emp_name;
    @Column(name = "emp_phone")
    private String emp_phone;
    @Column(name = "emp_address")
    private String emp_address;
    @Column(name = "emp_email")
    private String emp_email;
    @Column(name = "emp_birth")
    private java.sql.Date emp_birth;
    @Column(name = "emp_hiredate")
    private java.sql.Date emp_hiredate;
    @Column(name = "emp_level")
    private int emp_level;
    @Column(name = "emp_status")
    private String emp_status;
    @Column(name = "dept_name")
    private String dept_name;
    @Column(name = "emp_annual")
    private int emp_annual;
    @Column(name = "emp_profile")
    private String emp_profile;
}
