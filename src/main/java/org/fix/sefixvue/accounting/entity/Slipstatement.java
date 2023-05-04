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
@Table(name = "Slipstatement")
@Entity
public class Slipstatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "slipstatement_seq", allocationSize = 1)
    @Column(name = "slipstatement_no")
    private long slipstatement_no;
    @Column(name = "trading_no")
    private long trading_no;
    @Column(name = "tradetype")
    private String tradetype;
    @Column(name = "slipstatement_amount")
    private Integer slipstatement_amount;
    @Column(name = "slipstatement_brief")
    private String slipstatement_brief;
    @Column(name = "slipstatement_date")
    private java.sql.Date slipstatement_date;

}
