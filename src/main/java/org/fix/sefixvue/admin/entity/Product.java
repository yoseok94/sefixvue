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
@Table(name = "Product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "product_seq", allocationSize = 1)
    @Column(name = "product_no")
    private long product_no;    // 상품 순번
    @Column(name = "product_id")
    private String product_id;    // 상품 아이디
    @Column(name = "product_name")
    private String product_name;    // 상품 이름
    @Column(name = "product_category")
    private String product_category;    // 상품 카테고리
    @Column(name = "product_cost")
    private int product_cost;    // 상품 원가
    @Column(name = "purchase_price")
    private int purchase_price;    // 상품 구매가
    @Column(name = "consumer_price")
    private int consumer_price;    // 상품 소비자가
    @Column(name = "product_date")
    private java.sql.Date product_date;    // 상품 작성 일시
    @Column(name = "product_img")
    private String product_img;    // 상품 첨부 이미지
    @Column(name = "product_remarks")
    private String product_remarks;    // 상품 비고
}
