package org.fix.sefixvue.accounting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fix.sefixvue.business.entity.Trade;

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
    private long slipstatementno;

    @ManyToOne
    @JoinColumn(name = "trading_no", insertable = false, updatable = false)
    private Trade trade;

    @Column(name = "tradetype")
    private String tradetype;
    @Column(name = "slipstatement_amount")
    private int slipstatementamount;
    @Column(name = "slipstatement_brief")
    private String slipstatementbrief;
    @Column(name = "slipstatement_date")
    private java.sql.Date slipstatementdate;

}
