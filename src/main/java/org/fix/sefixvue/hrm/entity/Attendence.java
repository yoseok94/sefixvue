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
@Table(name = "Attendence")                // 자동으로 생성될 테이블 이름이 BOARD 이다
@Entity
public class Attendence {
    @Id                               // PK 처리
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "attendence_seq", allocationSize = 1)
    @Column(name = "attendence_no")
    private long attendenceno;
    @Column(name = "emp_id")
    private String empid;
    @Column(name = "emp_name")
    private String empname;
    @Column(name = "intime")
    private java.sql.Date intime;
    @Column(name = "outtime")
    private java.sql.Date outtime;
    @Column(name = "inip")
    private String inip;
    @Column(name = "outip")
    private String outip;
    @Column(name = "request_date")
    private java.sql.Date requestdate;
    @Column(name = "reason")
    private String reason;
    @Column(name = "reasonpr")
    private String reasonpr;
    @Column(name = "holiday")
    private String holiday;
    @Column(name = "divide")
    private String divide;

}
