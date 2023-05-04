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
    private long trading_no;
    @Column(name = "trading_day")
    private java.sql.Date trading_day;
    @Column(name = "due_date")
    private java.sql.Date due_date;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "unit_price")
    private int unit_price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "include_tax")
    private boolean include_tax;
    @Column(name = "supply_price")
    private int supply_price;
    @Column(name = "tax")
    private int tax;
    @Column(name = "total_price")
    private int total_price;
    @Column(name = "orders_date")
    private java.sql.Date orders_date;
    @Column(name = "orders_type")
    private String orders_type;
    @Column(name = "orders_price")
    private int orders_price;
    @Column(name = "total_orders_price")
    private int total_orders_price;
    @Column(name = "account_name")
    private String account_name;
    @Column(name = "dept_no")
    private int dept_no;
    @Column(name = "emp_id")
    private String emp_id;
}
