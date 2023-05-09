package org.fix.sefixvue.accounting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ALLOWANCES_DEDUCTIONS")
@Entity
public class AllowancesDeductions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "ad_seq", allocationSize = 1)
    @Column(name = "ad_no")
    private long adno;
    @Column(name = "ad_code")
    private Integer adcode;
    @Column(name = "ad_name")
    private String adname;
    @Column(name = "ad_calculation")
    private String adcalculation;
    @Column(name = "ad_calculationmethod")
    private String adcalculationmethod;

}
