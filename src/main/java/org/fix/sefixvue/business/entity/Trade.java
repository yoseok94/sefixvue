package org.fix.sefixvue.business.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Trade")
@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "trade", allocationSize = 1)
    @Column(name = "trading_no")
    private long tradingno;
    @Column(name = "trading_day")
    private java.sql.Date tradingday;
    @Column(name = "due_date")
    private java.sql.Date duedate;
    @Column(name = "product_name")
    private String productname;
    @Column(name = "unit_price")
    private int unitprice;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "include_tax")
    private boolean includetax;
    @Column(name = "supply_price")
    private int supplyprice;
    @Column(name = "tax")
    private int tax;
    @Column(name = "total_price")
    private int totalprice;
    @Column(name = "orders_date")
    private java.sql.Date ordersdate;
    @Column(name = "orders_type")
    private String orderstype;
    @Column(name = "orders_price")
    private int ordersprice;
    @Column(name = "total_orders_price")
    private int totalordersprice;
    @Column(name = "account_name")
    private String accountname;
    @Column(name = "dept_no")
    private int deptno;
    @Column(name = "emp_id")
    private String empid;
    @Column(name = "tradetype")
    private Integer tradetype;
}
