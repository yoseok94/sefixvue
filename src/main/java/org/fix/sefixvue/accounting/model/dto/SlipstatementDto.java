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
public class SlipstatementDto {
    private long slipstatementno;               // 전표 순번
    private long tradingno;                     // 거래 순번
    private String accountname;                  // 거래처 이름
    private String tradetype;                   // 거래 유형
    private long slipstatementamount;           // 전표 금액
    private String slipstatementbrief;          // 전표 적요
    private java.sql.Date slipstatementdate;    // 전표 발행 일자
}