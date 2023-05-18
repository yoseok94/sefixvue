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
public class AllowancesDeductionsDto {
    private long adno;                              // 수당 및 공제 순번
    private int adcode;                             // 수당 및 공제 코드
    private String adname;                          // 수당 및 공제 이름
    private String adcalculation;                   // 수당 및 공제 계산식
    private String adcalculationmethod;             // 수당 및 공제 산출방법

}
