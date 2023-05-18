package org.fix.sefixvue.accounting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryDto {
    private long salaryno;                              // 급여순번
    private String empid;                               // 사원코드
    private String empname;                             // 사원이름
    private String jobname;                               // 직급이름
    private String deptname;                             // 부서이름
    private int workhours;                              // 근무시간
    private int overtimehours;                              // 시간외근무시간
    private String basesalary;                              // 기본급여
    private String overtimesalary;                   // 초과근무수당
    private String totalpaymentsalary;                     // 지급총액
    private String earnedincometax;                        // 근로소득세
    private String localincomtax;                          // 지방소득세
    private String nationalpensionfee;                     // 국민연금료
    private String healthinsurancepremium;                 // 건강보험료
    private String employmentinsurancepremium;             // 고용보험료
    private String longtermcareinsurancepremium;           // 장기요양보험료
    private String totaldeductionsamount;                  // 공제총액
    private String actualpaymentsalary;                   // 실지급액
    private java.util.Date paymentdate;                 // 지급연월일
    private java.util.Date emp_hiredate;                // 입사일자
}
