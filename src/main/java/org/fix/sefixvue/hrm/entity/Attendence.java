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
//    근태 정보 순번 ATTENDENCE_NO NUMBER O
//    사원 아이디 EMP_ID VARCHAR2(50) O EMPLOYEE
//    사원 이름 EMP_NAME VARCHAR2(30) NN
//    출근시간 INTIME DATE N
//    퇴근시간 OUTTIME DATE N
//    출근 IP INIP VARCHAR2(100) N
//    퇴근 IP OUTIP VARCHAR2(100) N
//    근태 변동 신청 일자 REQUEST_DATE DATE N
//    근태 신청 사유 REASON VARCHAR2(50) N
//    기타 사유 추가 내용 REASONPR VARCHAR2(300) N
//    휴무여부(Y/N) HOLIDAY CHAR(1) NN / DEFAULT(Y)
//    구분1정상2비정상/
//    일자별 근태 구분 DIVIDE NUMBER N
}
