package org.fix.sefixvue.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private long product_no;    // 상품 순번
    private String product_id;    // 상품 아이디
    private String product_name;    // 상품 이름
    private String product_category;    // 상품 카테고리
    private int product_cost;    // 상품 원가
    private int purchase_price;    // 상품 구매가
    private int consumer_price;    // 상품 소비자가
    private java.sql.Date product_date;    // 상품 작성 일시
    private String product_img;    // 상품 첨부 이미지
    private String product_remarks;    // 상품 비고

}
