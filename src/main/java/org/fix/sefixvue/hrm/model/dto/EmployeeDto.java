package org.fix.sefixvue.hrm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private long empno;
    private String empId;
    private String emppw;
    private String empname;
    private String empphone;
    private String empaddress;
    private String empemail;
    private String empbirth;
    private String emphiredate;
    private String emplevel;
    private String empstatus;
    private String deptname;
    private int empannual;
    private String empprofile;
//    사원 순번 EMP_NO NUMBER O
//    사원 아이디 EMP_ID VARCHAR2(50) UNIQUE
//    사원 패스워드 EMP_PW VARCHAR2(500) NN
//    사원 이름 EMP_NAME VARCHAR2(30) NN
//    사원 전화번호 EMP_PHONE VARCHAR2(30) NN
//    사원 주소 EMP_ADDRESS VARCHAR2(300) NN
//    사원 이메일 EMP_EMAIL VARCHAR2(100) NN
//    사원 생년월일 EMP_BIRTH DATE NN
//    사원 입사일자 EMP_HIREDATE DATE SYSDATE
//    사원 레벨 EMP_LEVEL NUMBER NN
//    사원 재직상태 EMP_STATUS CHAR(1) NN / DEFAULT(N)
//    담당부서 이름 DEPT_NAME VARCHAR2(30) N O DEPT
//    사원연차수 EMP_ANNUAL NUMBER N
//    사원 프로필사진 EMP_PROFILE VARCHAR2(200) N
}
