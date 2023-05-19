package org.fix.sefixvue.admin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Dept")
@Entity
public class Dept {


    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySequence")
    //@SequenceGenerator(name = "mySequence", sequenceName = "dept_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_no")
    private long deptno;    // 부서 순번
    @Column(name = "product_id")
    private String productid;    // 상품 아이디
    @Column(name = "dept_id")
    private String deptid;    // 부서 아이디
    @Column(name = "dept_name")
    private String deptname;    // 부서 이름
    @Column(name = "dept_date")
    private LocalDateTime deptdate;    // 부서 작성일시

}
