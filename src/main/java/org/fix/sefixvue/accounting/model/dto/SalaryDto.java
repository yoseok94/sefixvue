package org.fix.sefixvue.accounting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryDto {
    private long salary_no;    // 급여순번
    private String emp_id;    // 사번
    private String dept_id;    // 부서 아이디
    private long ad_no;    // 수당 및 공제 순번
    private int salaryclassification;    // 급여 구분
    private long totalamountpaid;    // 급여 지급 총액
    private long actualpaymentamount;    // 급여 실 지급액
    private int workdays;    // 근로일수
    private int totalworkhours;  // 총근로시간
    private java.sql.Date payment_date;    // 급여 지급 일자
    private java.sql.Date paystubsend_date;    // 명세서 발송 일자

}
