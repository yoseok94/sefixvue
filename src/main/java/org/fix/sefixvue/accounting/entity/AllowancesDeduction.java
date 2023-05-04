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
@Table(name = "Allowances_deduction")
@Entity
public class AllowancesDeduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "ad_seq", allocationSize = 1)
    @Column(name = "ad_no")
    private Integer ad_no;
    @Column(name = "ad_code")
    private int ad_code;
    @Column(name = "ad_name")
    private String ad_name;
    @Column(name = "ad_calculation")
    private String ad_calculation;
    @Column(name = "ad_calculationmethod")
    private String ad_calculationmethod;

}
