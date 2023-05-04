package org.fix.sefixvue.accounting.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder                              // 클래스로 컴파일 되라.
@Table(name = "Slipstatement")                // 자동으로 생성될 테이블 이름이 BOARD 이다
@Entity
public class Slipstatement {

    @Id                               // PK 처리
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "slipstatement_seq", allocationSize = 1)
    @Column(name = "slipstatement_no")
    private Integer slipstatement_no;
    @Column(name = "account_no")
    private Integer account_no;
    @Column(name = "tradetype")
    private String tradetype;
    @Column(name = "slipstatement_amount")
    private long slipstatement_amount;
    @Column(name = "slipstatement_brief")
    private String slipstatement_brief;
    @Column(name = "slipstatement_date")
    private java.sql.Date slipstatement_date;

}
