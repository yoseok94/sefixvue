package org.fix.sefixvue.accounting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllowancesDeductionsDto {
    private long ad_no;    // 수당 및 공제 순번
    private int ad_code;    // 수당 및 공제 코드
    private String ad_name;    // 수당 및 공제 이름
    private String ad_calculation;    // 수당 및 공제 계산식
    private String ad_calculationmethod;    // 수당 및 공제 산출방법
}
