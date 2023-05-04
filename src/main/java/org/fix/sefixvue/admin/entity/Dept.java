package org.fix.sefixvue.admin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Dept")
@Entity
public class Dept {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "attendence_seq", allocationSize = 1)
    @Column(name = "dept_no")
    private long dept_no;    // 부서 순번
    @Column(name = "product_id")
    private String product_id;    // 상품 아이디
    @Column(name = "dept_id")
    private String dept_id;    // 부서 아이디
    @Column(name = "dept_name")
    private String dept_name;    // 부서 이름
    @Column(name = "dept_date")
    private java.sql.Date dept_date;    // 부서 작성일시

}
