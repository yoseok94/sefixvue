package org.fix.sefixvue.hrm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder                              // 클래스로 컴파일 되라.
@Table(name = "Attendence")                // 자동으로 생성될 테이블 이름이 BOARD 이다
@Entity
public class Attendence {
    @Id                               // PK 처리
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendence_no")
    private long attendenceno;
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "emp_name")
    private String empname;
    @Column(name = "dept_name")
    private String deptname;
    @Column(name = "intime")
    private LocalDateTime intime;
    @Column(name = "outtime")
    private LocalDateTime outtime;
    @Column(name = "inip")
    private String inip;
    @Column(name = "outip")
    private String outip;
    @Column(name = "request_date")
    private LocalDate requestdate;
    @Column(name = "reason")
    private String reason;
    @Column(name = "reasonpr")
    private String reasonpr;
    @Column(name = "request_result")
    private String requestresult;
    @Column(name = "divide")
    private String divide;

}
